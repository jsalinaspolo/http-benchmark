FROM amazoncorretto:21.0.6-al2

COPY ./build/libs/http-benchmark-0.1-SNAPSHOT-standalone.jar /opt/application/http-benchmarks.jar

WORKDIR /opt/application

ENTRYPOINT ["java", "-jar", "http-benchmarks.jar"]
