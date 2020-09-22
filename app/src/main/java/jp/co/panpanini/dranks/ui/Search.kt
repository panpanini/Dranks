package jp.co.panpanini.dranks.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.Ref
import androidx.compose.ui.text.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

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
fun RecentSearches(searches: List<String>, onClick: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.background)) {
        searches.forEach {
            Text(
                text = it,
                modifier = Modifier.clickable(onClick = { onClick(it) })
                    .padding(16.dp),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBoxPreview() {
    SearchBox { }
}

@Preview(showBackground = true)
@Composable
fun RecentSearchesPreview() {
    val searches = listOf("Margarita", "Manhattan", "Martini")
    DranksTheme(darkTheme = true) {
        RecentSearches(searches = searches, onClick = { })
    }
}