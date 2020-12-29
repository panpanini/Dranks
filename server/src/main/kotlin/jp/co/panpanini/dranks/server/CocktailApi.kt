package jp.co.panpanini.dranks.server

import com.github.panpanini.drink.Drink
import com.github.panpanini.drink.DrinkResponse
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer


@Location("/cocktail/search")
data class CocktailSearch(val name: String)

@Location("/cocktail/{id}")
data class Cocktail(val id: Int)

fun Routing.cocktailApi(client: HttpClient, json: Json) {
    get<CocktailSearch> { cocktail ->
        val response: String = client.get(
            "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=${cocktail.name}"
        )

        val drinkResponse: DrinkResponse = json.decodeFromString(serializer(), response)
        println(drinkResponse)
        call.respond(
            json.encodeToString(
                serializer(),
                drinkResponse.drinks?.map(Drink::toCocktail) ?: emptyList()
            )
        )
    }

    get<Cocktail> { cocktail ->
        val response: String = client.get(
            "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=${cocktail.id}"
        )

        val drinkResponse: DrinkResponse = json.decodeFromString(serializer(), response)

        call.respond(
            json.encodeToString(
                serializer(),
                drinkResponse.drinks?.first()?.toCocktail() ?: return@get
            )
        )
    }
}