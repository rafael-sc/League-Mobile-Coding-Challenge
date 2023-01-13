package life.league.challenge.kotlin.commom

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel(
    dispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {
    protected open val mainProvider = dispatcherProvider.main
    protected open val ioProvider = dispatcherProvider.io
}
