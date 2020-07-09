package sk.alex_katona.todo_app.flow.todo_list

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor

internal interface TodoListStore :
    Store<TodoListStore.TodoListIntents, TodoListStore.TodoListState, Nothing> {

    sealed class TodoListIntents {
        object Increase : TodoListIntents()
        object Decrease : TodoListIntents()
        data class Sum(val n: Int) : TodoListIntents()
    }

    data class TodoListState(
        val number: Int = 0
    )

}

internal class TodoListStoreImpl(private val storeFactory: StoreFactory) {

    private sealed class TodoListResult {
        class Value(val value: Int) : TodoListResult()
    }

    private object TodoListReducerImpl :
        Reducer<TodoListStore.TodoListState, TodoListResult> {
        override fun TodoListStore.TodoListState.reduce(result: TodoListResult): TodoListStore.TodoListState {
            return when (result) {
                is TodoListResult.Value -> this
            }
        }
    }

    private class TodoListExecutorImpl :
        SuspendExecutor<TodoListStore.TodoListIntents, Nothing, TodoListStore.TodoListState, TodoListResult, Nothing>() {
        override suspend fun executeIntent(
            intent: TodoListStore.TodoListIntents,
            getState: () -> TodoListStore.TodoListState
        ) {
            super.executeIntent(intent, getState)
        }
    }

}