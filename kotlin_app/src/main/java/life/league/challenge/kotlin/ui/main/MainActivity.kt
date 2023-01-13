package life.league.challenge.kotlin.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import life.league.challenge.kotlin.commom.extensions.setupObserverOnCreated
import life.league.challenge.kotlin.databinding.ActivityMainBinding
import life.league.challenge.kotlin.di.MainModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

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
        setupObserverOnCreated(viewModel.loginState() to ::loginStateObserver)
    }

    private fun loginStateObserver(isValidLogin: Boolean) {
        Toast.makeText(this, "Is valid login: $isValidLogin", Toast.LENGTH_SHORT).show()
    }

}
