package sk.alex_katona.todo_app.database

import sk.alex_katona.todo_app.mvvm.todo_list.TodoItem

fun TodoItem.convert(): TodoEntity{
    return TodoEntity(
        title = this.title
    )
}

fun TodoEntity.convert(): TodoItem{
    return TodoItem(
        id = this.id,
        title = this.title
    )
}