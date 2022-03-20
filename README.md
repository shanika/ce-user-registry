## Counties Energy User Registry

This is an exercise done as part of counties energy interview process.

### Overview
As a part of designing a new electric system a new user register needs to be established. This exercise covers building the services to support this new register.

### Goal
Design and implement a RESTful API to retrieve user information.

###Features
1. The API must have the ability for a client to retrieve users matching a requested surname
2. Each user’s details are to contain the following information: 
   1. Surname
   2. First name
   3. Email address

###Technical Requirements
1. The service is to be implemented using Spring Boot or Micronaut. Other Java dependencies/libraries can be included for required functionality
2. User data can be pre-populated, or a RESTful endpoint exposed to add users to the system
   1. Data can be stored in memory or in a database
3. The application code should contain at least one test
4. Include a README file with instructions on how to build, run and use the application

## Assumptions

1. Surname, first name and email can have maximum of 100 characters

## Libraries and dependencies

Java 11, Micronaut framework, Micronaut Data/JPA, Lombok, H2

H2 inmemory database was used due the ease of execution.

## Build

Prerequisite: Java 11

```shell
./gradlew build
```

## Test

```shell
./gradlew test
```

## Run

```shell
./gradlew run
```

## Docker build and Run

```shell
# Build
./gradlew dockerBuild

# Run
docker run -p 8080:8080 ce-user-registry:latest
```

## Manual testing
```shell
# Create user
curl -X POST http://localhost:8080/users
   -H 'Content-Type: application/json'
   -d '{"firstName":"Jon","surname":"Doe", "email": "jon@example.com"}'
   
# search user
curl http://localhost:8080/users?surname=Doe
   -H "Accept: application/json" 
```



