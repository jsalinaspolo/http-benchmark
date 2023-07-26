package org.kotlin.community.http.benchmarks.ktor

import io.ktor.server.application.call
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.kotlin.community.http.benchmarks.HttpBenchmarkBase
import org.kotlin.community.http.benchmarks.benchmark

fun main(args: Array<String>) {
  benchmark(args) {
//    run<KtorJettyBenchmark>()
  }
}

//open class KtorJettyBenchmark : HttpBenchmarkBase() {
//  private lateinit var server: ApplicationEngine
//  override fun startServer(port: Int) {
//    server = embeddedServer(Jetty, port) {
//      routing {
//        get("/") {
//          call.respondText("Hello")
//        }
//      }
//    }
//    server.start()
//  }
//
//  override fun stopServer() {
//    server.stop(500, 5000)
//  }
//}
