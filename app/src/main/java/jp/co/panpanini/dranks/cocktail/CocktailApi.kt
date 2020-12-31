package jp.co.panpanini.dranks.cocktail

import com.github.panpanini.cocktail.Cocktail
import jp.co.panpanini.dranks.network.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {
    @GET("/cocktail/search")
    suspend fun search(@Query("name") name: String): NetworkResponse<List<Cocktail>>
}