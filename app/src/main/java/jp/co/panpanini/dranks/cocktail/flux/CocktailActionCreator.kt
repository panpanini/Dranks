package jp.co.panpanini.dranks.cocktail.flux

import jp.co.panpanini.dranks.cocktail.CocktailApi
import jp.co.panpanini.dranks.cocktail.Drink
import jp.co.panpanini.dranks.flux.ActionCreator
import jp.co.panpanini.dranks.flux.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CocktailActionCreator(
    private val cocktailApi: CocktailApi,
    private val scope: CoroutineScope,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main,
    dispatcher: Dispatcher<CocktailAction>
) : ActionCreator<CocktailAction>(dispatcher) {

    fun searchCocktail(cocktail: String) {
        if (cocktail.isBlank()) return dispatcher.dispatch(NoCocktailsFound)

        scope.launch(coroutineDispatcher) {
            val cocktails = cocktailApi.search(cocktail)
                .drinks
                ?.map(Drink::toCocktail)

            dispatcher.dispatch(
                if (cocktails != null) UpdateCocktails(cocktails) else NoCocktailsFound
            )
        }

    }
}