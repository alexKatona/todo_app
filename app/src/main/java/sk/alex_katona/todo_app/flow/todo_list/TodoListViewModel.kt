package sk.alex_katona.todo_app.flow.todo_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import sk.alex_katona.todo_app.base.BaseViewModel

data class TodoListViewState(
    val items: List<TodoItem> = emptyList()
)

sealed class TodoListActions {
    object Init : TodoListActions()
}

sealed class TodoListPartialState {
    data class TodoItems(val items: List<TodoItem>) : TodoListPartialState()
}

class TodoListViewModel @ViewModelInject constructor(
    private val todoListInteractor: TodoListInteractor
) : BaseViewModel<TodoListViewState, TodoListActions, TodoListPartialState>() {

    override fun postAction(action: TodoListActions) {
        when (action) {
            TodoListActions.Init -> loadStoredTodos()
        }
    }

    private fun loadStoredTodos() {
        viewModelScope.launch {
            todoListInteractor.getTodoItems()
                .distinctUntilChanged()
                .onEach {
                    emitPartialState(TodoListPartialState.TodoItems(it))
                }
                .launchIn(this)
        }
    }

    override fun getInitialState(): TodoListViewState = TodoListViewState()

    override fun reduce(
        previousState: TodoListViewState,
        partialState: TodoListPartialState
    ): TodoListViewState {
        return when (partialState) {
            is TodoListPartialState.TodoItems -> previousState.copy(items = partialState.items)
        }
    }
}