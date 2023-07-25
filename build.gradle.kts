plugins {
  kotlin("jvm") version "1.8.20"
  id("idea")
  id("org.jlleitschuh.gradle.ktlint") version "11.5.0"
  id("me.champeau.jmh") version "0.7.1"

  application
}

application {
  mainClass.set("org.kotlin.community.http.benchmarks.BenchmarksKt")
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
  implementation("org.slf4j:slf4j-api:2.0.7")
  implementation("ch.qos.logback:logback-classic:1.4.8")
  implementation("org.slf4j:log4j-over-slf4j:2.0.7")
  implementation("org.eclipse.jetty:jetty-server:9.4.28.v20200408")
  implementation("org.eclipse.jetty:jetty-webapp:9.4.28.v20200408")
  implementation("org.eclipse.jetty.websocket:websocket-servlet:11.0.15")
  implementation("io.netty:netty-all:4.1.95.Final")
  implementation("io.netty:netty-buffer:4.1.95.Final")
  implementation("io.netty:netty-codec-socks:4.1.95.Final")
  implementation("io.netty:netty-resolver-dns:4.1.95.Final")
  implementation("io.netty:netty-handler-proxy:4.1.95.Final")
  implementation("io.ratpack:ratpack-core:1.9.0")
  implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.2")
  implementation("io.ktor:ktor-server-netty:1.3.1")
  implementation("io.ktor:ktor-server-jetty:1.3.1")
  implementation("io.vertx:vertx-web:4.4.4")
  implementation("com.typesafe.akka:akka-http_2.11:10.0.4")
  implementation("com.typesafe.akka:akka-actor_2.11:2.5.32")
  implementation("com.typesafe.akka:akka-agent_2.11:2.4.17")
  implementation(platform("org.http4k:http4k-bom:5.4.1.0"))
  implementation("org.http4k:http4k-core")
  implementation("org.http4k:http4k-server-netty")
  implementation("org.http4k:http4k-server-jetty")
  implementation("org.http4k:http4k-server-helidon")
  implementation("org.http4k:http4k-server-ratpack")
  implementation("io.micronaut:micronaut-runtime:3.9.0")
  implementation("io.micronaut:micronaut-http:1.3.4")
  implementation("io.micronaut:micronaut-http-server-netty:4.0.1")
  implementation("org.openjdk.jmh:jmh-core:1.36")
  implementation("org.openjdk.jmh:jmh-generator-annprocess:1.36")
  implementation("org.apache.httpcomponents:httpclient:4.5.14")
  implementation("com.squareup.okhttp3:okhttp:3.6.0")
}

group = "org.kotlin.community"
version = "0.1-SNAPSHOT"
description = "http-benchmark"
java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
}

tasks {
  val fatJar = register<Jar>("fatJar") {
    dependsOn.addAll(
      listOf(
        "compileJava",
        "compileKotlin",
        "processResources"
      )
    ) // We need this for Gradle optimization to work
    archiveClassifier.set("standalone") // Naming the jar
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
      attributes["Implementation-Title"] = project.name
      attributes["Implementation-Version"] = project.version
      attributes["Main-Class"] = "org.kotlin.community.http.benchmarks.BenchmarksKt"
    }

    val sourcesMain = sourceSets.main.get()
    val contents = configurations.runtimeClasspath.get()
      .map { if (it.isDirectory) it else zipTree(it) } +
      sourcesMain.output
    from(contents)
  }
  build {
    dependsOn(fatJar)
  }
}
