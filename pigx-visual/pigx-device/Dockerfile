FROM pig4cloud/java:8-jre

MAINTAINER lz(lzj_0518@163.com)

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /pigx-device

WORKDIR /pigx-device

EXPOSE 4500
EXPOSE 5000

ADD ./target/pigx-device.jar ./

ADD ./src/main/resources/ca.jks ./

ADD ./src/main/resources/outgoing.CertwithKey.pkcs12 ./

CMD sleep 120;java $JAVA_OPTS -jar pigx-device.jar