package jp.co.panpanini.dranks.cocktail

import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {
    @GET("/api/json/v1/1/search.php")
    suspend fun search(@Query("s") name: String): DrinkResponse
}