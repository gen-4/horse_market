FROM eclipse-temurin:25-jre

RUN mkdir /horse_market
RUN mkdir /var/log/horse_market

WORKDIR /horse_market

COPY target/horse_market*.jar horse_market.jar

ENTRYPOINT ["java", "-jar", "horse_market.jar"]

