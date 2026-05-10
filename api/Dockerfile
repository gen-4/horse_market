FROM eclipse-temurin:25-jre

RUN mkdir /horse_exchange
RUN mkdir /var/log/horse_exchange

WORKDIR /horse_exchange

COPY target/horse_exchange*.jar horse_exchange.jar

ENTRYPOINT ["java", "-jar", "horse_exchange.jar"]

