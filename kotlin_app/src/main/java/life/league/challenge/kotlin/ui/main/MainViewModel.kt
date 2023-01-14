package life.league.challenge.kotlin.ui.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import life.league.challenge.kotlin.commom.BaseViewModel
import life.league.challenge.kotlin.commom.CoroutineDispatcherProvider
import life.league.challenge.kotlin.domain.exceptions.UnableToLoginException
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.usecase.LoginUseCase
import life.league.challenge.kotlin.domain.usecase.PostsUseCase

class MainViewModel(
    private val loginUseCase: LoginUseCase,
    private val postsUseCase: PostsUseCase,
    dispatcherProvider: CoroutineDispatcherProvider
) : BaseViewModel(dispatcherProvider) {

    private val loginState = MutableSharedFlow<Boolean>()
    fun loginState(): SharedFlow<Boolean> = loginState

    private val errorState = MutableSharedFlow<Throwable>()
    fun errorState(): SharedFlow<Throwable> = errorState

    private val posts = MutableSharedFlow<List<Post>>()
    fun posts(): SharedFlow<List<Post>> = posts

    fun initLogin() = try {
        viewModelScope.launch(ioExceptionHandler) {
            val result = loginUseCase.login("hello", "world")
            withContext(mainExceptionHandler) {
                getPosts(result)
                loginState.emit(result.isNotEmpty())
            }
        }
    } catch (e: UnableToLoginException) {
        viewModelScope.launch(mainExceptionHandler) {
            errorState.emit(e)
        }
    }

    private fun getPosts(accessToken: String) = viewModelScope.launch(ioExceptionHandler) {
        val result = postsUseCase.getPosts(accessToken)

        withContext(mainExceptionHandler) {
            posts.emit(result)
        }
    }
}
