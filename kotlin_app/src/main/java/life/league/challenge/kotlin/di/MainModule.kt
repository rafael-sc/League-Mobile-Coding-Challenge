package life.league.challenge.kotlin.di

import life.league.challenge.kotlin.commom.CoroutineDispatcherProvider
import life.league.challenge.kotlin.domain.usecase.login.LoginUseCase
import life.league.challenge.kotlin.domain.usecase.login.LoginUseCaseImpl
import life.league.challenge.kotlin.domain.usecase.posts.PostsUseCase
import life.league.challenge.kotlin.domain.usecase.posts.PostsUseCaseImpl
import life.league.challenge.kotlin.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

// object to hold MainScreen dependencies
object MainModule {
    private val instance: Module = module {
        viewModel {
            MainViewModel(
                postsUseCase = get(),
                dispatcherProvider = get()
            )
        }

        factory<PostsUseCase> {
            PostsUseCaseImpl(
                postsRepository = get(),
                loginRepository = get()
            )
        }
        factory<LoginUseCase> {
            LoginUseCaseImpl(
                loginRepository = get()
            )
        }
        factory<CoroutineDispatcherProvider> {
            CoroutineDispatcherProvider()
        }
    }

    val modules = instance +
        RetrofitModule.instance +
        LoginModule.instance +
        PostsModule.instance
}
