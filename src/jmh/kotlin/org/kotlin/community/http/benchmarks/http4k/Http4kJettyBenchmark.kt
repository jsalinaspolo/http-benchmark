package org.kotlin.community.http.benchmarks.http4k

import org.kotlin.community.http.benchmarks.benchmark

fun main(args: Array<String>) {
  benchmark(args) {
//    run<Http4kJettyBenchmark>()
  }
}

// open class Http4kJettyBenchmark : HttpBenchmarkBase() {
//  private lateinit var server: Http4kServer
//  override fun startServer(port: Int) {
//    server = { _: Request -> Response(OK).body("Hello") }.asServer(Jetty(port)).start()
//  }
//
//  override fun stopServer() {
//    server.stop()
//  }
// }
