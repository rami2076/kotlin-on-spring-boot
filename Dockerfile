FROM amazoncorretto:17 AS build
COPY ./ /home/app

RUN cd /home/app && \
    ls -ltr &&  \
    pwd && \
    chmod +x gradlew && \
    ./gradlew build -x runKtlintCheckOverMainSourceSet --stacktrace

FROM amazoncorretto:17-alpine
COPY --from=build build/libs/kotlin-on-spring-boot-0.0.1.jar /usr/local/lib/spring-render-deploy.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dfile.encoding=UTF-8", "-Dspring.profiles.active=h2","/usr/local/lib/spring-render-deploy.jar"]
