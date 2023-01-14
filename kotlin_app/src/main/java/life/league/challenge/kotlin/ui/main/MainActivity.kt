package life.league.challenge.kotlin.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import life.league.challenge.kotlin.commom.exceptions.UnableToLoginException
import life.league.challenge.kotlin.commom.extensions.setupObserverOnCreated
import life.league.challenge.kotlin.databinding.ActivityMainBinding
import life.league.challenge.kotlin.di.MainModule
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.ui.main.adapter.PostsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    private val postsAdapter: PostsAdapter by lazy {
        PostsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(MainModule.modules)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        setupObserver()
    }

    private fun initViews() {
        binding.run {
            buttonLogin.setOnClickListener {
                viewModel.initLogin()
            }
        }
    }

    private fun setupObserver() {
        viewModel.run {
            setupObserverOnCreated(loginState() to ::loginStateObserver)
            setupObserverOnCreated(errorState() to ::errorStateObserver)
            setupObserverOnCreated(posts() to ::postsObserver)
        }
    }

    private fun postsObserver(posts: List<Post>) {
        postsAdapter.setItems(posts)
        binding.recyclerViewPosts.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = postsAdapter
        }
    }

    private fun errorStateObserver(exception: Throwable) {
        val message = if (exception is UnableToLoginException) {
            "Unable to login"
        } else {
            "Unhandled exception - ${exception.message}"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun loginStateObserver(isValidLogin: Boolean) {
        Toast.makeText(this, "Is valid login: $isValidLogin", Toast.LENGTH_SHORT).show()
    }
}
