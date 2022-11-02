FROM openjdk:17-alpine3.14

WORKDIR /app

COPY ./.mvn .mvn
COPY ./pom.xml ./mvnw  ./
RUN ./mvnw dependency:resolve

COPY . .

CMD ./mvnw clean package -DskipTests

ENTRYPOINT ["java", "-jar", "./target/server-0.0.1.war"]

EXPOSE 8888