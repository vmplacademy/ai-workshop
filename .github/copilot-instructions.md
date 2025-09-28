# GitHub Copilot Instructions

## Project Overview
This is a multi-stack AI Workshop implementing identical TodoApp functionality across Spring Boot (Java 21), .NET 8, React, and Angular. All backends implement the OpenAPI contract at `docs/ToDoListOpenApi.json`.

## Documentation Structure (`docs/`)

### Core API Documentation
- **`docs/ToDoListOpenApi.json`** - THE source of truth for all API contracts
  - Defines all endpoints: GET/POST/PUT/DELETE `/api/tasks`
  - Specifies exact request/response schemas
  - ALL backends MUST implement this specification exactly

- **`docs/LocalApiClient.http`** - HTTP client file for testing API endpoints
  - Use with REST Client extensions in VS Code/IntelliJ
  - Contains example requests for all endpoints

### Frontend Documentation (`docs/frontend/`)
- **`docs/frontend/PRD_frontend.md`** - Product Requirements Document
  - Complete UI/UX specifications
  - Single-screen architecture requirements
  - Dialog-based form interactions

- **`docs/frontend/mockups/`** - Visual design specifications (7 PNG files)
  - `1_main_view.png` - Primary interface layout
  - `2.1_add_task_main_view.png` - Task creation context
  - `2.2_add_task_date_view.png` - Date picker UI
  - `2.3_add_task_dialog_view.png` - Create task modal
  - `3.1_edit_task_view.png` - Edit task context
  - `3.2_edit_task_dialog_view.png` - Edit task modal
  - `4_delete_task_view.png` - Delete confirmation dialog

**IMPORTANT**: For Angular 20, use `angular/ANGULAR_IMPLEMENTATION.md` instead of PRD

## Essential Architecture Patterns

### Contract-First Development
- **Source of Truth**: `docs/ToDoListOpenApi.json` defines all API contracts
- **Consistency**: All backends must implement identical endpoints and DTOs
- Generate code from OpenAPI spec, don't deviate from defined schemas

### Layered Architecture (All Backends)
**Spring Boot Pattern**:
```
api/ → domain/service/ → domain/repository/ → domain/model/
Controllers → Services (interfaces + impl) → JPA Repositories → Entities
```

**.NET Pattern**:
```
Controllers/ → Domains/Services/ → Domains/Repositories/ → Domains/Models/
Controllers → ITaskService + TaskService → DbContext → Entities
```

### Frontend Architecture Patterns
**React Pattern**:
```
components/ → hooks/ → services/ → types/
UI Components → Custom Hooks → API Services → TypeScript Types
```

**Angular Pattern** (Angular 20 with standalone components):
```
components/ → services/ → models/
Standalone Components → Injectable Services → TypeScript Interfaces
(NO modules - use standalone components only)
```

### Key Conventions

#### Interface-Based Design
- **Spring Boot**: Services must implement interfaces (e.g., `TaskService` interface → `TaskServiceAdapter` impl)
- **.NET**: All services have interface contracts (e.g., `ITaskService` → `TaskService`)
- **Testing**: Always mock interfaces, never concrete classes

#### Frontend Patterns
**React**:
- **Custom Hooks**: Use `useTasks()` for state management, `useAPI()` for data fetching
- **Component Composition**: Functional components with TypeScript props interfaces
- **Context + useReducer**: Global state with `TaskContext` and dispatch actions
- **React Query**: Use `@tanstack/react-query` for server state management

**Angular** (Version 20):
- **Signals**: Use `signal()`, `computed()`, `effect()` for ALL state management (NO NgRx/Akita)
- **Standalone Components**: ONLY use standalone components (no NgModules)
- **NO Routing**: Single-screen application - no Angular Router needed
- **CDK Dialog**: Use Angular CDK for modal dialogs, not custom implementations
- **Tailwind CSS 4**: Use utility classes, no Angular Material
#### DTO Patterns
- **Command Objects**: `CreateTaskCommand`, `UpdateTaskCommand` for inputs
- **Query Objects**: `TaskQuery` for outputs
- **Mapping**: Manual mapping methods (Spring Boot) or AutoMapper (.NET)

#### Frontend Testing Patterns
**React**:
- **Vitest + React Testing Library**: Use `vi.fn()` for mocks, not Jest
- **Component Testing**: `render()`, `screen.getByText()`, `fireEvent.click()`
- **Hook Testing**: `renderHook()` from `@testing-library/react`

**Angular**:
- **Jasmine + Karma**: Use `TestBed.configureTestingModule()`
- **Component Testing**: `fixture.detectChanges()`, `compiled.querySelector()`
- **Service Testing**: Mock dependencies with `jasmine.createSpy()`

#### Test Structure
**Spring Boot**: Use `@ExtendWith(MockitoExtension.class)` with `@Nested` classes:
```java
@Nested
@DisplayName("Create Task")
class CreateTask {
    @Test
    @DisplayName("Successfully create a task")
    void createTaskSuccessfully() { /* given-when-then pattern */ }
}
```

**.NET**: Use xUnit with method names `Should_Return_Expected_When_Condition()`:
```csharp
public void Should_Return_TaskQuery_When_CreateTask_WithValidCommand()
```

### Critical Development Commands
**Spring Boot**:
- `./mvnw spring-boot:test-run` - Run with test database (Testcontainers)
- `./mvnw test -Dtest=TaskServiceAdapterTest` - Run specific tests
- Swagger UI: `http://localhost:8080/swagger-ui.html`

**.NET**:
- `dotnet watch run` - Development with auto-reload
- `dotnet test` - Run all tests (including Testcontainers integration tests)
- Swagger UI: `http://localhost:5025/swagger`

**React**:
- `npm run dev` - Vite dev server (port 5173)
- `npm run test` - Vitest with React Testing Library
- Project structure: `src/components/`, `src/hooks/`, `src/services/`

**Angular** (Version 20):
- `npx @angular/cli@20 new . --standalone --routing=false --style=css --skip-git --ssr=false` - Initial setup
- `ng serve` - Development server (port 4200)
- `ng generate component components/ComponentName --standalone` - Create standalone components ONLY
- `ng test` - Jasmine + Karma testing
- Project structure: `src/app/components/`, `src/app/services/` (NO modules folder)
- Tailwind CSS 4 configuration required (see `angular/ANGULAR_IMPLEMENTATION.md`)

### Framework-Specific Testing Patterns
**Spring Boot**: Use Testcontainers configuration in `ToDoListLocalApplication` for development
**Spring Boot**: Follow given-when-then structure with AssertJ assertions
**.NET**: Use `IClassFixture<DbContextFixture>` for database tests with Testcontainers
**React**: Use Vitest + React Testing Library with `vi.fn()` mocks
**Angular**: Use `TestBed.configureTestingModule()` with Jasmine spies

### Frontend State Management
**React**: Context API with useReducer or React Query for server state
**Angular 20**: ONLY use Angular Signals (`signal()`, `computed()`, `effect()`) - NO NgRx, NO Akita

### Integration Points
- **Database**: All backends use PostgreSQL with Testcontainers for testing
- **OpenAPI Contract**: Controllers MUST match exact patterns from `docs/ToDoListOpenApi.json`
  - Task model: `{id, taskName, description, status, dueDate}`
  - Status enum: `TODO`, `IN_PROGRESS`, `DONE`
  - All endpoints under `/api/tasks`
- **Frontend Mockups**: UI MUST match designs in `docs/frontend/mockups/`
  - Single-screen layout as shown in `1_main_view.png`
  - Dialog overlays as shown in `2.3_add_task_dialog_view.png` and `3.2_edit_task_dialog_view.png`
- **CORS**: Configured for frontend integration on different ports
- **Swagger/OpenAPI**: Auto-generated documentation available on `/swagger` or `/swagger-ui.html`

## Workshop Context
This is an educational project focused on GitHub Copilot capabilities. Prioritize:
1. Generating code that follows established patterns in the codebase
2. Creating comprehensive tests with proper naming conventions
3. Maintaining consistency with the OpenAPI contract
4. Following the interface-based architecture already established

## Framework-Specific Instructions
Reference detailed patterns in:
- `.github/instructions/spring-boot.instructions.md`
- `.github/instructions/dotnet-architecture-good-practices.instructions.md`
- `.github/instructions/react.instructions.md`
- `.github/instructions/angular.instructions.md`