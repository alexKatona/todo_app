package sk.alex_katona.todo_app.mvvm.todo_list

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

class TodoListFragment : BaseFragment() {

    private val todoListViewModel: TodoListViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_first

    override fun init(view: View, savedInstanceState: Bundle?) {
        todoListViewModel.getViewStateLiveData().observe(this, render())

        button_interaction.clicksThrottled(lifecycleScope) {
            todoListViewModel.emitAction(TodoListActions.Init)
        }

        button_add_random_todo.clicksThrottled(lifecycleScope) {
            todoListViewModel.emitAction(TodoListActions.GenerateNewTodoItem)
        }

        button_navigation.clicksThrottled(lifecycleScope) {
            appNavigator.navigateFrom(AppScreens.List)
        }
    }

    private fun render(): Observer<TodoListViewState> {
        return Observer {
            println("Rendering: $it")
            loading.visibility = if (it.isLoading) View.VISIBLE else View.GONE
            textview_first.text =
                it.items.ifEmpty {
                    listOf(
                        TodoItem(
                            "Seems there are no todo items, lets generate them"
                        )
                    )
                }
                    .joinToString(separator = "\n")
        }
    }
}