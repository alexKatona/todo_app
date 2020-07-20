package sk.alex_katona.todo_app.mvvm.todo_list

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class TodoItem(val title: String)

interface TodoListInteractor {
    suspend fun getTodoItems(): Flow<List<TodoItem>>
    suspend fun storeTodoItem(todoItem: TodoItem)
}

class TodoListInteractorImpl @Inject constructor() :
    TodoListInteractor {

    // this can be a database, api call etc
    private val todoItems: MutableList<TodoItem> = mutableListOf()

    override suspend fun getTodoItems(): Flow<List<TodoItem>> {
        return channelFlow {
            this.send(todoItems)
        }.onEach {
            println("getTodoItems")
        }.onEach { delay(1000) }
    }

    override suspend fun storeTodoItem(todoItem: TodoItem) {
        println("storeTodoItem")
        delay(1000)
        todoItems.add(todoItem)
    }

}