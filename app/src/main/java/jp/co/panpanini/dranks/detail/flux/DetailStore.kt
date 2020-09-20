package jp.co.panpanini.dranks.detail.flux

import jp.co.panpanini.dranks.cocktail.Cocktail
import jp.co.panpanini.dranks.flux.Dispatcher
import jp.co.panpanini.dranks.flux.Store
import jp.co.panpanini.dranks.flux.ViewProperty
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class DetailStore(
    coroutineScope: CoroutineScope,
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main,
    dispatcher: Dispatcher<DetailAction>
) : Store<DetailAction>(coroutineScope, coroutineDispatcher, dispatcher) {

    val cocktail = ViewProperty.create<Cocktail>()

    override fun handleAction(action: DetailAction) = when (action) {
        is SetCocktail -> cocktail.value = action.cocktail
    }

}