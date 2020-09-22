package jp.co.panpanini.dranks.cocktail.flux

import androidx.lifecycle.viewModelScope
import jp.co.panpanini.dranks.SearchService
import jp.co.panpanini.dranks.cocktail.CocktailApi
import jp.co.panpanini.dranks.flux.Dispatcher
import jp.co.panpanini.dranks.flux.FluxProvider

class CocktailFluxProvider(
    private val cocktailApi: CocktailApi,
    private val searchService: SearchService
) : FluxProvider<CocktailAction, CocktailActionCreator, CocktailStore>() {
    override fun createActionCreator(dispatcher: Dispatcher<CocktailAction>): CocktailActionCreator {
        return CocktailActionCreator(cocktailApi, searchService, viewModelScope, dispatcher = dispatcher)
    }

    override fun createStore(dispatcher: Dispatcher<CocktailAction>): CocktailStore {
        return CocktailStore(viewModelScope, dispatcher = dispatcher)
    }

}