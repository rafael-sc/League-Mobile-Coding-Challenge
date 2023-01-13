package life.league.challenge.kotlin.ui.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import life.league.challenge.kotlin.commom.BaseViewModel
import life.league.challenge.kotlin.commom.CoroutineDispatcherProvider
import life.league.challenge.kotlin.domain.usecase.LoginUseCase
import life.league.challenge.kotlin.domain.usecase.PostsUseCase


class MainViewModel(
    private val loginUseCase: LoginUseCase,
    private val postsUseCase: PostsUseCase,
    dispatcherProvider: CoroutineDispatcherProvider
) : BaseViewModel(dispatcherProvider) {

    private val loginState = MutableSharedFlow<Boolean>()
    fun loginState(): SharedFlow<Boolean> = loginState

    fun initLogin() = viewModelScope.launch(ioProvider) {
        val result = loginUseCase.login("hello", "world")
        withContext(mainProvider) {
            loginState.emit(result.isNullOrEmpty().not())
        }
    }

    private fun requestPosts() = viewModelScope.launch(ioProvider) {
        val result = postsUseCase.getPosts()
    }

}
