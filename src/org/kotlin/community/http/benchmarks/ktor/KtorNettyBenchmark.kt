package org.kotlin.community.http.benchmarks.ktor

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.kotlin.community.http.benchmarks.HttpBenchmarkBase
import org.kotlin.community.http.benchmarks.benchmark
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    benchmark(args) {
        run<KtorNettyBenchmark>()
    }
}

open class KtorNettyBenchmark : HttpBenchmarkBase() {
    private lateinit var server: ApplicationEngine
    override fun startServer(port: Int) {
        server = embeddedServer(Netty, port) {
            install(Routing) {
                get("/") {
                    call.respondText("Hello")
                }
            }
        }
        server.start()
    }

    override fun stopServer() {
        server.stop(500, 5000, TimeUnit.MILLISECONDS)
    }
}
