# OData and Spring Boot Train

## Information

This project was done to train skills in OData 2.0 and Spring Boot v2.0. 
The entity `Person` is included in it and we realize CRUD methods to it.

## Requirements
* Java 8 and later
* IDE (Intellij Idea will be more suitable)
* Maven v3.6.2

## Used Technologies
* Spring Boot 2.2.0
* Olingo v2.0

## Deployment

### Locally
* Clone git repository:
```https://github.com/Shotim/OData-and-Spring-Boot-Train```

* Build project:
```./mvnw.cmd clean install```

>If the project will be built result in the command line will be the following:
```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

* Start Spring Boot application:

```./mvnw.cmd spring-boot:run```

### On the CF
* Do all steps from local deployment
* Log on to SAP CF:
```
cf login
```
* Create hanatrial service instance:
```
cf create-service hanatrial hdi-shared hana
```
* Change fields `host` and `path` in manifest.yml
* Push project to CF:
```
cf push
```
