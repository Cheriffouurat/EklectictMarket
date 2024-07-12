FROM openjdk:17-alpine

ADD target/EklecticProject-*.jar /EklecticProject.jar

CMD ["java", "-jar", "/EklecticProject.jar"]