package sk.alex_katona.todo_app.mviCake

import co.zsmb.rainbowcake.withIOContext
import javax.inject.Inject

class TodoDetailPresenter @Inject constructor(
    private val todoDetailInteractor: TodoDetailInteractor
) {
    suspend fun loadTodoDetails(): TodoDetails = withIOContext {
        todoDetailInteractor.getDetails()
    }
}