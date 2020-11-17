FROM mysql:5.7

MAINTAINER lz(lzj_0518@163.com)

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./1schema.sql /docker-entrypoint-initdb.d

#COPY ./2pigxx.sql /docker-entrypoint-initdb.d
#
#COPY ./3pigxx_config.sql /docker-entrypoint-initdb.d
#
#COPY ./4pigxx_job.sql /docker-entrypoint-initdb.d
#
#COPY ./5pigxx_device.sql /docker-entrypoint-initdb.d
#
#COPY ./6pigxx_smartff.sql /docker-entrypoint-initdb.d