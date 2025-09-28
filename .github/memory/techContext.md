# Technical Context

## Technology Stack Overview

This project implements identical functionality across multiple technology stacks to demonstrate GitHub Copilot's versatility across different programming paradigms.

## Backend Technologies

### Spring Boot Implementation
**Language:** Java 21
**Framework:** Spring Boot 3.5.5
**Dependencies:**
- Spring Web (REST API development)
- Spring Data JPA (Database abstraction)
- PostgreSQL Driver (Database connectivity)
- Lombok (Boilerplate reduction)
- Spring Boot Starter Test (Testing framework)
- Testcontainers (Integration testing)

**Development Commands:**
```bash
# Navigate to project
cd spring-boot

# Build and test
./mvnw clean install

# Run application
./mvnw spring-boot:run

# Run with test database
./mvnw spring-boot:test-run

# Run specific tests
./mvnw test -Dtest=TaskServiceTest
./mvnw test -Dtest=*IntegrationTest
```

**Configuration:**
- Application runs on port 8080
- Swagger UI available at `/swagger-ui.html`
- H2 console for development database
- PostgreSQL for production

### .NET Implementation
**Language:** C#
**Framework:** .NET 8.0
**Dependencies:**
- ASP.NET Core (Web API framework)
- Entity Framework Core (ORM)
- PostgreSQL Entity Framework Provider
- AutoMapper (Object mapping)
- FluentValidation (Input validation)
- xUnit (Testing framework)
- Moq (Mocking framework)
- Testcontainers (Integration testing)

**Development Commands:**
```bash
# Navigate to project
cd dotnet/src

# Restore packages
dotnet restore

# Build application
dotnet build

# Run application
dotnet run

# Run tests
cd ../tests/TodoApp.Tests
dotnet test

# Watch mode for development
dotnet watch run
```

**Configuration:**
- Application runs on port 5025
- Swagger UI available at `/swagger`
- Entity Framework migrations for database schema
- Environment-specific configuration files

## Frontend Technologies

### React Implementation
**Language:** TypeScript
**Framework:** React 18
**Build Tool:** Vite
**Styling:** Tailwind CSS
**Dependencies:**
- React Router (Navigation)
- Axios (HTTP client)
- React Hook Form (Form management)
- Zod (Schema validation)
- Vitest (Testing framework)
- React Testing Library (Component testing)

**Development Commands:**
```bash
# Navigate to project
cd react

# Install dependencies
npm install

# Development server
npm run dev

# Build for production
npm run build

# Run tests
npm run test

# Lint code
npm run lint

# Preview production build
npm run preview
```

**Configuration:**
- Development server on port 5173
- Hot module replacement for rapid development
- TypeScript strict mode enabled
- ESLint and Prettier for code quality

### Angular Implementation
**Language:** TypeScript
**Framework:** Angular (Latest)
**Styling:** Tailwind CSS
**Dependencies:**
- Angular Router (Navigation)
- Angular HTTP Client (API communication)
- Angular Reactive Forms (Form management)
- RxJS (Reactive programming)
- Angular Testing Utilities (Component testing)

**Development Commands:**
```bash
# Navigate to project
cd angular

# Install dependencies
npm install

# Development server
ng serve

# Build for production
ng build

# Run tests
ng test

# End-to-end tests
ng e2e

# Lint code
ng lint
```

**Configuration:**
- Development server on port 4200
- Angular CLI for scaffolding and building
- TypeScript strict mode enabled
- Tailwind CSS utility-first styling

## Database Technology

### PostgreSQL
**Version:** Latest stable
**Usage:** Production database for all backend implementations
**Schema:**
```sql
-- Spring Boot table name
CREATE TABLE t_task (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) NOT NULL,
    due_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- .NET table name
CREATE TABLE Tasks (
    Id BIGSERIAL PRIMARY KEY,
    TaskName VARCHAR(255) NOT NULL,
    Description TEXT,
    Status VARCHAR(50) NOT NULL,
    DueDate TIMESTAMP NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**Status Enumeration:**
- `TODO` - Initial state
- `IN_PROGRESS` - Work in progress
- `DONE` - Completed

## Development Environment Setup

### Prerequisites
```bash
# Java Development Kit 21
java -version

# .NET SDK 8.0
dotnet --version

# Node.js (LTS version)
node --version
npm --version

# Docker (for database testing)
docker --version

# Git
git --version
```

### IDE Configuration
**Recommended IDEs:**
- **Spring Boot:** IntelliJ IDEA, Eclipse, VS Code with Java extensions
- **.NET:** Visual Studio, JetBrains Rider, VS Code with C# extensions
- **React/Angular:** VS Code, WebStorm, Atom with appropriate extensions

**GitHub Copilot Integration:**
- Install GitHub Copilot extension for chosen IDE
- Configure with GitHub account
- Follow framework-specific instruction files in `.github/instructions/`

## API Specification

### OpenAPI Contract
**File:** `docs/ToDoListOpenApi.json`
**Specification Version:** OpenAPI 3.0.2
**Base URL:** `http://localhost:{port}/api`

**Endpoints:**
- `POST /tasks` - Create new task
- `GET /tasks` - Retrieve all tasks
- `GET /tasks/{id}` - Retrieve specific task
- `PUT /tasks/{id}` - Update existing task
- `DELETE /tasks/{id}` - Remove task

### Request/Response Models
```typescript
// Task creation request
interface CreateTaskRequest {
  taskName: string;
  dueDate: string; // ISO 8601 format
  description?: string;
}

// Task response model
interface TaskResponse {
  id: number;
  taskName: string;
  description: string;
  status: 'TODO' | 'IN_PROGRESS' | 'DONE';
  dueDate: string;
  createdAt: string;
  updatedAt: string;
}
```

## Testing Infrastructure

### Test Databases
**Testcontainers Integration:**
- Automatic PostgreSQL container startup for integration tests
- Isolated test environment for each test suite
- Cleanup after test completion

### Test Coverage Goals
- **Unit Tests:** 80%+ coverage for business logic
- **Integration Tests:** All API endpoints covered
- **Frontend Tests:** All components with user interaction scenarios

## Build and Deployment

### Local Development
- All services run independently on different ports
- Frontend applications proxy API requests to backend services
- Hot reload enabled for rapid development iteration

### Production Considerations
- Environment-specific configuration files
- Database connection pooling
- CORS configuration for frontend integration
- Logging and monitoring setup

## Technical Constraints

### Version Compatibility
- Java 21 LTS for long-term support
- .NET 8.0 for latest features and performance
- React 18 for concurrent features
- Angular latest for modern development experience

### Performance Requirements
- API response times under 200ms for typical operations
- Frontend rendering under 100ms for user interactions
- Database queries optimized with proper indexing

### Security Considerations
- Input validation on all API endpoints
- SQL injection prevention through ORM usage
- CORS configuration for cross-origin requests
- Error handling without sensitive information exposure