package jp.co.panpanini.dranks.detail.flux

import androidx.lifecycle.viewModelScope
import jp.co.panpanini.dranks.cocktail.CocktailApi
import jp.co.panpanini.dranks.flux.Dispatcher
import jp.co.panpanini.dranks.flux.FluxProvider

class DetailFluxProvider(
    private val cocktailApi: CocktailApi
) : FluxProvider<DetailAction, DetailActionCreator, DetailStore>() {
    override fun createActionCreator(dispatcher: Dispatcher<DetailAction>) =
        DetailActionCreator(cocktailApi, viewModelScope, dispatcher = dispatcher)

    override fun createStore(dispatcher: Dispatcher<DetailAction>) =
        DetailStore(viewModelScope, dispatcher = dispatcher)
}