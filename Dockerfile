FROM ubuntu:trusty
MAINTAINER Konrad Kühne <konrad.kuehne@rocketmail.com>

RUN apt-get  update
RUN apt-get install -y git-core curl unzip openjdk-7-jre-headless

ENV JAVA_HOME /usr/lib/jvm/java-7-openjdk-amd64

RUN wget https://raw.github.com/technomancy/leiningen/stable/bin/lein -O /usr/local/bin/lein
RUN chmod +x /usr/local/bin/lein
ENV LEIN_ROOT yes
RUN lein


# grab datomic
RUN wget https://my.datomic.com/downloads/free/0.9.4899 -O /opt/datomic.zip
RUN unzip /opt/datomic.zip -d /opt
RUN mv /opt/datomic-free-0.9.4899 /opt/datomic


ADD ./opt /opt

RUN git clone https://github.com/kordano/haushalt.git /opt/haushalt

EXPOSE 8092
