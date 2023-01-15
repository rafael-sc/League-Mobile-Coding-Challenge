package life.league.challenge.kotlin.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.commom.exceptions.ApiException
import life.league.challenge.kotlin.commom.extensions.isConnected
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
        setTitle(R.string.general_posts)
        setContentView(binding.root)
        setupObserver()
        if (this.isConnected()) {
            viewModel.getPosts()
        } else {
            showDisconnectedWarning()
        }
    }

    private fun showDisconnectedWarning() {
        Snackbar.make(
            binding.root,
            R.string.no_internet_connection_available,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.general_retry) {
            if (this.isConnected()) {
                viewModel.getPosts()
            }
        }.show()
    }

    private fun setupObserver() {
        viewModel.run {
            setupObserverOnCreated(loadingState() to ::loadingStateObserver)
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

    private fun errorStateObserver(e: Throwable) {
        val message = when (e) {
            is ApiException.UnableToLoginException -> getString(R.string.error_unable_to_login)
            is ApiException.UnableToGetUsersException -> getString(R.string.error_unable_get_users)
            is ApiException.UnableToGetPostsException -> getString(R.string.error_unable_get_posts)
            else -> getString(R.string.error_generic_message)
        }
        Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.general_retry) {
                viewModel.getPosts()
            }
            .show()
    }

    private fun loadingStateObserver(isLoading: Boolean) {
        binding.run {
            circularProgressIndicator.isVisible = isLoading
            recyclerViewPosts.isVisible = isLoading.not()
        }
    }
}
