export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
cd liquibase
mvn liquibase:update
cd ..
mvn clean install -DskipTests
mvn tomcat7:run

