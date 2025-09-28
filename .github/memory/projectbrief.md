# Project Brief

**Project Name:** AI Workshop - Multi-Stack ToDo List Application
**Purpose:** Educational platform for mastering GitHub Copilot through hands-on development
**Created:** 2025-09-28
**Status:** Active Development

## Core Mission

Transform how developers code by mastering GitHub Copilot's AI assistance across different technology stacks through building production-ready ToDo applications.

## Project Scope

### Primary Deliverables
1. **Spring Boot Backend** (Java 21 + Spring Boot 3.5.5)
   - Contract-first development using OpenAPI specification
   - Layered architecture with proper separation of concerns
   - PostgreSQL integration with JPA/Hibernate
   - Comprehensive testing with Testcontainers

2. **.NET Backend** (.NET 8.0)
   - Clean Architecture implementation
   - Interface-based contracts for testability
   - Entity Framework Core with PostgreSQL
   - Unit and integration testing

3. **React Frontend** (React 18 + TypeScript + Vite)
   - Modern React patterns and hooks
   - Tailwind CSS for styling
   - Vitest + React Testing Library for testing
   - API integration with backend services

4. **Angular Frontend** (Angular + TypeScript)
   - Component-based architecture
   - Tailwind CSS for styling
   - Integration with OpenAPI specification
   - Mobile-responsive design

### Shared Foundation
- **OpenAPI Specification** (`docs/ToDoListOpenApi.json`) - Single source of truth for API contracts
- **Visual Mockups** - Complete UI/UX designs with Tailwind CSS implementation
- **GitHub Copilot Integration** - Custom instructions and prompts for each technology stack
- **Educational Documentation** - Comprehensive guides for workshop phases

## Success Criteria

### Technical Goals
- ✅ All backends implement identical API contracts from OpenAPI spec
- ✅ Frontend applications consume backend APIs seamlessly
- ✅ Comprehensive test coverage across all implementations
- ✅ Production-ready code quality and architecture

### Educational Goals
- 🎯 Demonstrate GitHub Copilot's code generation capabilities
- 🎯 Show smart completions and context-aware suggestions
- 🎯 Teach test automation with AI assistance
- 🎯 Enable documentation generation and maintenance
- 🎯 Guide refactoring and optimization workflows

## Key Constraints

1. **Contract Adherence** - All implementations must follow the OpenAPI specification exactly
2. **Technology Versions** - Use specified versions for consistency across workshop environments
3. **Architecture Patterns** - Each stack must demonstrate its recommended architectural approach
4. **Workshop Flow** - Code must be structured to support progressive learning phases

## Workshop Phases

1. **Setup & Fundamentals** - Configure Copilot, learn prompt engineering
2. **Backend Development** - Generate controllers, services, tests from OpenAPI
3. **Frontend Development** - Create React/Angular components with AI assistance
4. **Advanced Techniques** - Refactoring, optimization, documentation generation

## Repository Structure

```
├── spring-boot/         # Java backend implementation
├── dotnet/              # .NET backend implementation
├── react/               # React frontend implementation
├── angular/             # Angular frontend implementation (in progress)
├── docs/                # API specs, mockups, and documentation
├── .github/             # Copilot instructions and automation prompts
└── .memory/             # Memory Bank for AI context persistence
```

This project serves as both a functional multi-stack application and a comprehensive learning platform for AI-assisted development workflows.