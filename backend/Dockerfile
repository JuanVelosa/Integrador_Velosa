# build the project with maven
# TODO: Use mvn wrapper
FROM maven:3-amazoncorretto-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src/ ./src

RUN mvn clean package -DskipTests

# run it with java 21
# TODO: use distroless gcr.io/distroless/java17-debian12
FROM amazoncorretto:21-alpine AS run

RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# least privilege principle
RUN chown -R javauser:javauser .

USER javauser

CMD ["java", "-jar", "app.jar"]
