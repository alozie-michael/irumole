FROM openjdk:8
EXPOSE 8081
COPY ./target/irumole-0.0.1-SNAPSHOT.jar irumole-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh","-c","java ${JAVA_OPTIONS} -jar irumole-0.0.1-SNAPSHOT.jar"]