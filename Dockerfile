FROM java:8

LABEL maintainer="hjhearts@gmail.com"

VOLUME /tmp

ARG JAR_FILE=build/libs/JCompany-1.0-SNAPSHOT.jar

ADD ${JAR_FILE} jcompany-springboot.jar

ENTRYPOINT ["java", "-jar", "/jcompany-springboot.jar"]