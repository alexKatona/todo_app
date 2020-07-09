package sk.alex_katona.todo_app.navigators

import androidx.navigation.NavController
import androidx.navigation.findNavController
import sk.alex_katona.todo_app.R
import sk.alex_katona.todo_app.base.BaseNavigator
import sk.alex_katona.todo_app.managers.AppActivityManager
import javax.inject.Inject

sealed class AppScreens {
    object List : AppScreens()
    object Detail : AppScreens()
}

interface AppNavigator : BaseNavigator<AppScreens>

class AppNavigatorImpl @Inject constructor(
    private val appActivityManager: AppActivityManager
) : AppNavigator {

    override fun navigateFrom(screen: AppScreens) {
        when (screen) {
            AppScreens.List -> getNavController()?.navigate(R.id.action_FirstFragment_to_SecondFragment)
            AppScreens.Detail -> getNavController()?.navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun getNavController(): NavController? {
        return appActivityManager.getCurrentActivity()?.findNavController(R.id.nav_host_fragment)
    }
}