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
  git clone https://github.com/vmplacademy/ai-workshop.git
  
 ```

#### 2. Checkout proper branch

```bash
  cd ai-workshop 
  
  git checkout backend/initial
 ```

#### 3. Build the Project

```bash
  cd ai-workshop/backend 
  
  ./mvnw clean install -DskipTests
 ```

#### 4. Run application locally

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

**TaskController**: Implement `TaskController` with injected `TaskService`.

Prompt examples:
```text
We are going to create simple ToDo list application. Do not! provide any suggestions at this point. It just for context. 
We are going to implement it step by step starting with TaskController. Just wait for my next promtps.
```
```text
Create TaskController with injected TaskService as interface in Spring Boot as RestController for CRUD operations for ToDo application. 
Provide only empty bodies without implementations. Handle proper operations via suitable Http methods
```

### CRUD Operations Implementation

**Create Method**: Step-by-step guide to implementing the `create` method.

Prompt example:
```text
Create "create" method using TaskService. It takes object named CreateTaskCommand as parameter. Returned type should be TaskQuery.
```

**Get Method**: Step-by-step guide to implementing the `get` method.

Prompt example:
```text
Create "get" method using TaskService. It takes TaskId as Long as parameters. Returned type should be TaskQuery.
```

**Update Method**: Step-by-step guide to implementing the `update` method.

Prompt example:
```text
Create "update" method using TaskService. It takes object named UpdateTaskCommand as parameter. Returned type should be TaskQuery.
```

**Delete Method**: Step-by-step guide to implementing the `delete` method.

Prompt example:
```text
Create "delete" method using TaskService. It takes TaskId as Long as parameters. This method should be 'void'.
```

## 3. Service Layer Implementation

**CalculatorService**: Implement `TaskServiceAdapter` with Spring’s `@Service` annotation.

Prompt example:
```text
Based on TaskController provide proper implementation for interface TaskService as TaskServiceAdapter. It should contain Spring "@Service" annotation.
```

## 4. Entity and Repository Setup

### Create TaskEntity

**TaskEntity**: Define with relevant Lombok annotations and JPA configuration.

Prompt example:
```text
Create TaskEntity with all relevant Lombok annotations for storing jpa entity. Use table name as "t_task".  
Provide all relevant columns as String: "name", TaskStatus (enum): "status". Add proper data types for each column. 
```

### Create TaskRepository

**TaskRepository**: Implement `TaskRepository` extending `JpaRepository`.

Prompt example:
```text
Create TaskRepository, which extends JpaRepository based on TaskEntity.
```

### Use TaskRepository in TaskService

**TaskService**: Use `TaskRepository` and implement CRUD operations.

Prompt example:
```text
In TaskService use injected TaskRepository to implement CRUD operations.
```

## 5. Exercise 1: New controller's method

**Exercise**: Implement the `getAllTasks` method in both `TaskController` and `TaskService`.

Prompt example:
```text
Create new method in both TastController and TaskService for "getAllTasks". 
It will be "GET" operation and should returned List<TaskQuery>.
```
***Optional**: If needed, checkout proper project version at this point:
    ```bash
    git checkout https://github.com/vmplacademy/ai-workshop.git/backend/task-1
    ```

## 6. Unit Testing

### Generate Unit Tests using JUnit 5 and Mockito

**TaskServiceTest**: Create unit tests with mock operations and input validation.

Prompt example:
```text
Create TaskServiceTest, which should be unit test class based on TaskService. Provide test methods for each operation. 
Handle proper input validation. Mock operations with TaskRepository.
```
**Review and Refine**: Evaluate generated test cases for accuracy.

## 7. Integration Testing

### TestContainers and Integration Tests

**TaskControllerApiTest**: Implement using `@SpringBootTest` and WebClientTest.

Prompt example:
```text
Create TaskApiTest, which should be integration test class based on TaskController. Provide test methods for each operation. 
Use @SpringBootTest annotation and use WebClientTest. Import using @Import existing TestcontainersConfiguration class.
```

**Extend TestContainer Configuration**: Ensure comprehensive integration testing.

## 8. Exercise 2: Additional test cases

**Exercise**: Implement `TaskRepositoryTest` for handling database interaction.

Prompt example:
```text
Create new test class for TaskRepository. It should contain unit test cases for handling database interaction. 
It should use @DataJpaTest annotation. Add also dependency for H2 database for tests. 
```

**Optional**: If needed, checkout proper project version at this point:
    ```bash
    git checkout https://github.com/vmplacademy/ai-workshop.git/backend/task-2
    ```

## 9. Code Refactoring

### Refactor Existing Code with GitHub Copilot

**Improve Code Quality**: Refactor `LegacyNotificationService` and entire `legacy` package for better readability and performance.

Prompt examples:
```text
Explain in detail the role of the given class based on TaskOutOfDateService class.
```
```text
Provide potential code improvements for <method_name>. Use java best practices as of Java 21. Make sure that the suggestions given improve code quality and readability.
```

**Use Best Practices**: Ensure refactored code aligns with Java 21 standards.


## 10. Documentation Generation

### Generate Technical Documentation

**Javadoc**: Automatically generate for `TaskController` and `TaskService`.

**README.md**: Create comprehensive project documentation using GitHub Copilot.

Prompt examples:
```text
Create simple project documentation as README.md based on already existing Javadoc documentation in TaskController. 
Add information about used technologies such as Spring Boot 3.x, PostgreSQL, testcontainers. 
Provide different sections with proper headers as "Main project goal", "Used technologies", "How to start it?". 
Use markdown format.
```
```text
Provide suggestions what could be also included in such a README.md?
```

## * Have fun!

Most importantly, have fun and enjoy the workshop! If you have any questions or need assistance, feel free to ask. We are here to help you! 🚀
