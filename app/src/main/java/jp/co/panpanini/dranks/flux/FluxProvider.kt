package jp.co.panpanini.dranks.flux

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

abstract class FluxProvider<ActionType, FlowActionCreator, FlowStore> : ViewModel() {

    private val dispatcher: Dispatcher<ActionType> = Dispatcher(viewModelScope)

    val actionCreator: FlowActionCreator by lazy {
        // Initialize Store before ActionCreator to correctly dispatch value from ActionCreator.
        store
        return@lazy createActionCreator(dispatcher)
    }

    val store: FlowStore by lazy {
        createStore(dispatcher)
    }

    protected abstract fun createActionCreator(
        dispatcher: Dispatcher<ActionType>
    ): FlowActionCreator
    protected abstract fun createStore(dispatcher: Dispatcher<ActionType>): FlowStore

}