package sk.alex_katona.todo_app.mviCake

import androidx.hilt.lifecycle.ViewModelInject
import co.zsmb.rainbowcake.base.RainbowCakeViewModel

sealed class TodoDetailViewState {
    object Loading : TodoDetailViewState()
    object Init : TodoDetailViewState()
    data class Data(val details: TodoDetails) : TodoDetailViewState()
}

class TodoDetailViewModel @ViewModelInject constructor(
    private val todoDetailPresenter: TodoDetailPresenter
) : RainbowCakeViewModel<TodoDetailViewState>(TodoDetailViewState.Init) {

    fun loadDetails() = execute {
        viewState = TodoDetailViewState.Loading
        val details = todoDetailPresenter.loadTodoDetails()
        viewState = TodoDetailViewState.Data(details)
    }

}