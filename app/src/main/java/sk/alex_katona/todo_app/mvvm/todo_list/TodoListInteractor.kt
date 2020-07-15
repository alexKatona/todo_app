package sk.alex_katona.todo_app.mvvm.todo_list

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class TodoItem(val title: String)

interface TodoListInteractor {
    suspend fun getTodoItems(): Flow<List<TodoItem>>
    suspend fun storeTodoItem(todoItem: TodoItem): Flow<Unit>
}

class TodoListInteractorImpl @Inject constructor() :
    TodoListInteractor {

    // this can be a database, api call etc
    private val todoItems: MutableList<TodoItem> = mutableListOf()

    override suspend fun getTodoItems(): Flow<List<TodoItem>> {
        return flow {
            emit(todoItems)
        }.onEach { delay(1000) }
    }

    override suspend fun storeTodoItem(todoItem: TodoItem): Flow<Unit> {
        return flow {
            todoItems.add(todoItem)
            emit(Unit)
        }.onEach { delay(1000) }
    }

}