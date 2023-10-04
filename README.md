# kotlin-on-spring-boot

## Description

Sample of Kotlin on Spring Boot.

## Command

### Build

`gradlew build` is deprecate.
Instead of `gradlew clean build -x runKtlintCheckOverMainSourceSet -x test`.
Cause: `gradle-ktlint` has bug.
Issue: https://github.com/JLLeitschuh/ktlint-gradle/issues/579
Note:
To save time, test step excluded from the build task.  
If other tasks also fail related to `ktlint`, use the `-x` option to exclude them.

```shell
gradlew clean build -x runKtlintCheckOverMainSourceSet -x test
```

### Formatter

Set kotlin formatter for IntelliJ.

```shell
gradlew ktlintApplyToIdea
```

### Up Container

Init datasource.

```bash
docker-compose -f docker-compose.yaml -p kotlin-on-spring-boot up -d
```

### Check

If only `ktlintCheck` is used, it succeeds.

```shell
gradlew ktlintCheck
```

### Test

When not work test, then run build command.

```shell
gradlew test
```

### Down Container

```shell
docker-compose down 
```

## Swagger

### Local

[Swagger-UI](http://localhost:8080/rami/swagger-ui/index.html)
