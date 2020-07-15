package sk.alex_katona.todo_app.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<ViewState, Action, PartialState> : ViewModel() {

    private val viewState = MutableLiveData<ViewState>()

    init {
        viewState.setValue(this.getInitialState())
    }

    abstract fun getInitialState(): ViewState

    fun getViewStateLiveData(): MutableLiveData<ViewState> {
        return this.viewState
    }

    protected fun emitPartialState(partialState: PartialState) {
        println("===Reducing=============================================")
        println("| previousState: ${viewState.value}")
        println("| partialState: $partialState")
        println("========================================================")
        val newState = reduce(viewState.value ?: getInitialState(), partialState)
        viewState.setValue(newState)
    }

    abstract fun emitAction(action: Action)

    abstract fun reduce(previousState: ViewState, partialState: PartialState): ViewState
}