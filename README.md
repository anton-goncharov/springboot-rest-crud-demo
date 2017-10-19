### Requirements

* Java 8+ 
* Apache Maven

### Overview

By default, the applucation runs on port **8000**. This can be configured in `src/main/resources/application.yml`.

The service root URL is `localhost:8000/v1/payments`

Embedded in-memory H2 database is used. It runs console at `localhost:8000/h2`

Auto-generated REST API documentation is deployed to `localhost:8000/ui-swagger.html` 

### Deployment

Clone the repository to your local folder.

To run tests, execute
`mvn test`

To run the application, execute
`mvn spring-boot:run`
