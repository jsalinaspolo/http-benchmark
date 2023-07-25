package org.kotlin.community.http.benchmarks.armeria

import com.linecorp.armeria.common.HttpResponse
import com.linecorp.armeria.server.Server
import org.kotlin.community.http.benchmarks.HttpBenchmarkBase
import org.kotlin.community.http.benchmarks.benchmark

fun main(args: Array<String>) {
  benchmark(args) {
    run<ArmeriaBenchmark>()
  }
}

open class ArmeriaBenchmark : HttpBenchmarkBase() {
  private lateinit var server: Server
  override fun startServer(port: Int) {
    server = Server.builder()
      .port(port)
      .service("/") { ctx, req -> HttpResponse.of("Hello") }
      .build()

    server.start()
  }

  override fun stopServer() {
    server.stop()
  }
}
