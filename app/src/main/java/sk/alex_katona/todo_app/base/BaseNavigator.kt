package sk.alex_katona.todo_app.base

interface BaseNavigator<T> {
    fun navigateFrom(screen: T)
}