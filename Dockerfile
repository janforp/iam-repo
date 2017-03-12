FROM hub.c.163.com/library/java:8

RUN mkdir -p /opt/zbmatsu

RUN mkdir -p /opt/zbmatsu/log

ADD target/iam-repo-1.0.0.jar /opt/zbmatsu/app.jar

WORKDIR /opt/zbmatsu

EXPOSE 9000

CMD ["java", "-jar", "/opt/zbmatsu/app.jar"]
