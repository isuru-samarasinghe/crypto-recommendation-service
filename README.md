## Crypto Recommendation Service

This is a Java 8 and Spring Boot application that provides a recommendation service for cryptocurrencies. The service reads prices from CSV files, calculates various statistics for each cryptocurrency, and exposes several endpoints for retrieving this data.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 8
- Maven
- Docker (optional)

### Installing

1. Clone the repository
```bash
git clone https://github.com/isuru-samarasinghe/crypto-recommendation-service.git
```

2. Navigate to the project directory
```bash
cd crypto-recommendation-service
```

3. Build the project with Maven
```bash
mvn clean install
```

4. Run the application
```bash
java -jar target/crypto-recommendation-service-1.0-SNAPSHOT.jar
```

The application will start running at `http://localhost:8080`.

If you want to run the application using `mvn spring-boot:run` command, makesure to copy `application.properties` to the root of the project.

## Docker

To run the application in a Docker container, build the Docker image and run it:

1. Build the project
```bash
mvn package
```

2. Build the Docker image
```bash
docker build -t crypto-recommendation-service .
```

3. Run the Docker container
```bash
docker run -p 8080:8080 crypto-recommendation-service
```

## Documentation

The API documentation is available at `http://localhost:8080/swagger-ui.html`.


## Testing

To run the tests, use the following Maven command:

```bash
mvn test
```


## Code Coverage

To generate the code coverage report, use the following Maven command:

```bash
mvn test jacoco:report
```

This will generate a report in `target/site/jacoco/index.html`. Open this file in a web browser to view the report.


## Built With

- [Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) - The programming language used
- [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
- [Maven](https://maven.apache.org/) - Dependency Management
- [Swagger](https://swagger.io/) - Used to generate the API documentation

## Authors

- Isuru Samarasinghe

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE.md](LICENSE.md) file for details