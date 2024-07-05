FROM java:8

LABEL maintainer="cube-server"
WORKDIR /opt/app-service/cube-server/

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

EXPOSE 8080

ENTRYPOINT [ "java","-jar","-Duser.timezone=GMT+08","cube-server.jar" ]