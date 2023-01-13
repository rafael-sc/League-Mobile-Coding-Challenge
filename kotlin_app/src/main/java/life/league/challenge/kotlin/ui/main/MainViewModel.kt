package life.league.challenge.kotlin.ui.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import life.league.challenge.kotlin.commom.BaseViewModel
import life.league.challenge.kotlin.commom.CoroutineDispatcherProvider
import life.league.challenge.kotlin.domain.usecase.LoginUseCase


class MainViewModel(
    private val loginUseCase: LoginUseCase,
    dispatcherProvider: CoroutineDispatcherProvider
) : BaseViewModel(dispatcherProvider) {

    private val loginState = MutableSharedFlow<Boolean>()
    fun loginState(): SharedFlow<Boolean> = loginState

    fun initLogin() {
        viewModelScope.launch(ioProvider) {
            val result = loginUseCase.login("hello", "world")
            withContext(mainProvider) {
                loginState.emit(result.isNullOrEmpty().not())
            }
        }
    }
}
