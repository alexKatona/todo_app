package sk.alex_katona.todo_app.mviCake

import sk.alex_katona.todo_app.database.TodoDatabase
import sk.alex_katona.todo_app.database.convert
import sk.alex_katona.todo_app.mvvm.todo_list.TodoItem
import javax.inject.Inject

class TodoDetailInteractor @Inject constructor(
    private val todoDatabase: TodoDatabase
) {
    suspend fun getDetails(todoId: Int): TodoItem? {
        return todoDatabase.todoDao()
            .getAll()
            .firstOrNull { it.id == todoId } // TODO FIX selection
            ?.convert()
    }
}