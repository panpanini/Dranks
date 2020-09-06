package jp.co.panpanini.dranks

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import jp.co.panpanini.dranks.cocktail.CocktailApi
import jp.co.panpanini.dranks.cocktail.flux.CocktailFluxProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com")
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single {
        get<Retrofit>().create(CocktailApi::class.java)
    }

    viewModel {
        CocktailFluxProvider(get())
    }
}