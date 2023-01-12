package life.league.challenge.kotlin.ui.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.commom.BaseViewModel
import life.league.challenge.kotlin.commom.CoroutineDispatcherProvider
import life.league.challenge.kotlin.domain.usecase.LoginUseCase


class MainViewModel(
    private val loginUseCase: LoginUseCase,
    dispatcherProvider: CoroutineDispatcherProvider
) : BaseViewModel(dispatcherProvider) {


    fun initLogin() {
        viewModelScope.launch(mainExceptionHandler) {
            loginUseCase.login("hello", "world")
        }
    }
}
