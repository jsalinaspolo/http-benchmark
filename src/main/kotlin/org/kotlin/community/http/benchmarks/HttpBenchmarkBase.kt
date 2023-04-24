package org.kotlin.community.http.benchmarks

import ch.qos.logback.classic.Level
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.TearDown
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@State(Scope.Benchmark)
abstract class HttpBenchmarkBase {
  private val httpClient = OkBenchmarkClient()

  private val port = 5678

  abstract fun startServer(port: Int)
  abstract fun stopServer()

  @Setup
  fun configureServer() {
    val root = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as ch.qos.logback.classic.Logger
    root.level = Level.ERROR
    startServer(port)
  }

  @TearDown
  fun shutdownServer() {
    stopServer()
  }

  @Setup
  fun configureClient() {
    httpClient.setup()
  }

  @TearDown
  fun shutdownClient() {
    httpClient.shutdown()
  }

  private fun load(url: String) {
    httpClient.load(url).use {
      val buf = ByteArray(8192)
      while (it.read(buf) != -1);
    }
  }

  @Benchmark
  fun hello() {
    load("http://localhost:$port/hello")
  }
}
