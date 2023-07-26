package org.kotlin.community.http.benchmarks.ktor

import io.ktor.server.routing.get
import org.kotlin.community.http.benchmarks.benchmark

fun main(args: Array<String>) {
  benchmark(args) {
//    run<KtorNettyBenchmark>()
  }
}

// open class KtorNettyBenchmark : HttpBenchmarkBase() {
//  private lateinit var server: ApplicationEngine
//  override fun startServer(port: Int) {
//    server = embeddedServer(Netty, port) {
//      routing {
//        get("/") {
//          call.respondText("Hello")
//        }
//      }
//    }
//    server.start(wait = true)
//  }
//
//  override fun stopServer() {
//    server.stop(500, 5000)
//  }
// }
