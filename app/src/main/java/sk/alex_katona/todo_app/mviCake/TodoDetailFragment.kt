package sk.alex_katona.todo_app.mviCake

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_second.*
import sk.alex_katona.todo_app.R
import sk.alex_katona.todo_app.helpers.clicksThrottled
import sk.alex_katona.todo_app.navigators.AppNavigator
import sk.alex_katona.todo_app.navigators.AppScreens
import javax.inject.Inject

@AndroidEntryPoint
class TodoDetailFragment : RainbowCakeFragment<TodoDetailViewState, TodoDetailViewModel>() {

    val args: TodoDetailFragmentArgs by navArgs()

    @Inject
    lateinit var appNavigator: AppNavigator

    private val todoViewModel: TodoDetailViewModel by viewModels()

    override fun getViewResource(): Int = R.layout.fragment_second

    override fun provideViewModel(): TodoDetailViewModel = todoViewModel

    override fun onStart() {
        super.onStart()
        button_second.clicksThrottled(lifecycleScope) {
            appNavigator.navigateFrom(AppScreens.Detail)
        }
        viewModel.loadDetails(args.todoId)
    }

    override fun render(viewState: TodoDetailViewState) {
        when (viewState) {
            TodoDetailViewState.Loading -> {
                textview_second.text = "Loading"
            }
            TodoDetailViewState.Init -> {
            }
            is TodoDetailViewState.Data -> {
                textview_second.text = viewState.details.title
            }
            TodoDetailViewState.Error -> {
                textview_second.text = "Todo item not found"
            }
        }.exhaustive
    }

}