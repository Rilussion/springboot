FROM java:8u111-jdk-alpine

RUN apk update && apk add vim\
            curl\
            wget

RUN mkdir -p /tmp/mappings /tmp/__files \
    && wget --directory-prefix /tmp http://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-standalone/2.6.0/wiremock-standalone-2.6.0.jar

COPY mappings/ /tmp/mappings
COPY __files/ /tmp/__files


EXPOSE 8092

WORKDIR /tmp

ENTRYPOINT ["java", "-jar", "wiremock-standalone-2.6.0.jar", "--port", "8092"]