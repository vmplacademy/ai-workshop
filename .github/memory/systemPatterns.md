# System Patterns

## Architecture Overview

This project demonstrates multiple architectural patterns across different technology stacks, each showcasing how GitHub Copilot can assist in implementing established software design patterns.

## Cross-Stack Architectural Principles

### Contract-First Development
**Pattern:** API specification drives implementation
**Implementation:** OpenAPI specification (`docs/ToDoListOpenApi.json`) serves as single source of truth
**Benefits:**
- Consistent API contracts across all backend implementations
- Frontend can be developed in parallel with backend
- Clear documentation and testing boundaries

### Layered Architecture
**Pattern:** Separation of concerns through distinct layers
**Common Layers:**
- Presentation Layer (Controllers/Components)
- Business Logic Layer (Services/Domain)
- Data Access Layer (Repositories/DAL)
- Model/Entity Layer (Data structures)

## Technology-Specific Patterns

### Spring Boot Architecture

**Pattern:** Layered Architecture with Dependency Injection
```
┌─────────────────┐
│   Controllers   │ ← REST API endpoints
├─────────────────┤
│    Services     │ ← Business logic
├─────────────────┤
│  Repositories   │ ← Data access
├─────────────────┤
│    Entities     │ ← JPA entities
└─────────────────┘
```

**Key Patterns:**
- **Repository Pattern** - JPA repositories for data access
- **Service Layer Pattern** - Business logic separation
- **DTO Pattern** - Data transfer objects for API boundaries
- **Dependency Injection** - Spring IoC container management

**Directory Structure:**
```
src/main/java/
├── api/              # REST controllers
├── domain/
│   ├── service/      # Business logic interfaces and implementations
│   ├── repository/   # JPA repositories
│   └── model/        # Entities and enums
└── dto/              # Data Transfer Objects
```

### .NET Architecture

**Pattern:** Clean Architecture with Interface Segregation
```
┌─────────────────┐
│  Controllers    │ ← API endpoints
├─────────────────┤
│   Services      │ ← Business logic (ITaskService)
├─────────────────┤
│  Repositories   │ ← Data context (EF Core)
├─────────────────┤
│    Models       │ ← Domain entities
└─────────────────┘
```

**Key Patterns:**
- **Interface-Based Contracts** - All services implement interfaces
- **Clean Architecture** - Dependency inversion and separation
- **CQRS Elements** - Command/Query separation in DTOs
- **Entity Framework Pattern** - Code-first database approach

**Directory Structure:**
```
src/
├── Controllers/      # API controllers
├── Domains/
│   ├── Services/     # Service interfaces and implementations
│   ├── Models/       # Domain entities
│   └── Repositories/ # EF Core DbContext
└── Dtos/             # Command and Query objects
```

### Frontend Architectures

#### React Pattern
**Pattern:** Component-Based Architecture with Hooks
```
┌─────────────────┐
│   Components    │ ← UI components
├─────────────────┤
│     Hooks       │ ← State management
├─────────────────┤
│   Services      │ ← API integration
├─────────────────┤
│     Types       │ ← TypeScript definitions
└─────────────────┘
```

**Key Patterns:**
- **Component Composition** - Reusable UI building blocks
- **Custom Hooks** - Shared state logic
- **Provider Pattern** - Context for global state
- **Functional Programming** - Immutable state updates

#### Angular Pattern
**Pattern:** Component-Based with Services and Dependency Injection
```
┌─────────────────┐
│   Components    │ ← UI components
├─────────────────┤
│   Services      │ ← Business logic and API calls
├─────────────────┤
│    Models       │ ← TypeScript interfaces
├─────────────────┤
│   Modules       │ ← Feature organization
└─────────────────┘
```

**Key Patterns:**
- **Single Responsibility** - One component per feature
- **Service Layer** - Injectable services for data and logic
- **Reactive Programming** - RxJS observables for async operations
- **Module Federation** - Feature-based organization

## Data Access Patterns

### Database Design
**Pattern:** Normalized relational design
**Schema:**
```sql
Tasks Table:
- id (Primary Key)
- taskName/name (VARCHAR)
- description (TEXT)
- status (ENUM: TODO, IN_PROGRESS, DONE)
- dueDate (TIMESTAMP)
- createdAt (TIMESTAMP)
- updatedAt (TIMESTAMP)
```

### ORM Patterns
- **Spring Boot:** JPA/Hibernate with entity annotations
- **.NET:** Entity Framework Core with fluent configuration
- **Shared:** Repository pattern for data access abstraction

## Testing Patterns

### Backend Testing
**Pattern:** Test Pyramid with multiple layers
```
┌─────────────────┐
│ Integration     │ ← Full application testing
├─────────────────┤
│ Service Tests   │ ← Business logic testing
├─────────────────┤
│ Unit Tests      │ ← Individual component testing
└─────────────────┘
```

**Technologies:**
- **Spring Boot:** JUnit 5, Mockito, Testcontainers
- **.NET:** xUnit, Moq, Testcontainers
- **Shared:** PostgreSQL test containers for integration tests

### Frontend Testing
**Pattern:** Component testing with user interaction simulation
- **React:** Vitest + React Testing Library
- **Angular:** Jasmine + Karma (standard Angular testing)

## Security Patterns

### API Security
- **Input Validation** - Request body validation
- **Error Handling** - Consistent error responses
- **CORS Configuration** - Cross-origin request handling

### Data Validation
- **Backend Validation** - Service layer validation
- **Frontend Validation** - Form validation with user feedback
- **Schema Validation** - OpenAPI specification enforcement

## Deployment Patterns

### Development Environment
- **Docker Integration** - Testcontainers for database testing
- **Local Development** - Hot reload and rapid iteration
- **Environment Configuration** - Properties/appsettings files

### CI/CD Considerations
- **Build Pipeline** - Automated testing and building
- **Multi-Stack Support** - Independent deployment paths
- **Documentation Generation** - Automated from OpenAPI specs

These patterns demonstrate how GitHub Copilot can assist in implementing established architectural approaches while maintaining code quality and consistency across different technology stacks.