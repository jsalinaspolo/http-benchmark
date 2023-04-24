package org.kotlin.community.http.benchmarks.ratpack

import org.kotlin.community.http.benchmarks.HttpBenchmarkBase
import org.kotlin.community.http.benchmarks.benchmark
import ratpack.http.internal.HttpHeaderConstants.PLAIN_TEXT_UTF8
import ratpack.server.RatpackServer

fun main(args: Array<String>) {
    benchmark(args) {
        run<RatpackBenchmark>()
    }
}

open class RatpackBenchmark : HttpBenchmarkBase() {
    private lateinit var server: RatpackServer
    override fun startServer(port: Int) {
        server = RatpackServer.start { serverSpec ->
            serverSpec
                    .serverConfig { config -> config.port(port) }
                    .handlers { chain -> chain.get { it.response.send(PLAIN_TEXT_UTF8, "Hello") } }
        }
    }

    override fun stopServer() {
        server.stop()
    }
}
