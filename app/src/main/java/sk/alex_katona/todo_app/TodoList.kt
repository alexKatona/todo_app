package sk.alex_katona.todo_app

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*
import sk.alex_katona.todo_app.base.BaseFragment
import sk.alex_katona.todo_app.navigators.AppScreens

class TodoList : BaseFragment() {

    override fun getLayoutResId(): Int = R.layout.fragment_first

    override fun init(view: View, savedInstanceState: Bundle?) {
        //TODO swap to throttled listener
        button_first.setOnClickListener {
            appNavigator.navigateFrom(AppScreens.List)
        }
    }
}