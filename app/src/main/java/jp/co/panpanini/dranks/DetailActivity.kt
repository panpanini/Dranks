package jp.co.panpanini.dranks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.platform.setContent
import jp.co.panpanini.dranks.cocktail.Cocktail
import jp.co.panpanini.dranks.cocktail.ui.CocktailDetail
import jp.co.panpanini.dranks.cocktail.ui.CocktailHeader
import jp.co.panpanini.dranks.detail.flux.DetailActionCreator
import jp.co.panpanini.dranks.detail.flux.DetailFluxProvider
import jp.co.panpanini.dranks.detail.flux.DetailStore
import jp.co.panpanini.dranks.ui.DranksTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val fluxProvider: DetailFluxProvider by viewModel()

    private val detailActionCreator: DetailActionCreator
        get() = fluxProvider.actionCreator

    private val detailStore: DetailStore
        get() = fluxProvider.store

    private val cocktail: Cocktail
        get() = intent.getSerializableExtra(KEY_COCKTAIL) as Cocktail

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

        fun createIntent(context: Context, cocktail: Cocktail): Intent {
            return Intent(context, DetailActivity::class.java)
                    .putExtra(KEY_COCKTAIL, cocktail)
        }
    }

}