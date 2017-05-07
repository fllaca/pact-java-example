# Pact Java (Spring Boot) example

## Pact Docs for JVM
* Consumer: https://github.com/DiUS/pact-jvm/tree/master/pact-jvm-consumer-junit
* Provider: https://github.com/DiUS/pact-jvm/tree/master/pact-jvm-provider-maven

## Pact verification

### 1. Generate Specs in consumer project

```
cd countries-service-client
mvn test
```

The pact file will be generated on `pacts` folder

### 2. Start Server to verify on

```
cd countries-service
mvn spring-boot:run -Dspring.profiles.active=h2
```

### 3. Verify Pacts

```
cd countries-service
mvn pact:verify
```
