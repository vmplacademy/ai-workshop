---
description: 'Spring Boot development guidelines and best practices'
applyTo: '**/pom.xml, **/*.java, **/application.yml, **/application.properties'
---

# Spring Boot Development Guidelines

## Technology Stack Requirements
- **Java Version**: Java 21 (LTS)
- **Spring Boot Version**: 3.5.5
- **Build Tool**: Maven 3.9+
- **Database**: PostgreSQL (development and production)
- **Testing**: JUnit 5, Mockito, Testcontainers

## Project Structure
Follow Maven standard directory layout with interface-based design:
```
src/
├── main/
│   ├── java/
│   │   └── com/yourorg/yourapp/
│   │       ├── YourApplication.java
│   │       ├── api/              # REST Controllers
│   │       ├── config/           # Configuration classes
│   │       ├── domain/           # Business logic
│   │       │   ├── model/        # Entities and DTOs
│   │       │   ├── repository/   # Repository interfaces (JPA)
│   │       │   ├── service/      # Service interfaces
│   │       │   └── service/impl/ # Service implementations
│   │       ├── infrastructure/   # External service implementations
│   │       └── dto/              # Data Transfer Objects
│   └── resources/
│       ├── application.yml       # Main configuration
│       └── db/migration/         # Flyway migrations (if used)
└── test/
    └── java/
        └── com/yourorg/yourapp/
            ├── YourApplicationTests.java
            ├── api/              # Controller tests
            ├── domain/service/   # Service interface tests
            └── infrastructure/   # Repository integration tests
```

**Interface Organization**:
- **Repository Interfaces**: `domain.repository` package (extended from JpaRepository)
- **Service Interfaces**: `domain.service` package
- **Service Implementations**: `domain.service.impl` package (annotated with `@Service`)
- **Infrastructure Interfaces**: `infrastructure` package (external services)

## Architecture Patterns

### Layered Architecture
- **Controller Layer** (`@RestController`): Handle HTTP requests/responses
- **Service Layer** (`@Service`): Business logic and transaction management
- **Repository Layer** (`@Repository`): Data access abstraction
- **Entity Layer** (`@Entity`): JPA entities representing domain objects

### Interface-Based Layer Contracts

**MANDATORY**: All contracts between layers MUST be defined as interfaces to ensure loose coupling and testability.

#### **Interface Design Principles**
* **Separation of Concerns**: Each interface should have a single, well-defined responsibility
* **Dependency Inversion**: Controllers depend on service interfaces, services depend on repository interfaces
* **Spring Integration**: Use `@Service` and `@Repository` on implementations, not interfaces
* **Naming Convention**: Use descriptive names without 'I' prefix (Java convention)

#### **Layer Interface Requirements**

**Repository Interfaces**:
```java
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByTitleContaining(String keyword);
    
    @Query("SELECT t FROM Task t WHERE t.createdAt >= :date")
    List<Task> findTasksCreatedAfter(@Param("date") LocalDateTime date);
}
```

**Service Interfaces**:
```java
public interface TaskService {
    List<TaskDto> getAllTasks();
    TaskDto getTaskById(Long id);
    TaskDto createTask(CreateTaskCommand command);
    TaskDto updateTask(Long id, UpdateTaskCommand command);
    void deleteTask(Long id);
    List<TaskDto> getTasksByStatus(TaskStatus status);
}
```

**External Service Interfaces**:
```java
public interface NotificationService {
    void sendTaskCreatedNotification(TaskDto task);
    void sendTaskCompletedNotification(TaskDto task);
}

public interface CacheService {
    <T> Optional<T> get(String key, Class<T> type);
    void put(String key, Object value);
    void evict(String key);
}
```

#### **Implementation Guidelines**
* **Constructor Injection**: Always inject interfaces, never concrete classes
* **Spring Annotations**: Use `@Service` and `@Repository` on implementations
* **Exception Handling**: Define custom exceptions for business rule violations
* **Transaction Management**: Use `@Transactional` on service methods
* **Testing Support**: Interfaces enable easy mocking with Mockito

**Service Implementation Example**:
```java
@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final NotificationService notificationService;
    
    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }
    
    @Override
    public TaskDto createTask(CreateTaskCommand command) {
        Task task = taskMapper.toEntity(command);
        Task savedTask = taskRepository.save(task);
        TaskDto result = taskMapper.toDto(savedTask);
        
        notificationService.sendTaskCreatedNotification(result);
        return result;
    }
}
```

**Controller Implementation Example**:
```java
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {
    
    private final TaskService taskService; // Inject interface, not implementation
    
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
    
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody CreateTaskCommand command) {
        TaskDto createdTask = taskService.createTask(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }
}
```

### Dependency Injection
- Use constructor injection over field injection
- Prefer interfaces over concrete implementations
- Use `@Autowired` only when constructor injection is not possible

## Essential Dependencies

### Core Spring Boot Starters
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

### Testing Dependencies
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>postgresql</artifactId>
    <scope>test</scope>
</dependency>
```

## Configuration Guidelines

### Application Configuration (application.yml)
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/todolist
    username: ${DB_USERNAME:todolist}
    password: ${DB_PASSWORD:todolist}
    driver-class-name: org.postgresql.Driver
  
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
  
  application:
    name: todolist-api

server:
  port: 8080

logging:
  level:
    com.yourorg.yourapp: DEBUG
    org.hibernate.SQL: DEBUG
```

## Code Quality Standards

### Entity Design
```java
@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
```

### Repository Pattern
```java
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
    
    @Query("SELECT t FROM Task t WHERE t.createdAt >= :date")
    List<Task> findTasksCreatedAfter(@Param("date") LocalDateTime date);
}
```

### Service Layer
```java
// Service Interface (in domain/service package)
public interface TaskService {
    List<TaskDto> getAllTasks();
    TaskDto getTaskById(Long id);
    TaskDto createTask(CreateTaskCommand command);
    TaskDto updateTask(Long id, UpdateTaskCommand command);
    void deleteTask(Long id);
}

// Service Implementation (in domain/service/impl package)
@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }
    
    @Override
    public TaskDto createTask(CreateTaskCommand command) {
        Task task = taskMapper.toEntity(command);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }
}
```

### Controller Layer
```java
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {
    private final TaskService taskService; // Inject interface, not implementation
    
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
    
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody CreateTaskCommand command) {
        TaskDto createdTask = taskService.createTask(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }
}
```

**Key Benefits of Interface-Based Design**:
- **Testability**: Easy mocking with Mockito for unit tests
- **Flexibility**: Easy to swap implementations (e.g., caching service)
- **SOLID Principles**: Follows Dependency Inversion Principle
- **Spring Integration**: Spring automatically finds implementation by interface

## Testing Standards

### Test Naming Convention
Use descriptive method names: `should_ReturnAllTasks_When_TasksExist()`

### Unit Tests
```java
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    
    @Mock
    private TaskRepository taskRepository;
    
    @Mock
    private TaskMapper taskMapper;
    
    @InjectMocks
    private TaskService taskService;
    
    @Test
    void should_ReturnAllTasks_When_TasksExist() {
        // Given
        List<Task> tasks = List.of(createSampleTask());
        when(taskRepository.findAll()).thenReturn(tasks);
        when(taskMapper.toDto(any(Task.class))).thenReturn(createSampleTaskDto());
        
        // When
        List<TaskDto> result = taskService.getAllTasks();
        
        // Then
        assertThat(result).hasSize(1);
        verify(taskRepository).findAll();
    }
}
```

### Integration Tests
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class TaskControllerIntegrationTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void should_CreateTask_When_ValidRequestProvided() {
        // Given
        CreateTaskCommand command = CreateTaskCommand.builder()
                .title("Test Task")
                .description("Test Description")
                .build();
        
        // When
        ResponseEntity<TaskDto> response = restTemplate.postForEntity(
                "/api/tasks", command, TaskDto.class);
        
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getTitle()).isEqualTo("Test Task");
    }
}
```

## Error Handling

### Global Exception Handler
```java
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        log.warn("Entity not found: {}", ex.getMessage());
        ErrorResponse error = ErrorResponse.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        
        ErrorResponse error = ErrorResponse.builder()
                .message("Validation failed")
                .details(errors)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
```

## Documentation

### OpenAPI/Swagger Integration
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

### API Documentation
```java
@Tag(name = "Tasks", description = "Task management operations")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    @Operation(summary = "Get all tasks", description = "Retrieve all tasks from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved tasks"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        // Implementation
    }
}
```

## Performance Considerations

### Database Optimization
- Use appropriate JPA fetch types (`LAZY` by default)
- Implement pagination for large datasets
- Use database indexes for frequently queried fields
- Consider using `@BatchSize` for N+1 query problems

### Caching
```java
@Service
@RequiredArgsConstructor
public class TaskService {
    
    @Cacheable(value = "tasks", key = "#id")
    public TaskDto getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }
}
```

## Security Best Practices

### Input Validation
- Always validate input using Bean Validation annotations
- Sanitize user input to prevent injection attacks
- Use parameterized queries (JPA handles this automatically)

### CORS Configuration
```java
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

## Deployment Considerations

### Docker Configuration
```dockerfile
FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

### Production Configuration
- Use environment variables for sensitive configuration
- Enable production logging configuration
- Configure connection pooling for database connections
- Implement health checks and monitoring endpoints
