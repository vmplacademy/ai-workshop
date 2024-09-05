# Workshop Agenda: GitHub Copilot and Spring Boot Integration

## 1. Getting started

### Requirements

#### IDE of your choice.

During the workshop, we will be using IntelliJ IDEA. You can download free Community Edition version or try 30-days trial of Ultimate from the following
link: [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).
You can also use any other IDE with both Java and official Github Copilot support: Visual Studio or Visual Studio Code.

#### Github Copilot.

To install and configure Github Copilot simply follow official _Quick Start_
documentation: [Quickstart for GitHub Copilot](https://docs.github.com/en/copilot/quickstart).

**Important** be able to complete the workshop tasks, you need to have a GitHub account with enabled GitHub Copilot. There is 30-days free trial available.

More information can be found on the official GitHub Copilot website: [GitHub Copilot](https://docs.github.com/en/copilot).

#### Docker

To lunch the application locally, as well as start integration tests, you need to have Docker installed on your machine.
You can use [Docker Desktop](https://docs.docker.com/desktop/) or [Docker Engine](https://docs.docker.com/engine/), depending on your OS and needs.

#### Java 21

The project is built using Java 21. You can download the latest JDK 21 version from the official Oracle website: [Java 21](https://www.oracle.com/java/technologies/downloads/).

### Project Setup

#### 1. Clone the Project

```bash
  git checkout https://github.com/vmplacademy/ai-workshop.git/backend/initial
 ```

#### 2. Build the Project

```bash
  cd ai-workshop/backend 
  
  ./mvnw clean install -DskipTests
 ```

#### 3. Run application locally

```bash
  ./mvnw spring-boot:test-run
 ```

Start the application and navigate to `http://localhost:8080/swagger-ui.html` to access the Swagger UI. If there are not any errors, you are all set.

### Introduction to the Training Project

- **Overview**: Introduction to the ToDo List application.
- **Project Goals**: Discuss the objectives and functionality.

Goal of this project is to introduce you to the GitHub Copilot and its capabilities. We will be using GitHub Copilot to generate code snippets, unit tests, and documentation for the Spring Boot application.

## 2. TaskController Implementation

### Create RestController with GitHub Copilot Chat

- **TaskController**: Implement `TaskController` with injected `TaskService`.

### CRUD Operations Implementation

- **Create Method**: Step-by-step guide to implementing the `create` method.
- **Delete Method**: Step-by-step guide to implementing the `delete` method.
- **Update Method**: Step-by-step guide to implementing the `update` method.

## 3. Service Layer Implementation

- **CalculatorService**: Implement `CalculatorServiceAdapter` with Spring’s `@Service` annotation.

## 4. Entity and Repository Setup

### Create TaskEntity

- **TaskEntity**: Define with relevant Lombok annotations and JPA configuration.

### Create TaskRepository

- **TaskRepository**: Implement `TaskRepository` extending `JpaRepository`.

## 5. Exercise 1: New controller's method

- **Exercise**: Implement the `getAllTasks` method in both `TaskController` and `TaskService`.
- **Optional**: If needed, checkout proper project version at this point:
    ```bash
    git checkout https://github.com/vmplacademy/ai-workshop.git/backend/task-1
    ```

## 6. Unit Testing

### Generate Unit Tests using JUnit 5 and Mockito

- **TaskServiceTest**: Create unit tests with mock operations and input validation.
- **Review and Refine**: Evaluate generated test cases for accuracy.

## 7. Integration Testing

### TestContainers and Integration Tests

- **TaskControllerApiTest**: Implement using `@SpringBootTest` and WebClientTest.
- **Extend TestContainer Configuration**: Ensure comprehensive integration testing.

## 8. Exercise 2: Additional test cases

- **Exercise**: Implement `TaskRepositoryTest` using an in-memory database (HashMap).
- **Optional**: If needed, checkout proper project version at this point:
    ```bash
    git checkout https://github.com/vmplacademy/ai-workshop.git/backend/task-2
    ```

## 9. Code Refactoring

### Refactor Existing Code with GitHub Copilot

- **Improve Code Quality**: Refactor `LegacyNotificationService` and entire `legacy` package for better readability and performance.
- **Use Best Practices**: Ensure refactored code aligns with Java 21 standards.


## 10. Documentation Generation

### Generate Technical Documentation

- **Javadoc**: Automatically generate for `TaskController` and `TaskService`.
- **README.md**: Create comprehensive project documentation using GitHub Copilot.