## Run server

Setup database if you haven't yet:

```
cd liquibase
mvn liquibase:update
```

Run tomcat server:

```
mvn clean install -DskipTests
mvn tomcat7:run
```

## Create user

```
sh createuser.sh
```

OR

```
curl -v -X POST localhost:8080/api/v1/public/users -H "Content-type: application/json" -d '{"username":"test@example.com","password":"w1secret$"}'
```

(replace username and password with whatever you like)
