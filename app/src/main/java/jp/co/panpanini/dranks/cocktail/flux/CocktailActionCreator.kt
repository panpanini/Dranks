package jp.co.panpanini.dranks.cocktail.flux

import jp.co.panpanini.dranks.cocktail.CocktailApi
import jp.co.panpanini.dranks.cocktail.Drink
import jp.co.panpanini.dranks.flux.ActionCreator
import jp.co.panpanini.dranks.flux.Dispatcher
import jp.co.panpanini.dranks.network.Failure
import jp.co.panpanini.dranks.network.NetworkResponse
import jp.co.panpanini.dranks.network.ServerError
import jp.co.panpanini.dranks.network.Success
import kotlinx.coroutines.*
import java.lang.Exception

class CocktailActionCreator(
    private val cocktailApi: CocktailApi,
    private val scope: CoroutineScope,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main,
    dispatcher: Dispatcher<CocktailAction>
) : ActionCreator<CocktailAction>(dispatcher) {

    fun searchCocktail(cocktail: String) {
        if (cocktail.isBlank()) return dispatcher.dispatch(NoCocktailsFound)

        dispatcher.dispatch(ShowLoading(true))

        scope.launch(coroutineDispatcher) {
            val cocktails = when (val response = cocktailApi.search(cocktail)) {
                is Success -> response.data.drinks?.map(Drink::toCocktail)
                else -> return@launch handleError(response)
            }

            dispatcher.dispatch(ShowLoading(false))
            dispatcher.dispatch(
                if (cocktails != null) UpdateCocktails(cocktails) else NoCocktailsFound
            )
        }
    }

    private fun handleError(response: NetworkResponse<*>) {
        dispatcher.dispatch(ShowLoading(false))
        dispatcher.dispatch(
            when (response) {
                is ServerError -> NoCocktailsFound
                is Failure -> NoCocktailsFound
                is Success -> return // do nothing
            }
        )
    }
}