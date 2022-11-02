FROM openjdk:17-alpine3.14

WORKDIR /app

COPY ./.mvn .mvn
COPY ./pom.xml ./mvnw  ./
RUN ./mvnw dependency:resolve

COPY . .

#CMD ./mvnw clean package -DskipTests
#CMD echo "Сборка приложения..."
#CMD ["./mvnw", "clean", "package", "-DskipTests"]

CMD echo "Expose port = " $SPRING_APP_DOCKER_PORT
EXPOSE $SPRING_APP_DOCKER_PORT
#ENTRYPOINT ["java", "-jar", "./target/server-0.0.1.war"]
CMD ./mvnw spring-boot:run