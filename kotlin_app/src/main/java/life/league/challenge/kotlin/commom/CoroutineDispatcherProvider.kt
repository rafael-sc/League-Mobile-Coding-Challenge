package life.league.challenge.kotlin.commom

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class CoroutineDispatcherProvider(
    val main: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val io: CoroutineDispatcher,
) {
    constructor() : this(Dispatchers.Main, Dispatchers.Default, Dispatchers.IO)
}
