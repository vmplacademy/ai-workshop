# Workshop Agenda: GitHub Copilot and Spring Boot Integration

## 1. Introduction and Environment Setup

### 1.1 Installation and Configuration of IntelliJ IDEA

- **Download and Install**: IntelliJ IDEA.
- **Configure**: IntelliJ IDEA for Spring Boot projects.
- **Integrate**: GitHub Copilot with IntelliJ IDEA.

### 1.2 Project Setup

- **Clone the Project**: From the provided GitHub repository.
    ```bash
    git checkout https://github.com/vmplacademy/ai-workshop.git/backend/initial
    ```
- **Build the Project**: Ensure successful project build for all participants.

## 2. Introduction to the Training Project

- **Overview**: Introduction to the ToDo List application.
- **Project Goals**: Discuss the objectives and functionality.

## 3. TaskController Implementation

### 3.1 Create RestController with GitHub Copilot Chat

- **TaskController**: Implement `TaskController` with injected `TaskService`.

### 3.2 CRUD Operations Implementation

- **Create Method**: Step-by-step guide to implementing the `create` method.
- **Delete Method**: Step-by-step guide to implementing the `delete` method.
- **Update Method**: Step-by-step guide to implementing the `update` method.

## 4. Service Layer Implementation

- **CalculatorService**: Implement `CalculatorServiceAdapter` with Spring’s `@Service` annotation.

## 5. Entity and Repository Setup

### 5.1 Create TaskEntity

- **TaskEntity**: Define with relevant Lombok annotations and JPA configuration.

### 5.2 Create TaskRepository

- **TaskRepository**: Implement `TaskRepository` extending `JpaRepository`.

## 6. Exercise 1: New controller's method

- **Exercise**: Implement the `getAllTasks` method in both `TaskController` and `TaskService`.
- **Optional**: If needed, checkout proper project version at this point:
    ```bash
    git checkout https://github.com/vmplacademy/ai-workshop.git/backend/task-1
    ```

## 7. Unit Testing

### 7.1 Generate Unit Tests using JUnit 5 and Mockito

- **TaskServiceTest**: Create unit tests with mock operations and input validation.
- **Review and Refine**: Evaluate generated test cases for accuracy.

## 8. Integration Testing

### 8.1 TestContainers and Integration Tests

- **TaskControllerApiTest**: Implement using `@SpringBootTest` and WebClientTest.
- **Extend TestContainer Configuration**: Ensure comprehensive integration testing.

## 6. Exercise 2: Additional test cases

- **Exercise**: Implement `TaskRepositoryTest` using an in-memory database (HashMap).
- **Optional**: If needed, checkout proper project version at this point:
    ```bash
    git checkout https://github.com/vmplacademy/ai-workshop.git/backend/task-2
    ```

## 10. Documentation Generation

### 10.1 Generate Technical Documentation

- **Javadoc**: Automatically generate for `TaskController` and `TaskService`.
- **README.md**: Create comprehensive project documentation using GitHub Copilot.

## 11. Code Refactoring

### 11.1 Refactor Existing Code with GitHub Copilot

- **Improve Code Quality**: Refactor `LegacyNotificationService` and entire `legacy` package for better readability and performance.
- **Use Best Practices**: Ensure refactored code aligns with Java 21 standards.
