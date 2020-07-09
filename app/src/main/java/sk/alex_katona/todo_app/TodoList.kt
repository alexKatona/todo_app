package sk.alex_katona.todo_app

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_first.*
import sk.alex_katona.todo_app.base.BaseFragment
import sk.alex_katona.todo_app.helpers.clicksThrottled
import sk.alex_katona.todo_app.navigators.AppScreens

class TodoList : BaseFragment() {

    override fun getLayoutResId(): Int = R.layout.fragment_first

    override fun init(view: View, savedInstanceState: Bundle?) {
        button_first.clicksThrottled(lifecycleScope) {
            appNavigator.navigateFrom(AppScreens.List)
        }
    }
}