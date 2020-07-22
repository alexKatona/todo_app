package sk.alex_katona.todo_app.navigators

import androidx.navigation.NavController
import androidx.navigation.findNavController
import sk.alex_katona.todo_app.R
import sk.alex_katona.todo_app.base.BaseNavigator
import sk.alex_katona.todo_app.managers.AppActivityManager
import sk.alex_katona.todo_app.mviCake.TodoDetailFragmentDirections
import sk.alex_katona.todo_app.mvvm.todo_list.TodoListFragmentDirections
import javax.inject.Inject

sealed class AppScreens {
    object Detail : AppScreens()
    data class List(val id: Int) : AppScreens()
}

interface AppNavigator : BaseNavigator<AppScreens>

class AppNavigatorImpl @Inject constructor(
    private val appActivityManager: AppActivityManager
) : AppNavigator {

    override fun navigateFrom(screen: AppScreens) {
        when (screen) {
            is AppScreens.List -> getNavController()?.navigate(TodoListFragmentDirections.actionFirstFragmentToSecondFragment(screen.id))
             AppScreens.Detail -> getNavController()?.navigate(TodoDetailFragmentDirections.actionSecondFragmentToFirstFragment())
        }
    }

    private fun getNavController(): NavController? {
        return appActivityManager.getCurrentActivity()?.findNavController(R.id.nav_host_fragment)
    }
}