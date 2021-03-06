package jp.co.panpanini.dranks.cocktail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.github.panpanini.cocktail.Cocktail
import com.github.panpanini.cocktail.Ingredient
import dev.chrisbanes.accompanist.coil.CoilImage
import jp.co.panpanini.dranks.R
import jp.co.panpanini.dranks.ui.LabelChip

@Composable
fun CocktailList(cocktailsLiveData: LiveData<List<Cocktail>>, onCocktailClicked: (Cocktail) -> Unit) {
    val cocktails by cocktailsLiveData.observeAsState(listOf())
    LazyColumn { items(cocktails) { CocktailRow(it, onCocktailClicked) } }
}

@Composable
fun CocktailRow(cocktail: Cocktail, onCocktailClicked: (Cocktail) -> Unit) {

    Row(modifier = Modifier.fillMaxWidth()
        .padding(8.dp)
        .clickable(onClick = { onCocktailClicked(cocktail) })
    ) {
        CoilImage(
            request = ImageRequest.Builder(ContextAmbient.current)
                .data(cocktail.thumbUrl)
                .size(250, 250)
                .transformations(CircleCropTransformation())
                .placeholder(R.drawable.ic_launcher_background)
                .build()
        )
        Text(cocktail.name, modifier = Modifier.align(Alignment.CenterVertically).padding(start = 8.dp))
    }
}

@Composable
fun CocktailHeader(cocktail: Cocktail) {
    ConstraintLayout {
        val (image, title) = createRefs()

        CoilImage(
            request = ImageRequest.Builder(ContextAmbient.current)
                .data(cocktail.thumbUrl)
                .build(),
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
            }
        )
        Text(
            cocktail.name,
            modifier = Modifier.constrainAs(title) {
                bottom.linkTo(image.bottom)
                centerHorizontallyTo(image)
                width = Dimension.fillToConstraints

            }.background(MaterialTheme.colors.background.copy(alpha = 0.8f)),
            style = MaterialTheme.typography.h2,
        )
    }
}

@ExperimentalLayout
@Composable
fun CocktailDetail(cocktail: Cocktail) {
    Column {
        ChipLayout(cocktail = cocktail)
        Ingredients(ingredients = cocktail.ingredients)
        Instructions(cocktail)
    }

}

@ExperimentalLayout
@Composable
fun ChipLayout(cocktail: Cocktail) {
    FlowRow {
        LabelChip(
            text = "Alcoholic",
            modifier = Modifier.padding(8.dp)
        )
        cocktail.glass?.let {
            LabelChip(
                text = it,
                modifier = Modifier.padding(8.dp)
            )
        }
        cocktail.category?.let {
            LabelChip(
                text = it,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun Ingredients(ingredients: List<Ingredient>) {
    Column {
        Text(
            text = "Ingredients:",
            style = MaterialTheme.typography.h5
        )
        ingredients.forEach { ingredient ->
            Text(
                text = "${ingredient.name}: ${ingredient.measure}",
                modifier = Modifier.padding(start = 16.dp, top = 8.dp)
            )
        }
    }
}

@Composable
fun Instructions(cocktail: Cocktail) {
    Column {
        Text(
            text = "Instructions:",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = cocktail.instructions ?: return,
            modifier = Modifier.padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 16.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun CocktailPreview() {
    val cocktail = Cocktail(
        1,
        "Neko",
        thumbUrl = "http://placekitten.com/200/200",
    )

    CocktailRow(cocktail) { }
}

@Preview(showBackground = true)
@Composable
fun CocktailHeaderPreview() {
    val cocktail = Cocktail(
        1,
        "Neko",
        thumbUrl = "http://placekitten.com/200/200",
    )
    CocktailHeader(cocktail = cocktail)
}

fun Cocktail(id: Int, name: String, thumbUrl: String): com.github.panpanini.cocktail.Cocktail {
    return com.github.panpanini.cocktail.Cocktail(
        id,
        name,
        thumbUrl = thumbUrl,
        alternativeName = null,
        tags = null,
        videoUrl = null,
        category = null,
        iba = null,
        alcoholic = true,
        glass = null,
        instructions = null,
        ingredients = listOf(),
        creativeCommons = false,
        dateModified = null
    )
}
