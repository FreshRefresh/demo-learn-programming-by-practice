#!/usr/bin/env bash
[[ -z ${JAVA_MAX_HEAP} ]] && JAVA_MAX_HEAP="768m"
[[ -z ${JAVA_MIN_HEAP} ]] && JAVA_MIN_HEAP="64m"

exec java -Xms${JAVA_MIN_HEAP} -Xmx${JAVA_MAX_HEAP} -jar ./demo-learn-programming-by-practice-0.0.1-SNAPSHOT.jar
