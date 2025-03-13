# Java 21 & Spring Boot 3.3 Coding Guidelines

## Java 21 Features to Leverage
- **Record Patterns**: Use record patterns with pattern matching for more concise, type-safe data extraction
- **Pattern Matching for Switch**: Prefer switch expressions with pattern matching over long if-else chains
- **Virtual Threads**: Use virtual threads for I/O-bound operations to improve scalability
- **Structured Concurrency**: Use structured concurrency API for managing related tasks
- **String Templates**: Leverage preview feature with `--enable-preview` for cleaner string interpolation
- **Sequenced Collections**: Use the new framework interfaces for ordered collections

## Spring Boot 3.3 Best Practices
- **Spring Boot Starters**: Use appropriate starters to minimize configuration
- **Configuration Properties**: Use `@ConfigurationProperties` with strong typing
- **Native Support**: Design with GraalVM native compilation in mind
- **Auto-configuration**: Create custom auto-configurations for reusable components
- **Observability**: Use Micrometer and the Observability API for metrics, traces, and logs
- **RestClient**: Use the new RestClient instead of RestTemplate for HTTP communications

## Code Organization
- Keep controllers thin, delegate business logic to services
- Use DTOs as Java records for API communication
  ```java
  public record UserDto(Long id, String name, String email) {}
  ```
- Store business rules in the domain model, not in services
- Follow immutability principles where appropriate

## Lombok Usage
- Use `@Data` for full suite of getters, setters, equals, hashCode, and toString for mutable classes
- Prefer `@Value` for immutable classes
- Use `@Builder` for fluent object creation patterns
- Apply `@RequiredArgsConstructor` for constructor injection
- Use `@Slf4j` for logging
- Combine with records where applicable:
  ```java
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public class ResponseWrapper<T> {
      private T data;
      private String message;
  }
  ```

## Security Guidelines
- Use Spring Security with proper CSRF and XSS protection
- Implement OAuth2/OIDC for authentication where appropriate
- Validate all input data
- Never trust client-side data

## Testing
- Use JUnit 5 with AssertJ for assertions
- Leverage Spring Boot Test for integration testing
- Use Testcontainers for integration tests requiring external dependencies
- Mock external services with MockMvc or WebTestClient

## Performance Considerations
- Use appropriate caching strategies
- Optimize database queries with proper indexing
- Consider non-blocking APIs for high-throughput scenarios
- Leverage virtual threads for I/O operations

## API Design
- Follow REST principles for API design
- Use proper HTTP methods and status codes
- Implement proper error handling with Problem Details (RFC 7807)
- Document APIs with OpenAPI/Swagger