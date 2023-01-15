package life.league.challenge.kotlin.commom

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    dispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    protected val errorState = MutableSharedFlow<Throwable>()
    fun errorState(): SharedFlow<Throwable> = errorState

    protected open val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            handleException(throwable)
        }

    protected open val mainExceptionHandler = dispatcherProvider.main + exceptionHandler
    protected open val ioExceptionHandler = dispatcherProvider.io + exceptionHandler

    @CallSuper
    private fun handleException(throwable: Throwable) {
        viewModelScope.launch(mainExceptionHandler) {
            errorState.emit(throwable)
        }
        // log to crashlytics or something like it
        Log.e("Exception", throwable.message.orEmpty())
        throwable.printStackTrace()
        return
    }
}
