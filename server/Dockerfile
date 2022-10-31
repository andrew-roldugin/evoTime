FROM openjdk:17-alpine3.14

WORKDIR /app

COPY ./pom.xml ./mvnw  ./
COPY ./.mvn .mvn
RUN ./mvnw dependency:resolve

COPY . .

CMD ./mvnw clean package -DskipTests

ENTRYPOINT ["java", "-jar", "./target/server-0.0.1.war"]

EXPOSE ${SPRING_APP_DOCKER_PORT}