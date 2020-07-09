package sk.alex_katona.todo_app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import sk.alex_katona.todo_app.navigators.AppNavigator
import javax.inject.Inject

@AndroidEntryPoint()
open class BaseFragment : Fragment() {

    @Inject
    lateinit var appNavigator: AppNavigator

    // https://gist.github.com/mitchtabian/55cb572670aedf6f9045a392fdb68c63
    // currently its not supporting injections into abstract fragments
    open fun getLayoutResId(): Int {
        throw NotImplementedError("Implement this method and dont call super")
    }

    open fun init(view: View, savedInstanceState: Bundle?) {
        throw NotImplementedError("Implement this method and dont call super")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view, savedInstanceState)
    }
}