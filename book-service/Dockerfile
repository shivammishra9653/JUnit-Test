FROM phenompeople/tomcat-openjdk:8.5.72
MAINTAINER Rakesh Kumar Shah "rakesh.shah@phenompeople.com"
LABEL project="book-service"
ARG WAR_NAME="book-service.war"
ARG APP_HOME="/opt/deployment"
ENV WAR_NAME=${WAR_NAME} \
    APP_HOME=${APP_HOME}

COPY *-server.sh ${APP_HOME}/
COPY target/${WAR_NAME} ${CATALINA_HOME}/webapps/${WAR_NAME}
RUN chmod a+x ${APP_HOME}/*-server.sh && \
    touch ${CATALINA_PID} && \
    chown -R phenom:phenom ${APP_HOME}

USER phenom

WORKDIR ${APP_HOME}
ENTRYPOINT ["/opt/deployment/start-server.sh"]