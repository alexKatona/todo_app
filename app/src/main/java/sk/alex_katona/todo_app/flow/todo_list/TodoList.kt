package sk.alex_katona.todo_app.flow.todo_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_first.*
import sk.alex_katona.todo_app.R
import sk.alex_katona.todo_app.base.BaseFragment
import sk.alex_katona.todo_app.helpers.clicksThrottled
import sk.alex_katona.todo_app.navigators.AppScreens

class TodoList : BaseFragment() {

    private val todoListViewModel: TodoListViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_first

    override fun init(view: View, savedInstanceState: Bundle?) {
        todoListViewModel.getViewStateLiveData().observe(this, render())

        button_interaction.clicksThrottled(lifecycleScope) {
            todoListViewModel.postAction(TodoListActions.Init)
        }

        button_navigation.clicksThrottled(lifecycleScope) {
            appNavigator.navigateFrom(AppScreens.List)
        }
    }

    private fun render(): Observer<TodoListViewState> {
        return Observer {
            textview_first.text = it.items.joinToString(separator = ";")
        }
    }
}