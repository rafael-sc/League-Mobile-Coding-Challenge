package life.league.challenge.kotlin.commom

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel(
    dispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    protected open val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            handleException(throwable)
        }

    protected open val mainExceptionHandler = dispatcherProvider.main + exceptionHandler
    protected open val ioExceptionHandler = dispatcherProvider.io + exceptionHandler

    @CallSuper
    private fun handleException(throwable: Throwable) {
        //handle exception
    }
}
