---
description: Setup AI Workshop frontend project for specified framework with comprehensive requirements and success criteria
mode: ask
---

# Setup AI Workshop Frontend Project

Define the task to achieve based on the specified frontend framework, including specific requirements, constraints, and success criteria for workshop implementation.

## Input Parameters

**Framework**: ${input:framework:react} (Options: `react`, `angular`, `vue`, `svelte`)

## Task Definition Template

Based on the selected frontend framework (`${input:framework}`), this prompt will generate:

### 🎯 **Primary Objective**
Create a comprehensive workshop frontend project setup for **${input:framework}** that provides a modern, responsive UI for the TodoList application and integrates with any backend implementation (Spring Boot, .NET, Node.js).

### 📋 **Framework-Specific Requirements**

Each framework follows specific guidelines and best practices detailed in dedicated instruction files:

#### **React** (`framework=react`)
📖 **Full Guidelines**: [React Instructions](../instructions/react.instructions.md)
- **Technology Stack**: React 18, Vite, TypeScript, Tailwind CSS
- **Architecture**: Component-based with hooks and context
- **Testing**: Vitest, React Testing Library
- **State Management**: React Context API or Zustand
- **Routing**: React Router v6
- **HTTP Client**: Axios or Fetch API

#### **Angular** (`framework=angular`)
📖 **Full Guidelines**: [Angular Instructions](../instructions/angular.instructions.md)
- **Technology Stack**: Angular 18, TypeScript, Angular Material
- **Architecture**: Component-based with services and dependency injection
- **Testing**: Jasmine, Karma, Angular Testing Utilities
- **State Management**: NgRx or Akita
- **Routing**: Angular Router
- **HTTP Client**: Angular HttpClient

### 🚫 **Universal Frontend Constraints**
1. **Backend Agnostic**: Must work with Spring Boot, .NET, and Node.js backends
2. **Follow framework-specific conventions** and best practices
3. **Responsive Design**: Mobile-first approach with proper breakpoints
4. **Use GitHub Copilot integration** examples throughout
5. **TypeScript**: Mandatory for type safety and better development experience
6. **Modern Build Tools**: Vite, Webpack, or framework-specific CLI
7. **Component Testing**: Comprehensive component and integration tests
8. **Accessibility**: WCAG 2.1 AA compliance where possible
9. **Performance**: Code splitting, lazy loading, and optimization
10. **API Integration**: Configurable backend endpoints

### 🎨 **UI/UX Requirements**
1. **Task List View**: Display all tasks with status indicators
2. **Task Creation**: Form to add new tasks with validation
3. **Task Editing**: Inline or modal editing capabilities
4. **Task Status**: Visual status management (Pending, In Progress, Completed)
5. **Search/Filter**: Ability to filter tasks by status or search by text
6. **Responsive Layout**: Works on mobile, tablet, and desktop
7. **Loading States**: Proper loading indicators for API calls
8. **Error Handling**: User-friendly error messages and recovery
9. **Dark Mode**: Optional dark/light theme toggle
10. **Keyboard Navigation**: Accessible keyboard shortcuts

### ✅ **Success Criteria**
1. **Project Build**: Framework-specific build command succeeds
2. **Development Server**: Local dev server runs successfully
3. **API Integration**: Successfully connects to backend APIs
4. **Component Rendering**: All UI components render correctly
5. **Testing Suite**: All component and integration tests pass
6. **Responsive Design**: Works across different screen sizes
7. **Type Safety**: No TypeScript compilation errors
8. **Performance**: Meets Core Web Vitals standards
9. **Accessibility**: Basic accessibility requirements met
10. **Documentation**: Component documentation and usage examples

### 📚 **Reference Materials**
- **Backend APIs**: OpenAPI specifications from backend implementations
- **Design System**: Consistent UI components and styling
- **Component Library**: Framework-specific component libraries
- **Testing Patterns**: Best practices for component testing
- **Performance Guidelines**: Framework-specific optimization techniques

### 📦 **Expected Frontend Deliverables**
- [ ] Complete frontend project structure with proper component organization
- [ ] Build configuration (package.json, vite.config.ts, etc.)
- [ ] Application entry point and routing setup
- [ ] Component library with reusable UI components
- [ ] Task management components (List, Create, Edit, Delete)
- [ ] API service layer for backend communication
- [ ] State management implementation
- [ ] Comprehensive component test suites
- [ ] README.md and development documentation
- [ ] Framework-specific .gitignore configuration
- [ ] Docker configuration for deployment
- [ ] Environment configuration for different backends
- [ ] Responsive CSS/styling implementation
- [ ] Accessibility features and keyboard navigation
- [ ] Error boundaries and error handling

### 🏗️ **Common Frontend Architecture**

All frontend implementations should follow this structure:

```
{framework}/
├── README.md
├── GETTING_STARTED.md
├── .gitignore
├── package.json
├── vite.config.ts (or equivalent)
├── tsconfig.json
├── src/
│   ├── components/      # Reusable UI components
│   │   ├── common/      # Generic components
│   │   └── task/        # Task-specific components
│   ├── pages/           # Page components
│   ├── services/        # API communication
│   ├── stores/          # State management
│   ├── types/           # TypeScript type definitions
│   ├── utils/           # Utility functions
│   ├── hooks/           # Custom hooks (React/Vue)
│   └── assets/          # Static assets
├── tests/
│   ├── unit/           # Unit component tests
│   ├── integration/    # Integration tests
│   └── e2e/            # End-to-end tests
├── public/             # Static public assets
```

### 🔌 **Backend Integration**

Frontend should support multiple backend endpoints:

```typescript
// Environment configuration example
interface ApiConfig {
  baseUrl: string;
  endpoints: {
    tasks: string;
    health: string;
  };
}

const API_CONFIGS: Record<string, ApiConfig> = {
  'spring-boot': {
    baseUrl: 'http://localhost:8080',
    endpoints: { tasks: '/api/tasks', health: '/actuator/health' }
  },
  'dotnet': {
    baseUrl: 'https://localhost:7001',
    endpoints: { tasks: '/api/tasks', health: '/health' }
  },
  'nodejs': {
    baseUrl: 'http://localhost:3000',
    endpoints: { tasks: '/api/tasks', health: '/health' }
  }
};
```

---

**Usage Examples**:
- `/setup-project framework=react` - Setup React frontend project
- `/setup-project framework=angular` - Setup Angular frontend project