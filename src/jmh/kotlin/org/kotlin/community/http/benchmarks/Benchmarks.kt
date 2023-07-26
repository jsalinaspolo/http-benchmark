package org.kotlin.community.http.benchmarks

import org.kotlin.community.http.benchmarks.armeria.ArmeriaBenchmark
import org.kotlin.community.http.benchmarks.http4k.Http4kHelidonBenchmark
import org.kotlin.community.http.benchmarks.http4k.Http4kNettyBenchmark
import org.kotlin.community.http.benchmarks.http4k.Http4kRatpackBenchmark
import org.kotlin.community.http.benchmarks.netty.NettyBenchmark
import org.kotlin.community.http.benchmarks.ratpack.RatpackBenchmark
import org.kotlin.community.http.benchmarks.vertx.VertxBenchmark

fun main(args: Array<String>) {
  benchmark(args) {
    iterations = 5
    iterationTime = 10_000
    setup()
  }

  benchmark(args) {
    profile("gc")
    iterations = 5
    iterationTime = 10_000
    setup()
  }
}

private fun BenchmarkSettings.setup() {
//  run<JettyBenchmark>()
//  run<Http4kJettyBenchmark>()
  run<Http4kNettyBenchmark>()
  run<Http4kHelidonBenchmark>()
  run<Http4kRatpackBenchmark>()
  run<VertxBenchmark>()
  run<NettyBenchmark>()
  run<RatpackBenchmark>()
//  run<KtorNettyBenchmark>()
//  run<KtorJettyBenchmark>()
  run<ArmeriaBenchmark>()
}
