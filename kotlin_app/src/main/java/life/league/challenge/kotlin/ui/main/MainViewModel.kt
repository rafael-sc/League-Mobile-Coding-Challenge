package life.league.challenge.kotlin.ui.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.commom.BaseViewModel
import life.league.challenge.kotlin.commom.CoroutineDispatcherProvider
import life.league.challenge.kotlin.commom.exceptions.ApiException
import life.league.challenge.kotlin.domain.model.Post
import life.league.challenge.kotlin.domain.usecase.posts.PostsUseCase

class MainViewModel(
    private val postsUseCase: PostsUseCase,
    dispatcherProvider: CoroutineDispatcherProvider
) : BaseViewModel(dispatcherProvider) {

    private val loadingState = MutableSharedFlow<Boolean>()
    fun loadingState(): SharedFlow<Boolean> = loadingState

    private val errorState = MutableSharedFlow<Throwable>()
    fun errorState(): SharedFlow<Throwable> = errorState

    private val posts = MutableSharedFlow<List<Post>>()
    fun posts(): SharedFlow<List<Post>> = posts

    private val loadedPosts: MutableList<Post> = mutableListOf()

    fun getPosts() = viewModelScope.launch(mainExceptionHandler) {
        loadingState.emit(true)
        try {
            val result = postsUseCase.getPosts()
            loadedPosts.clear()
            loadedPosts.addAll(result)
        } catch (e: ApiException) {
            errorState.emit(e)
        }
        posts.emit(loadedPosts)
        loadingState.emit(false)
    }
}
