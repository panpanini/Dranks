package jp.co.panpanini.dranks.server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.get

fun Application.main() {
    install(DefaultHeaders)
    install(Locations)
    install(CallLogging)
    install(CORS) {
        method(HttpMethod.Get)
        header(HttpHeaders.XForwardedProto)
        anyHost()
        host("localhost")
        // host("my-host:80")
        // host("my-host", subDomains = listOf("www"))
        // host("my-host", schemes = listOf("http", "https"))
        allowCredentials = true
        allowNonSimpleContentTypes = true
    }
    install(Koin) {
        modules(drankModule)
    }
    install(StatusPages) {
        exception<Throwable> {
            call.respond(status = HttpStatusCode.InternalServerError, message = it.stackTraceToString() ?: "")
        }
        status(HttpStatusCode.NotFound) {
            call.respond(status = HttpStatusCode.NotFound, message = "oof")
        }
    }

    routing {
        get("/helloworld") {
            call.respond("Hello, World!")
        }
        cocktailApi(get(), get())
    }
}