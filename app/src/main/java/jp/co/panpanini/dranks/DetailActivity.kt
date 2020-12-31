package jp.co.panpanini.dranks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import com.github.panpanini.cocktail.Cocktail
import jp.co.panpanini.dranks.cocktail.ui.CocktailDetail
import jp.co.panpanini.dranks.cocktail.ui.CocktailHeader
import jp.co.panpanini.dranks.detail.flux.DetailActionCreator
import jp.co.panpanini.dranks.detail.flux.DetailFluxProvider
import jp.co.panpanini.dranks.detail.flux.DetailStore
import jp.co.panpanini.dranks.ui.DranksTheme
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val fluxProvider: DetailFluxProvider by viewModel()

    private val json: Json by inject()

    private val detailActionCreator: DetailActionCreator
        get() = fluxProvider.actionCreator

    private val detailStore: DetailStore
        get() = fluxProvider.store

    private val cocktail: Cocktail
        get() = json.decodeFromString(serializer(), intent.getStringExtra(KEY_COCKTAIL)!!)

    @OptIn(ExperimentalLayout::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DranksTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        DrinksAppBar()
                        ScrollableColumn {
                            val cocktail = detailStore.cocktail.liveData.observeAsState()
                            cocktail.value?.let {
                                CocktailHeader(cocktail = it)
                                CocktailDetail(cocktail = it)
                            }
                        }
                    }

                }
            }
        }

        detailActionCreator.initCocktail(cocktail)
    }

    companion object {
        private const val KEY_COCKTAIL = "cocktail"

        fun createIntent(context: Context, cocktail: Cocktail, json: Json): Intent {
            return Intent(context, DetailActivity::class.java)
                    .putExtra(KEY_COCKTAIL, json.encodeToString(serializer(), cocktail))
        }
    }

}