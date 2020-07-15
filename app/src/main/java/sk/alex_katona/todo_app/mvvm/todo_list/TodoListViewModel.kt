package sk.alex_katona.todo_app.mvvm.todo_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import sk.alex_katona.todo_app.mvvm.BaseViewModel
import java.util.*

data class TodoListViewState(
    val items: List<TodoItem> = emptyList(),
    val isLoading: Boolean = false
)

sealed class TodoListActions {
    object Init : TodoListActions()
    object GenerateNewTodoItem : TodoListActions()
}

sealed class TodoListPartialState {
    data class TodoItems(val items: List<TodoItem>) : TodoListPartialState()
    object Loading : TodoListPartialState()
}

class TodoListViewModel @ViewModelInject constructor(
    private val todoListInteractor: TodoListInteractor
) : BaseViewModel<TodoListViewState, TodoListActions, TodoListPartialState>() {

    override fun emitAction(action: TodoListActions) {
        when (action) {
            TodoListActions.Init -> loadStoredTodos()
            TodoListActions.GenerateNewTodoItem -> generateNewTodoItem()
        }
    }

    private fun generateNewTodoItem() {
        viewModelScope.launch {
            emitPartialState(TodoListPartialState.Loading)
            todoListInteractor.storeTodoItem(TodoItem(UUID.randomUUID().toString().take(5)))
            loadStoredTodos(false)
        }
    }

    private fun loadStoredTodos(withLoading: Boolean = true) {
        viewModelScope.launch {
            if (withLoading) {
                emitPartialState(TodoListPartialState.Loading)
            }
            todoListInteractor.getTodoItems()
                .collect {
                    emitPartialState(TodoListPartialState.TodoItems(it))
                }
        }
    }

    override fun getInitialState(): TodoListViewState =
        TodoListViewState()

    override fun reduce(
        previousState: TodoListViewState,
        partialState: TodoListPartialState
    ): TodoListViewState {
        return when (partialState) {
            is TodoListPartialState.TodoItems -> previousState.copy(
                items = partialState.items,
                isLoading = false
            )
            TodoListPartialState.Loading -> previousState.copy(isLoading = true)
        }
    }
}