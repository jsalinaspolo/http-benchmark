package org.kotlin.community.http.benchmarks

import org.kotlin.community.http.benchmarks.http4k.Http4kJettyBenchmark
import org.kotlin.community.http.benchmarks.http4k.Http4kNettyBenchmark
import org.kotlin.community.http.benchmarks.ktor.KtorJettyBenchmark
import org.kotlin.community.http.benchmarks.ktor.KtorNettyBenchmark
import org.kotlin.community.http.benchmarks.ratpack.RatpackBenchmark

fun main(args: Array<String>) {
    benchmark(args) {
        iterations = 5
        iterationTime = 10_000
        setup()
    }

    benchmark(args) {
        profile("gc")
        iterations = 20
        iterationTime = 500
        setup()
    }
}

private fun BenchmarkSettings.setup() {
//    run<AkkaHttpBenchmark>()
//    run<JettyBenchmark>()
    run<Http4kJettyBenchmark>()
    run<Http4kNettyBenchmark>()
//    run<VertxBenchmark>()
//    run<NettyBenchmark>()

    run<RatpackBenchmark>()
    run<KtorNettyBenchmark>()
    run<KtorJettyBenchmark>()
}

