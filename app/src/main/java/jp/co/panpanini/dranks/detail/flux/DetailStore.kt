package jp.co.panpanini.dranks.detail.flux

import jp.co.panpanini.dranks.flux.Dispatcher
import jp.co.panpanini.dranks.flux.Store
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class DetailStore(
    coroutineScope: CoroutineScope,
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main,
    dispatcher: Dispatcher<DetailAction>
) : Store<DetailAction>(coroutineScope, coroutineDispatcher, dispatcher) {
    override fun handleAction(action: DetailAction) = when (action) {

        else -> {}
    }

}