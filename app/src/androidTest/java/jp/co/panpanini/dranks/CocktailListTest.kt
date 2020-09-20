package jp.co.panpanini.dranks

import androidx.ui.test.assertCountEquals
import androidx.ui.test.createComposeRule
import androidx.ui.test.onAllNodesWithText
import jp.co.panpanini.dranks.cocktail.Cocktail
import jp.co.panpanini.dranks.cocktail.ui.CocktailRow
import org.junit.Rule
import org.junit.Test

class CocktailListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun cocktail_row_should_display_title() {
        val cocktail = Cocktail(1, "Neko", null, null, null, null, null, true, null, null, "http://placekitten.com/200/200", listOf(), true, null)

        composeTestRule.setContent {
            CocktailRow(cocktail) { }
        }

        onAllNodesWithText("Neko").assertCountEquals(1)
    }
}