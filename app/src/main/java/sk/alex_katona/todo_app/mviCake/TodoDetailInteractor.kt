package sk.alex_katona.todo_app.mviCake

import kotlinx.coroutines.delay
import javax.inject.Inject

class TodoDetailInteractor @Inject constructor() {
    suspend fun getDetails(): TodoDetails {
        delay(3000)
        return TodoDetails("this can be loaded from db, cache, remote")
    }
}

data class TodoDetails(
    val name: String
)