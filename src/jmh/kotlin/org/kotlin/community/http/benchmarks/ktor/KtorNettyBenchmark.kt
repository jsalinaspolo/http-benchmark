package org.kotlin.community.http.benchmarks.ktor

import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.EmbeddedServer
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.kotlin.community.http.benchmarks.HttpBenchmarkBase
import org.kotlin.community.http.benchmarks.benchmark

fun main(args: Array<String>) {
  benchmark(args) {
    run<KtorNettyBenchmark>()
  }
}

open class KtorNettyBenchmark : HttpBenchmarkBase() {
  private lateinit var server: EmbeddedServer<ApplicationEngine, *>
  override fun startServer(port: Int) {
    server = embeddedServer(Netty, port = port) {
      routing {
        get("/") {
          call.respondText("Hello")
        }
      }
    }
    server.start(wait = true)
  }

  override fun stopServer() {
    server.stop(500, 5000)
  }
}
