package jp.co.panpanini.dranks.detail.flux

import com.github.panpanini.cocktail.Cocktail
import jp.co.panpanini.dranks.cocktail.CocktailApi
import jp.co.panpanini.dranks.flux.ActionCreator
import jp.co.panpanini.dranks.flux.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class DetailActionCreator(
    private val cocktailApi: CocktailApi,
    private val scope: CoroutineScope,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main,
    dispatcher: Dispatcher<DetailAction>
) : ActionCreator<DetailAction>(dispatcher) {

    fun initCocktail(cocktail: Cocktail) {
        dispatcher.dispatch(SetCocktail(cocktail))
    }
}