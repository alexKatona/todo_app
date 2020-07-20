package sk.alex_katona.todo_app.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<ViewState, Action, PartialState> : ViewModel() {

    private val viewState = MutableLiveData<ViewState>()

    init {
        viewState.postValue(this.getInitialState())
    }

    abstract fun getInitialState(): ViewState

    fun getViewStateLiveData(): LiveData<ViewState> {
        return this.viewState
    }

    @Synchronized
    protected fun emitPartialState(partialState: PartialState) {
        println("===emitPartialState=============================================")
        println("| previousState: ${viewState.value}")
        println("| partialState: $partialState")
        println("========================================================")
        val newState = reduce(viewState.value ?: getInitialState(), partialState)
        viewState.postValue(newState)
    }

     open fun emitAction(action: Action){
         println("$action")
     }

     abstract fun reduce(previousState: ViewState, partialState: PartialState): ViewState
}