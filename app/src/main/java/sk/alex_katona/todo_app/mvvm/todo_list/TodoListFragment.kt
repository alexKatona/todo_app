package sk.alex_katona.todo_app.mvvm.todo_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.row_todo_item.view.*
import sk.alex_katona.todo_app.R
import sk.alex_katona.todo_app.base.BaseFragment
import sk.alex_katona.todo_app.helpers.clicksThrottled
import sk.alex_katona.todo_app.navigators.AppScreens
import sk.lighture.betteradapter.BetterAdapter
import sk.lighture.betteradapter.EmptyBinderHolder

class TodoListFragment : BaseFragment() {

    private val todoListViewModel: TodoListViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_first

    override fun init(view: View, savedInstanceState: Bundle?) {
        todoListViewModel.getViewStateLiveData().observe(this, render())

        rv_todo_items.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = BetterAdapter(TodoItemBinder())
        }

        button_add_random_todo.clicksThrottled(lifecycleScope) {
            todoListViewModel.emitAction(TodoListActions.GenerateNewTodoItem)
        }

        todoListViewModel.emitAction(TodoListActions.Init)
    }

    private fun render(): Observer<TodoListViewState> {
        return Observer {
            loading.visibility = if (it.isLoading) View.VISIBLE else View.GONE

            (rv_todo_items.adapter as? BetterAdapter)?.data = it.items.toMutableList()
        }
    }

    inner class TodoItemBinder : EmptyBinderHolder<TodoItem>(R.layout.row_todo_item, TodoItem::class.java) {
        override fun bind(item: TodoItem) {
            super.bind(item)
            itemView.tv_todo_title.text = item.title
            itemView.clicksThrottled(lifecycleScope){
                appNavigator.navigateFrom(AppScreens.List(item.id))
            }
        }
    }
}