plugins {
  kotlin("jvm") version "1.8.20"
  id("idea")
  id("org.jlleitschuh.gradle.ktlint") version "11.3.2"
  application
}

application {
  mainClass.set("org.kotlin.community.http.benchmarks.BenchmarksKt")
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.20")
  implementation("org.slf4j:slf4j-api:2.0.7")
  implementation("ch.qos.logback:logback-classic:1.4.7")
  implementation("org.slf4j:log4j-over-slf4j:2.0.7")
  implementation("org.eclipse.jetty:jetty-server:9.4.28.v20200408")
  implementation("org.eclipse.jetty:jetty-webapp:9.4.28.v20200408")
  implementation("org.eclipse.jetty.websocket:websocket-servlet:9.4.28.v20200408")
  implementation("io.netty:netty-all:4.1.91.Final")
  implementation("io.netty:netty-buffer:4.1.91.Final")
  implementation("io.netty:netty-codec-socks:4.1.91.Final")
  implementation("io.netty:netty-resolver-dns:4.1.91.Final")
  implementation("io.netty:netty-handler-proxy:4.1.91.Final")
  implementation("io.ratpack:ratpack-core:1.7.6")
  implementation("com.fasterxml.jackson.core:jackson-annotations:2.10.3")
  implementation("io.ktor:ktor-server-netty:1.3.1")
  implementation("io.ktor:ktor-server-jetty:1.3.1")
  implementation("io.vertx:vertx-web:3.9.0")
  implementation("com.typesafe.akka:akka-http_2.11:10.0.4")
  implementation("com.typesafe.akka:akka-actor_2.11:2.4.17")
  implementation("com.typesafe.akka:akka-agent_2.11:2.4.17")
  implementation("org.http4k:http4k-core:3.242.0")
  implementation("org.http4k:http4k-server-netty:3.242.0")
  implementation("org.http4k:http4k-server-jetty:3.242.0")
  implementation("io.micronaut:micronaut-runtime:3.9.0")
  implementation("io.micronaut:micronaut-http:1.3.4")
  implementation("io.micronaut:micronaut-http-server-netty:1.3.4")
  implementation("org.openjdk.jmh:jmh-core:1.36")
  implementation("org.openjdk.jmh:jmh-generator-annprocess:1.23")
  implementation("org.apache.httpcomponents:httpclient:4.5.14")
  implementation("com.squareup.okhttp3:okhttp:3.6.0")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.3.71")
  testImplementation("junit:junit:4.12")
}

group = "org.kotlin.community"
version = "0.1-SNAPSHOT"
description = "http-benchmark"
java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
}

tasks.withType<Jar> {
  manifest {
    attributes["Implementation-Title"] = project.name
    attributes["Implementation-Version"] = project.version
    attributes["Main-Class"] = "org.kotlin.community.http.benchmarks.BenchmarksKt"
  }

//  from(configurations.runtimeClasspath.get()
//    .onEach { println("add from dependencies: ${it.name}") }
//    .map { if (it.isDirectory) it else zipTree(it) })
//  val sourcesMain = sourceSets.main.get()
//  sourcesMain.allSource.forEach { println("add from sources: ${it.name}") }
//  from(sourcesMain.output)
}
