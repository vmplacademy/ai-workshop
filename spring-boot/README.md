# ToDo List Application

## Main project goal

The goal of this project is to introduce you to GitHub Copilot and its capabilities. We will be using GitHub Copilot to generate code snippets, unit tests, and documentation for a Spring Boot application.

## Used technologies

- **Java 21**
- **Spring Boot 3.3.3**
- **PostgreSQL**
- **Testcontainers**
- **Maven**
- **Lombok**
- **Swagger/OpenAPI**

## Project Structure

```plaintext
.
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── pl
│   │   │   │   └── vm
│   │   │   │       └── aiworkshop
│   │   │   │           ├── api
│   │   │   │           └── domain
│   │   │   │               ├── legacy
│   │   │   │               ├── model
│   │   │   │               ├── repository
│   │   │   │               └── service
│   │   │   │           ├── dto
│   │   └── resources
│   └── test
│       ├── java
│       └── resources
├── pom.xml
└── README.md
```

## How to start it locally?

### Requirements

- **IDE of your choice**: IntelliJ IDEA is recommended.
- **GitHub Copilot**: Ensure you have a GitHub account with GitHub Copilot enabled.
- **Docker**: Install Docker Desktop or Docker Engine.
- **Java 21**: Download the latest JDK 21 version from the official Oracle website.

### Project Setup

1. **Clone the Project**

    ```bash
    git checkout https://github.com/vmplacademy/ai-workshop.git/backend/initial
    ```

2. **Build the Project**

    ```bash
    cd ai-workshop/backend
    ./mvnw clean install -DskipTests
    ```

3. **Run application locally**

    ```bash
    ./mvnw spring-boot:test-run
    ```

Start the application and navigate to `http://localhost:8080/swagger-ui.html` to access the Swagger UI. If there are no errors, you are all set.

Alternatively, you can use the `ToDoListLocalApplication` configuration, which requires Docker and uses Testcontainers.

## API Documentation

The API documentation is available at `http://localhost:8080/swagger-ui.html`.

## Testing

To run unit and integration tests, use the following command:

```bash
./mvnw test
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


