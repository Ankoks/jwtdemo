FROM openjdk:8
ADD target/jwt_demo.jar jwt_demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "jwt_demo.jar"]