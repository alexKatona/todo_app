package sk.alex_katona.todo_app.flow.todo_list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class TodoItem(val title: String)

interface TodoListInteractor {
    suspend fun getTodoItems(): Flow<List<TodoItem>>
}

class TodoListInteractorImpl @Inject constructor() : TodoListInteractor {

    //TODO add loeading from database etc.
    override suspend fun getTodoItems(): Flow<List<TodoItem>> {
        return flow {
            emit(listOf(TodoItem("---test1---")))
            kotlinx.coroutines.delay(3000)
            emit(listOf(TodoItem("+++test2+++")))
        }
    }

}