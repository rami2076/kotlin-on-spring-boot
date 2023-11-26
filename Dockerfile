FROM amazoncorretto:17 AS build
COPY ./ /home/app
RUN cd /home/app
RUN ./gradlew build -x runKtlintCheckOverMainSourceSet

FROM amazoncorretto:17-alpine
COPY --from=build /home/app/build/libs/kotlin-on-spring-boot-0.0.1.jar /usr/local/lib/spring-render-deploy.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dfile.encoding=UTF-8", "-Dspring.profiles.active=h2","/usr/local/lib/spring-render-deploy.jar"]
