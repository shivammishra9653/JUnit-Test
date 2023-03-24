#!/usr/bin/env bash
jar -uf ${CATALINA_HOME}/webapps/bitbucket_webhook.war WEB-INF/classes
$CATALINA_HOME/bin/catalina.sh run