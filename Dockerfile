FROM openjdk:11-jre-slim
ADD ./target/apartment-notifications-0.0.1-SNAPSHOT.jar apartment-notfication.jar

EXPOSE 8443

CMD java -jar apartment-notfication.jar
RUN echo "done"
