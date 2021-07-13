FROM registry.cosmoplat.com/cosmoplat/openjdk:8-jdk-alpine
MAINTAINER deltaqin delta_qin@163.com
#RUN mkdir -p /deltaqin/auth
#WORKDIR /deltaqin/auth

ENV TIME_ZONE Asia/Shanghai
RUN echo "$TIME_ZONE" > /etc/timezone

ARG JAR_FILE
ADD ${JAR_FILE} user.jar

EXPOSE 8001

CMD ["java","-jar","user.jar"]


#COPY ./target/febs-admin-2.2-RELEASE.jar /febs/febs-admin-2.2-RELEASE.jar
