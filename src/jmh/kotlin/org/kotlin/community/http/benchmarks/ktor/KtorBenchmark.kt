package org.kotlin.community.http.benchmarks.ktor

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.ApplicationEngineFactory
import io.ktor.server.engine.embeddedServer
import org.kotlin.community.http.benchmarks.HttpBenchmarkBase
import org.kotlin.community.http.benchmarks.benchmark

fun main(args: Array<String>) {
  benchmark(args) {
    run<KtorJettyBenchmark>()
    run<KtorNettyBenchmark>()
  }
}

abstract class KtorBenchmark constructor(val factory: ApplicationEngineFactory<io.ktor.server.engine.ApplicationEngine, io.ktor.server.engine.ApplicationEngine.Configuration>) : HttpBenchmarkBase() {
  private lateinit var server: ApplicationEngine
  override fun startServer(port: Int) {
    server = embeddedServer(factory, port) {
      install(Routing) {
        get("/") {
          call.respondText("Hello")
        }
      }
    }
    server.start()
  }

  override fun stopServer() {
    server.stop(500, 5000)
  }
}
