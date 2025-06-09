plugins {
  kotlin("jvm") version "2.1.20"
  id("idea")
  id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
  id("me.champeau.jmh") version "0.7.3"

  application
}

application {
  mainClass = "org.kotlin.community.http.benchmarks.BenchmarksKt"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.20")
  implementation("org.slf4j:slf4j-api:2.0.17")
  implementation("ch.qos.logback:logback-classic:1.5.18")
  implementation("org.slf4j:log4j-over-slf4j:2.0.17")
  implementation("org.eclipse.jetty:jetty-server:12.0.20")
  implementation("org.eclipse.jetty:jetty-webapp:11.0.25")
  implementation("org.eclipse.jetty.websocket:websocket-servlet:11.0.25")
  implementation("io.netty:netty-all:4.2.0.Final")
  implementation("io.netty:netty-buffer:4.2.2.Final")
  implementation("io.netty:netty-codec-socks:4.2.0.Final")
  implementation("io.netty:netty-resolver-dns:4.2.0.Final")
  implementation("io.netty:netty-handler-proxy:4.2.0.Final")
  implementation("io.ratpack:ratpack-core:1.9.0")
  implementation("com.fasterxml.jackson.core:jackson-annotations:2.18.3")
  implementation("io.ktor:ktor-server-netty:3.1.2")
  implementation("io.ktor:ktor-server-jetty-jakarta:3.1.2")
  implementation("io.vertx:vertx-web:4.5.14")
  implementation(platform("org.http4k:http4k-bom:6.7.0.0"))
  implementation("org.http4k:http4k-core")
  implementation("org.http4k:http4k-server-netty")
  implementation("org.http4k:http4k-server-jetty")
  implementation("org.http4k:http4k-server-helidon")
  implementation("org.http4k:http4k-server-ratpack")
  implementation("io.micronaut:micronaut-runtime:4.8.13")
  implementation("io.micronaut:micronaut-http:4.8.13")
  implementation("io.micronaut:micronaut-http-server-netty:4.8.13")
  implementation("org.openjdk.jmh:jmh-core:1.37")
  implementation("org.openjdk.jmh:jmh-generator-annprocess:1.37")
  implementation("org.apache.httpcomponents:httpclient:4.5.14")
  implementation("com.squareup.okhttp3:okhttp:4.12.0")
  implementation(platform("com.linecorp.armeria:armeria-bom:1.32.5"))
  implementation("com.linecorp.armeria:armeria-kotlin")
}

group = "org.kotlin.community"
version = "0.1-SNAPSHOT"
description = "http-benchmark"

kotlin {
  jvmToolchain(21)
}

tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
}

tasks.withType<JavaCompile>().configureEach {
  options.compilerArgs.add("--enable-preview")
}

jmh {
  jvmArgs.add("--enable-preview")
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
