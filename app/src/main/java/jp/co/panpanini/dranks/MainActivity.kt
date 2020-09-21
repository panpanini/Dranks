package jp.co.panpanini.dranks

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.Ref
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
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
                        CocktailList(cocktailStore.cocktails.liveData) {
                            startActivity(
                                    DetailActivity.createIntent(this@MainActivity, it)
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
fun SearchBox(search: (String) -> Unit) {
    val userNameState = remember { mutableStateOf("") }

    val closeKeyboardAndSearch = { searchTerm: String, controller: SoftwareKeyboardController? ->
        search(searchTerm)
        controller?.hideSoftwareKeyboard()
    }
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        val keyboardController: Ref<SoftwareKeyboardController> = remember { Ref() }
        OutlinedTextField(
            value = userNameState.value,
            onValueChange = userNameState::value::set,
            label = { Text(text = "Search:") },
            imeAction = ImeAction.Done,
            onImeActionPerformed = { action, softwareController ->
                if (action == ImeAction.Done) {
                    closeKeyboardAndSearch(userNameState.value, softwareController)
                }
            },
            onTextInputStarted = keyboardController::value::set,
            trailingIcon = {
                Button(
                    onClick = {
                        closeKeyboardAndSearch(userNameState.value, keyboardController.value)
                    },
                ) { Text(text = "Search") }
            },
            modifier = Modifier.background(MaterialTheme.colors.background)
        )
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