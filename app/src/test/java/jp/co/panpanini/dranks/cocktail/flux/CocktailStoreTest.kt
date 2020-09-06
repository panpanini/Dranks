package jp.co.panpanini.dranks.cocktail.flux

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import jp.co.panpanini.dranks.cocktail.Cocktail
import jp.co.panpanini.dranks.flux.Dispatcher
import jp.co.panpanini.dranks.getOrAwaitValue
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CocktailStoreTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val coroutineScope = TestCoroutineScope()

    private val coroutineDispatcher = TestCoroutineDispatcher()

    private val dispatcher: Dispatcher<CocktailAction> = mock()

    private lateinit var target: CocktailStore

    @Before
    fun setUp() {
        target = CocktailStore(coroutineScope, coroutineDispatcher, dispatcher)
    }

    @Test
    fun `UpdateCocktails action should update cocktails`() {
        val cocktail: Cocktail = mock()
        val action = UpdateCocktails(listOf(cocktail))

        target.handleAction(action)

        val result = target.cocktails.liveData.getOrAwaitValue()

        assertThat(result).isEqualTo(listOf(cocktail))
    }

    @Test
    fun `NoCocktailsFound should set noCocktailsFound to true`() {
        val action = NoCocktailsFound

        target.handleAction(action)

        val result = target.noCocktailsFound.liveData.getOrAwaitValue()

        assertThat(result).isTrue
    }
}