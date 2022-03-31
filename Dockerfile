FROM eclipse-temurin:17.0.1_12-jdk-alpine

EXPOSE 9090

ENV APP_HOME /workspace
ENV APP_USER serviceuser
ENV APP_SERVICE_GROUP servicegroup

RUN mkdir -p ${APP_HOME}
RUN addgroup ${APP_SERVICE_GROUP} && adduser --ingroup ${APP_SERVICE_GROUP} --disabled-password ${APP_USER}
RUN chown ${APP_USER} ${APP_HOME}

RUN apk --no-cache add curl
RUN apk --no-cache add bash

WORKDIR ${APP_HOME}

COPY scripts/start-app.sh ${APP_HOME}/
COPY /home/runner/work/demo-learn-programming-by-practice/demo-learn-programming-by-practice/target/demo-learn-programming-by-practice-0.0.1-SNAPSHOT.jar ./demo-learn-programming-by-practice-0.0.1-SNAPSHOT.jar

RUN chmod +x ${APP_HOME}/start-app.sh
RUN apk --no-cache add curl
RUN apk --no-cache add bash

USER ${APP_USER}
ENTRYPOINT ["./start-app.sh"]
