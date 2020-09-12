package jp.co.panpanini.dranks

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.state
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.ui.tooling.preview.Preview
import jp.co.panpanini.dranks.cocktail.flux.CocktailActionCreator
import jp.co.panpanini.dranks.cocktail.flux.CocktailFluxProvider
import jp.co.panpanini.dranks.cocktail.flux.CocktailStore
import jp.co.panpanini.dranks.cocktail.ui.CocktailList
import jp.co.panpanini.dranks.ui.DranksTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val cocktailFluxProvider: CocktailFluxProvider by viewModel()

    private val cocktailActionCreator: CocktailActionCreator
        get() = cocktailFluxProvider.actionCreator

    private val cocktailStore: CocktailStore
        get() = cocktailFluxProvider.store

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DranksTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        DrinksAppBar()
                        SearchBox(search = cocktailActionCreator::searchCocktail)
                        Loading(cocktailStore.showLoading.liveData)
                        CocktailList(cocktailStore.cocktails.liveData)
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
fun SearchBox(search: (String) -> Unit) {
    Column() {
        val userNameState = state { "" }
        Surface(border = BorderStroke(1.dp, Color.Gray), modifier = Modifier.fillMaxWidth()) {
            Row {
                TextField(
                    value = userNameState.value,
                    onValueChange = { userNameState.value = it },
                    label = { Text(text = "Search:") },
                )
                Stack(Modifier.fillMaxWidth().gravity(Alignment.CenterVertically)) {
                    Button(
                        onClick = { search(userNameState.value) },
                        modifier = Modifier.gravity(Alignment.Center)
                    ) { Text(text = "Search") }
                }
            }
        }
    }
}

@Composable
fun Loading(loadingLiveData: LiveData<Boolean>) {
    val isLoading by loadingLiveData.observeAsState(false)
    if (isLoading) {
        Stack(Modifier.fillMaxHeight().fillMaxWidth()) {
            CircularProgressIndicator(Modifier.gravity(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBoxPreview() {
    SearchBox {

    }
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    DranksTheme {
        DrinksAppBar()
    }
}