package jp.co.panpanini.dranks.cocktail.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.ui.tooling.preview.Preview
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import jp.co.panpanini.dranks.R
import jp.co.panpanini.dranks.cocktail.Cocktail

@Composable
fun CocktailList(cocktailsLiveData: LiveData<List<Cocktail>>) {
    val cocktails by cocktailsLiveData.observeAsState(listOf())
    LazyColumnFor(items = cocktails) { CocktailRow(it) }
}

@Composable
fun CocktailRow(cocktail: Cocktail) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        CoilImageWithCrossfade(
            request = ImageRequest.Builder(ContextAmbient.current)
                .data(cocktail.thumbUrl)
                .size(250, 250)
                .transformations(CircleCropTransformation())
                .placeholder(R.drawable.ic_launcher_background)
                .build()
        )
        Text(cocktail.name, modifier = Modifier.gravity(Alignment.CenterVertically).padding(start = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun CocktailPreview() {
    val cocktail = Cocktail(
        1,
        "Neko",
        null,
        null,
        null,
        null,
        null,
        true,
        null,
        null,
        "http://placekitten.com/200/200",
        listOf(),
        true,
        null
    )

    CocktailRow(cocktail)
}