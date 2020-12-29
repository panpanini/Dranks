package jp.co.panpanini.dranks.server

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val drankModule = module {
    single {
        HttpClient(OkHttp)
    }

    single {
        Json { ignoreUnknownKeys = true }
    }
}