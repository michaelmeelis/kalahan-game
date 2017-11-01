FROM java:8-jre-alpine
EXPOSE 8080
CMD java -jar /app/app.jar

COPY target/bol-kalaha-game-1.0.jar /app/app.jar
