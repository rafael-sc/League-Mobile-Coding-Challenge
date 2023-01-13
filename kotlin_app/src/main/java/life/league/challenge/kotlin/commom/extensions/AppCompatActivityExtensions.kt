package life.league.challenge.kotlin.commom.extensions

import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.SharedFlow

fun <T> ComponentActivity.setupObserverOnCreated(
    pair: Pair<SharedFlow<T>, (T) -> Unit>,
) = this.lifecycleScope.launchWhenCreated {
    pair.first.collect { it?.let(pair.second) }
}
