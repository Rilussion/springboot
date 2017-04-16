FROM java:8u111-jdk-alpine

ADD build/libs/springboot-0.1.0.jar app.jar

RUN apk update && apk add vim\
            curl\
            wget

EXPOSE 8082
ARG buildno

ENTRYPOINT ["java", "-jar", "app.jar"]