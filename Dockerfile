FROM amazoncorretto:21.0.0-al2

#COPY ./target/http-benchmarks-0.1-SNAPSHOT-jar-with-dependencies.jar /opt/application/http-benchmarks.jar
COPY ./build/libs/http-benchmark-0.1-SNAPSHOT-standalone.jar /opt/application/http-benchmarks.jar

WORKDIR /opt/application

ENTRYPOINT ["java", "-jar", "http-benchmarks.jar"]
