FROM openjdk:8u131
VOLUME /tmp
ADD build/libs/order-processing-sample-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java -XX:+UnlockExperimentalVMOptions   -XX:+UseCGroupMemoryLimitForHeap   -XX:MaxRAMFraction=1 $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar