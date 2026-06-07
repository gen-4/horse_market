FROM eclipse-temurin:25-jdk AS builder

RUN apt-get update -y && apt-get install -y maven

WORKDIR /app

COPY . .

RUN mvn clean package


FROM eclipse-temurin:25-jre

RUN mkdir /horse_market
RUN mkdir /var/log/horse_market

WORKDIR /horse_market

COPY --from=builder /app/target/horse_market*.jar ./horse_market.jar

ENTRYPOINT ["java", "-jar", "horse_market.jar"]

