package jp.co.panpanini.dranks.flux

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class Dispatcher<ActionType>(
    private val coroutineScope: CoroutineScope,
    private val coroutineDispatcher : CoroutineDispatcher = Dispatchers.Default
) {

    private val dispatcher = Channel<ActionType>()

    fun dispatch(action: ActionType) {
        coroutineScope.launch(coroutineDispatcher) {
            dispatcher.send(action)
        }
    }

    fun observeDispatch() = dispatcher.receiveAsFlow()

}