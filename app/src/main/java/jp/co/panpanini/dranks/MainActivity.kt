package jp.co.panpanini.dranks

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.LiveData
import jp.co.panpanini.dranks.cocktail.flux.CocktailActionCreator
import jp.co.panpanini.dranks.cocktail.flux.CocktailFluxProvider
import jp.co.panpanini.dranks.cocktail.flux.CocktailStore
import jp.co.panpanini.dranks.cocktail.ui.CocktailList
import jp.co.panpanini.dranks.ui.DranksTheme
import jp.co.panpanini.dranks.ui.SearchBox
import kotlinx.serialization.json.Json
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val cocktailFluxProvider: CocktailFluxProvider by viewModel()

    private val cocktailActionCreator: CocktailActionCreator
        get() = cocktailFluxProvider.actionCreator

    private val cocktailStore: CocktailStore
        get() = cocktailFluxProvider.store

    private val json: Json by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DranksTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        DrinksAppBar()
                        val recentSearchVisibility = cocktailStore
                            .recentSearchVisibility
                            .liveData
                            .observeAsState(false)

                        SearchBox(
                            search = cocktailActionCreator::searchCocktail,
                            cocktailStore.recentSearches.liveData,
                            recentSearchVisibility.value,
                            cocktailActionCreator::fetchRecentSearches
                        )
                        Loading(cocktailStore.showLoading.liveData)
                        CocktailList(cocktailStore.cocktails.liveData) {
                            startActivity(
                                    DetailActivity.createIntent(this@MainActivity, it, json)
                            )
                        }
                    }
                }
            }
        }

        cocktailStore.noCocktailsFound.liveData.observe(this) { show ->
            if (show) {
                Toast.makeText(this, "No Cocktails found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun DrinksAppBar() {
    TopAppBar(
        title = { Text(text = "Dranks") }
    )
}


@Composable
fun Loading(loadingLiveData: LiveData<Boolean>) {
    val isLoading by loadingLiveData.observeAsState(false)
    if (isLoading) {
        Box(Modifier.fillMaxHeight().fillMaxWidth()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    DranksTheme {
        DrinksAppBar()
    }
}