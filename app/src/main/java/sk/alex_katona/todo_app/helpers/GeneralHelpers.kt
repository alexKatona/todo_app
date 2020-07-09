package sk.alex_katona.todo_app.helpers

import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import ru.ldralighieri.corbind.view.clicks

fun View.clicksThrottled(scope: CoroutineScope, action: () -> Unit) {
    this.clicks()
        .throttleFirst(300)
        .onEach {
            action.invoke()
        }
        .launchIn(scope)
}

fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
    require(periodMillis > 0) { "period should be positive" }
    return flow {
        var lastTime = 0L
        collect { value ->
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastTime >= periodMillis) {
                lastTime = currentTime
                emit(value)
            }
        }
    }
}
