---
description: Setup AI Workshop backend project for specified framework with comprehensive requirements and success criteria
mode: ask
---

# Setup AI Workshop Backend Project

Define the task to achieve based on the specified backend framework, including specific requirements, constraints, and success criteria for workshop implementation.

## Input Parameters

**Framework**: ${input:framework:spring-boot} (Options: `spring-boot`, `dotnet`, `nodejs`)

## Task Definition Template

Based on the selected backend framework (`${input:framework}`), this prompt will generate:

### 🎯 **Primary Objective**
Create a comprehensive workshop backend project setup for **${input:framework}** that implements a contract-first API based on OpenAPI definitions. The goal is to create backend APIs for clients following API-first development principles with proper workshop structure and GitHub Copilot integration examples.

**⚠️ IMPORTANT**:
- Implementation must be done **step-by-step** with user confirmation at each phase. Wait for user approval before proceeding to the next step.
- Work within the current VS Code workspace. Do NOT create or open a new workspace during setup.

### 📋 **Framework-Specific Requirements**

Each framework follows specific guidelines and best practices detailed in dedicated instruction files:

#### **Spring Boot** (`framework=spring-boot`)
📖 **Full Guidelines**: [Spring Boot Instructions](../instructions/spring-boot.instructions.md)
- **Technology Stack**: Java 21, Spring Boot 3.5.5, Maven, PostgreSQL
- **Architecture**: Layered architecture with @RestController, @Service, @Repository
- **Testing**: JUnit 5, Mockito, @SpringBootTest, @DataJpaTest
- **Database**: PostgreSQL with JPA/Hibernate
- **Documentation**: Swagger/OpenAPI 3.0

#### **.NET** (`framework=dotnet`)
📖 **Full Guidelines**: 
- [C# Instructions](../instructions/csharp.instructions.md)
- [.NET Architecture Guidelines](../instructions/dotnet-architecture-good-practices.instructions.md)  
- [.NET Framework Guidelines](../instructions/dotnet-framework.instructions.md)
- **Technology Stack**: .NET 9.0, ASP.NET Core, Entity Framework Core, PostgreSQL
- **Architecture**: Clean Architecture with Controllers, Services, Repositories
- **Testing**: xUnit, Moq, Microsoft.AspNetCore.Mvc.Testing
- **Database**: PostgreSQL with Entity Framework Core
- **Documentation**: Swashbuckle.AspNetCore (Swagger)

### 🚫 **Universal Backend Constraints**
1. **Contract-First Development**: Start with OpenAPI specification, then implement
2. **Step-by-Step Implementation**: Each phase requires user confirmation before proceeding
3. **Follow framework-specific naming conventions** and best practices
4. **Strict adherence to OpenAPI contract** - all endpoints must match specification
5. **Use GitHub Copilot integration** examples throughout
6. **Support Docker containerization** for local development
7. **Include comprehensive testing strategy** (contract testing, unit, integration, API tests)
8. **Follow workshop branching strategy** (initial, task-1, task-2, etc.)
9. **PostgreSQL database** as primary data store
10. **RESTful API design** with proper HTTP status codes matching OpenAPI spec
11. **OpenAPI/Swagger documentation** as the source of truth for API design

### ✅ **Success Criteria**
1. **Project Compilation**: Framework-specific build command succeeds
2. **Application Startup**: Local development server runs successfully
3. **OpenAPI Compliance**: All endpoints match the OpenAPI specification exactly
4. **Database Integration**: Database operations work with PostgreSQL
5. **Contract Testing**: API responses validate against OpenAPI schema
6. **API Documentation**: Swagger/OpenAPI accessible and matches implementation
7. **Workshop Materials**: Step-by-step guide focuses on API-first development
8. **GitHub Copilot Examples**: Practical prompts for contract-first development
9. **CORS Configuration**: Proper setup for frontend integration
10. **Error Handling**: Consistent error responses matching OpenAPI error schemas
11. **User Confirmation**: Each implementation phase completed with user approval

### 📚 **Reference Materials**
- **OpenAPI Specification**: Primary source of truth for API contract definition
- **Workshop Structure**: Structured learning approach with progressive complexity
- **API Contract**: OpenAPI specification in docs folder serves as implementation guide
- **Testing Patterns**: Contract testing, API testing, and comprehensive test coverage
- **Database Schema**: PostgreSQL schema design aligned with API models

### 📦 **Expected Backend Deliverables**
- [ ] Complete backend project structure with proper layer separation
- [ ] Build configuration (pom.xml, .csproj, package.json, etc.)
- [ ] Application entry point with proper configuration
- [ ] Database connection and entity/model definitions
- [ ] REST API controllers with CRUD operations for Task entity
- [ ] Service layer with business logic implementation
- [ ] Repository/data access layer with database operations
- [ ] Comprehensive test suites (unit, integration, API tests)
- [ ] README.md and GETTING_STARTED.md documentation
- [ ] Framework-specific .gitignore configuration
- [ ] Docker configuration (Dockerfile, docker-compose.yml)
- [ ] Database migrations and seed data
- [ ] API documentation (Swagger/OpenAPI)
- [ ] Environment configuration (dev, test, prod)
- [ ] Logging and monitoring setup

### 🔄 **Step-by-Step Implementation Process**

**MANDATORY WORKFLOW**: Each phase requires explicit user confirmation before proceeding.

#### **Phase 1: Project Initialization**
1. Present project structure plan
2. **WAIT FOR USER APPROVAL** ✋
3. Create solution/project files and folder structure
4. Configure build system and dependencies

#### **Phase 2: OpenAPI Contract Setup**
1. Review/create OpenAPI specification
2. **WAIT FOR USER APPROVAL** ✋  
3. Set up API documentation tooling
4. Generate contract validation framework

#### **Phase 3: Database Layer**
1. Design database schema based on OpenAPI models
2. **WAIT FOR USER APPROVAL** ✋
3. Implement entities and database context
4. Create database migrations

#### **Phase 4: Repository Layer**
1. Define repository interfaces
2. **WAIT FOR USER APPROVAL** ✋
3. Implement database operations
4. Add repository unit tests

#### **Phase 5: Service Layer**
1. Define service interfaces and business logic
2. **WAIT FOR USER APPROVAL** ✋
3. Implement service classes
4. Add service unit tests

#### **Phase 6: API Controllers**
1. Create controller structure matching OpenAPI
2. **WAIT FOR USER APPROVAL** ✋
3. Implement CRUD endpoints
4. Add API integration tests

#### **Phase 7: Testing & Documentation**
1. Complete test coverage verification
2. **WAIT FOR USER APPROVAL** ✋
3. Generate final documentation
4. Validate against OpenAPI contract

**⚠️ IMPORTANT**: 
- Stop at each phase and ask: *"Ready to proceed with [Next Phase]?"*
- Only continue when user explicitly confirms
- Provide clear summary of what will be implemented in each phase

### 🏗️ **Common Backend Architecture**

All backend implementations should follow this structure:

```
{framework}/
├── README.md
├── GETTING_STARTED.md
├── .gitignore
├── docker-compose.yml
├── src/ (or equivalent)
│   ├── controllers/     # REST API endpoints
│   ├── services/        # Business logic layer
│   ├── repositories/    # Data access layer
│   ├── models/entities/ # Domain models
│   ├── dto/             # Data Transfer Objects
│   └── config/          # Configuration files
├── tests/
│   ├── unit/           # Unit tests
│   ├── integration/    # Integration tests
│   └── api/            # API/E2E tests
```
---

**Usage Examples**:
- `/setup-project framework=spring-boot` - Setup Spring Boot backend project
- `/setup-project framework=dotnet` - Setup .NET Web API backend project  