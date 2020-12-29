package jp.co.panpanini.dranks.server

import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
//    val port = System.getenv("PORT")?.toInt() ?: 5000
    val port = 5000

    embeddedServer(
        Netty,
        port = port,
        module = Application::main,
        watchPaths = listOf("server")
    ).start(wait = true)
}