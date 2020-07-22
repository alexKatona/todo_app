package sk.alex_katona.todo_app.mviCake

import co.zsmb.rainbowcake.withIOContext
import sk.alex_katona.todo_app.mvvm.todo_list.TodoItem
import javax.inject.Inject

class TodoDetailPresenter @Inject constructor(
    private val todoDetailInteractor: TodoDetailInteractor
) {
    suspend fun loadTodoDetails(todoId: Int): TodoItem? = withIOContext {
        todoDetailInteractor.getDetails(todoId)
    }
}