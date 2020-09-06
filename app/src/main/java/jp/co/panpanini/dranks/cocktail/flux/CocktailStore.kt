package jp.co.panpanini.dranks.cocktail.flux

import jp.co.panpanini.dranks.cocktail.Cocktail
import jp.co.panpanini.dranks.flux.Dispatcher
import jp.co.panpanini.dranks.flux.SignalProperty
import jp.co.panpanini.dranks.flux.Store
import jp.co.panpanini.dranks.flux.ViewProperty
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class CocktailStore(
    coroutineScope: CoroutineScope,
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main,
    dispatcher: Dispatcher<CocktailAction>
) : Store<CocktailAction>(coroutineScope, coroutineDispatcher, dispatcher) {

    val cocktails = ViewProperty.create<List<Cocktail>>()

    val noCocktailsFound = SignalProperty.create<Boolean>()

    override fun handleAction(action: CocktailAction) = when (action) {
        is UpdateCocktails -> cocktails.value = action.cocktails
        NoCocktailsFound -> noCocktailsFound.set(true)
    }

}