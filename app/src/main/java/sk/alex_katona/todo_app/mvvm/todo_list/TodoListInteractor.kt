package sk.alex_katona.todo_app.mvvm.todo_list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sk.alex_katona.todo_app.database.TodoDatabase
import sk.alex_katona.todo_app.database.convert
import javax.inject.Inject

data class TodoItem(val id: Int = 0, val title: String)

interface TodoListInteractor {
    suspend fun getTodoItems(): Flow<List<TodoItem>>
    suspend fun storeTodoItem(todoItem: TodoItem)
}

class TodoListInteractorImpl @Inject constructor(
    private val todoDatabase: TodoDatabase
) : TodoListInteractor {

    override suspend fun getTodoItems(): Flow<List<TodoItem>> {
        return flow {
           todoDatabase.todoDao()
               .getAll()
               .map { it.convert() }
               .also { emit(it) }
        }
    }

    override suspend fun storeTodoItem(todoItem: TodoItem) {
        todoDatabase.todoDao().addAll(todoItem.convert())
    }

}