package jp.co.panpanini.dranks.flux

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Store contains the application state and logic.
 * Stores will register with the application's dispatcher so that they can receive actions.
 * The data in Store must only be mutated by responding to an action.
 */
abstract class Store<ActionType>(
    scope: CoroutineScope,
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main,
    dispatcher: Dispatcher<ActionType>
) {

    init {
        scope.launch(coroutineDispatcher) {
            dispatcher.observeDispatch()
                .collect { handleAction(it) }
        }
    }

    abstract fun handleAction(action: ActionType): Unit
}