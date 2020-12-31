package jp.co.panpanini.dranks

import androidx.datastore.preferences.createDataStore
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import jp.co.panpanini.dranks.cocktail.CocktailApi
import jp.co.panpanini.dranks.cocktail.flux.CocktailFluxProvider
import jp.co.panpanini.dranks.detail.flux.DetailFluxProvider
import jp.co.panpanini.dranks.network.NetworkResponseAdapterFactory
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidApplication
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
            .baseUrl("http://10.0.2.2:5000")
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single {
        get<Retrofit>().create(CocktailApi::class.java)
    }

    single {
        androidApplication().createDataStore(name = "search")
    }

    single {
        Json { ignoreUnknownKeys = true }
    }

    single {
        SearchService(get())
    }

    viewModel {
        CocktailFluxProvider(get(), get())
    }

    viewModel {
        DetailFluxProvider(get())
    }
}