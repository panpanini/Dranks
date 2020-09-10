package jp.co.panpanini.dranks.cocktail

import jp.co.panpanini.dranks.network.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {
    @GET("/api/json/v1/1/search.php")
    suspend fun search(@Query("s") name: String): NetworkResponse<DrinkResponse>
}