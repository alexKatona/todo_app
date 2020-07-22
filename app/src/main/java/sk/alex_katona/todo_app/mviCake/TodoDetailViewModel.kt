package sk.alex_katona.todo_app.mviCake

import androidx.hilt.lifecycle.ViewModelInject
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import sk.alex_katona.todo_app.mvvm.todo_list.TodoItem

sealed class TodoDetailViewState {
    object Loading : TodoDetailViewState()
    object Init : TodoDetailViewState()
    object Error : TodoDetailViewState()
    data class Data(val details: TodoItem) : TodoDetailViewState()
}

class TodoDetailViewModel @ViewModelInject constructor(
    private val todoDetailPresenter: TodoDetailPresenter
) : RainbowCakeViewModel<TodoDetailViewState>(TodoDetailViewState.Init) {

    fun loadDetails(todoId: Int) = execute {
        viewState = TodoDetailViewState.Loading
        val details = todoDetailPresenter.loadTodoDetails(todoId)
        viewState = details?.let { TodoDetailViewState.Data(it) } ?: TodoDetailViewState.Error
    }

}