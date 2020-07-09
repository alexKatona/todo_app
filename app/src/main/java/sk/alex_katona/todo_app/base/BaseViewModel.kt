package sk.alex_katona.todo_app.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<ViewState, Action, PartialState> : ViewModel() {
    private val viewState = MutableLiveData<ViewState>()

    init {
        viewState.postValue(this.getInitialState())
    }

    abstract fun getInitialState(): ViewState

    fun getViewStateLiveData(): MutableLiveData<ViewState> {
        return this.viewState
    }

    protected fun emitPartialState(partialState: PartialState) {
        viewState.postValue(reduce(viewState.value ?: getInitialState(), partialState))
    }

    abstract fun postAction(action: Action)

    abstract fun reduce(previousState: ViewState, partialState: PartialState): ViewState
}