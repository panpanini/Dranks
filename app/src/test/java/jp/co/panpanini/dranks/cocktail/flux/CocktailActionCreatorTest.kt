package jp.co.panpanini.dranks.cocktail.flux

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import jp.co.panpanini.dranks.cocktail.Cocktail
import jp.co.panpanini.dranks.cocktail.CocktailApi
import jp.co.panpanini.dranks.cocktail.Drink
import jp.co.panpanini.dranks.cocktail.DrinkResponse
import jp.co.panpanini.dranks.flux.Dispatcher
import jp.co.panpanini.dranks.network.Success
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class CocktailActionCreatorTest {

    private val cocktailApi: CocktailApi = mock()

    private val coroutineScope = TestCoroutineScope()

    private val coroutineDispatcher = TestCoroutineDispatcher()

    private val dispatcher: Dispatcher<CocktailAction> = mock()

    lateinit var target: CocktailActionCreator

    @Before
    fun setUp() {
        target = CocktailActionCreator(cocktailApi, coroutineScope, coroutineDispatcher, dispatcher)
    }

    @Test
    fun `searchCocktail should dispatch NoCocktailsFound for empty input`() = runBlockingTest {
        val cocktail = ""
        target.searchCocktail(cocktail)

        verify(dispatcher).dispatch(NoCocktailsFound)
    }

    @Test
    fun `searchCocktail should dispatch UpdateCocktails with the returned values`() = runBlockingTest {
        val searchTerm = "neko"
        val expected: Cocktail = mock()
        val drink: Drink = mock {
            whenever(it.toCocktail()).thenReturn(expected)
        }
        whenever(cocktailApi.search(searchTerm)).thenReturn(
            Success(DrinkResponse(listOf(drink)))
        )

        target.searchCocktail(searchTerm)

        verify(dispatcher).dispatch(UpdateCocktails(listOf(expected)))
    }

    @Test
    fun `searchCocktail should dispatch NoCocktailsFound if API returns an empty result`() = runBlockingTest {
        val searchTerm = "hoge"
        whenever(cocktailApi.search(searchTerm)).thenReturn(Success(DrinkResponse(null)))

        target.searchCocktail(searchTerm)

        verify(dispatcher).dispatch(NoCocktailsFound)
    }

}