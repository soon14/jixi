FROM pig4cloud/java:8-jre

MAINTAINER lz(lzj_0518@163.com)

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /pigx-smartff

WORKDIR /pigx-smartff

EXPOSE 4600

ADD ./target/pigx-smartff.jar ./

CMD sleep 120;java $JAVA_OPTS -jar pigx-smartff.jar