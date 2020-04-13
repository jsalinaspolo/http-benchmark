FROM adoptopenjdk/openjdk8:jre8u232-b09-alpine

LABEL maintainer="javier.salinas@zopa.com"

COPY ./target/http-benchmarks-0.1-SNAPSHOT-jar-with-dependencies.jar /opt/application/http-benchmarks.jar

WORKDIR /opt/application

ENTRYPOINT ["java", "-jar", "http-benchmarks.jar"]
