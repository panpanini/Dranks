package jp.co.panpanini.dranks.cocktail.flux

import jp.co.panpanini.dranks.SearchService
import jp.co.panpanini.dranks.cocktail.CocktailApi
import jp.co.panpanini.dranks.flux.ActionCreator
import jp.co.panpanini.dranks.flux.Dispatcher
import jp.co.panpanini.dranks.network.Failure
import jp.co.panpanini.dranks.network.NetworkResponse
import jp.co.panpanini.dranks.network.ServerError
import jp.co.panpanini.dranks.network.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CocktailActionCreator(
    private val cocktailApi: CocktailApi,
    private val searchService: SearchService,
    private val scope: CoroutineScope,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main,
    dispatcher: Dispatcher<CocktailAction>
) : ActionCreator<CocktailAction>(dispatcher) {

    fun searchCocktail(cocktail: String) {
        if (cocktail.isBlank()) return dispatcher.dispatch(NoCocktailsFound)

        dispatcher.dispatch(ShowLoading(true))

        scope.launch(coroutineDispatcher) {
            val cocktails = when (val response = cocktailApi.search(cocktail)) {
                is Success -> response.data
                else -> return@launch handleError(response)
            }
            searchService.addRecentSearch(cocktail)
            dispatcher.dispatch(SetRecentSearchVisibility(false))
            dispatcher.dispatch(ShowLoading(false))
            dispatcher.dispatch(
                if (cocktails.isNotEmpty()) UpdateCocktails(cocktails) else NoCocktailsFound
            )
        }
    }

    fun fetchRecentSearches() {
        scope.launch(coroutineDispatcher) {
            searchService.getRecentSearches().collect {
                dispatcher.dispatch(UpdateRecentSearches(it))
                dispatcher.dispatch(SetRecentSearchVisibility(true))
            }
        }
    }

    private fun handleError(response: NetworkResponse<*>) {
        println(response)
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