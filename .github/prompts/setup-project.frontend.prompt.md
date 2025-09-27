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
Create a comprehensive workshop frontend project setup for **${input:framework}** that implements the ToDo List application according to the Product Requirements Document (PRD), providing a modern, responsive single-screen UI that integrates with any backend implementation (Spring Boot, .NET, Node.js).

### 📋 **Design Requirements**

**CRITICAL**: This implementation must follow the specifications defined in:
- **PRD Document**: `docs/frontend/PRD_frontend.md` - Defines complete UI/UX requirements, single-screen architecture, and dialog-based interactions
- **Visual Mockups**: `docs/frontend/mockups/` - Contains detailed PNG images showing exact layouts, colors, and component positioning

### 🎨 **Key Design Principles from PRD**
1. **Single-Screen Application**: All tasks visible on main screen without navigation
2. **Dialog-Based Forms**: Create/Edit operations use overlay dialogs, NOT separate screens
3. **Top-Right Add Button**: "Add Task" button positioned in header top-right corner
4. **Inline Task Actions**: Each task shows Edit and Delete buttons directly
5. **Context Preservation**: Main task list always visible behind dialogs
6. **All Tasks View**: Default view shows ALL tasks in single scrollable list

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

### 🎨 **UI/UX Requirements (Per PRD)**
1. **Single Main Screen**: All tasks displayed in one view with sidebar filters
2. **Header with Add Button**: Top-right positioned "Add Task" button
3. **Task List with Inline Actions**: Each task shows Edit/Delete buttons
4. **Dialog Forms**: Create/Edit forms as overlay dialogs (not separate screens)
5. **Status Management**: Visual status indicators (CREATED, IN_PROGRESS, DONE)
6. **Sidebar Filters**: Status filters, search, and sort options
7. **Responsive Layout**: Mobile-first design with collapsible sidebar
8. **Loading States**: Proper loading indicators without blocking interface
9. **Error Handling**: User-friendly error messages with retry options
10. **Accessibility**: WCAG 2.1 AA compliance with keyboard navigation

### ✅ **Success Criteria**
1. **PRD Compliance**: Implementation matches all requirements in `docs/frontend/PRD_frontend.md`
2. **Single-Screen Design**: All tasks visible on main screen without navigation
3. **Dialog Overlays**: Create/Edit forms work as overlay dialogs
4. **API Integration**: Successfully connects to backend APIs (Spring Boot, .NET)
5. **Responsive Design**: Works on mobile, tablet, and desktop per PRD specs
6. **Task Actions**: Edit/Delete buttons functional on each task
7. **Header Layout**: Add Task button correctly positioned top-right
8. **Project Build**: Framework-specific build command succeeds
9. **Type Safety**: No TypeScript compilation errors
10. **Testing Suite**: Component tests cover all PRD requirements

### 📚 **Reference Materials**
- **PRD Document**: `docs/frontend/PRD_frontend.md` - Complete UI/UX requirements and specifications
- **Visual Mockups**: `docs/frontend/mockups/` - Detailed PNG images of all views:
  - `1_main_view.png` - Main task list screen layout
  - `2.1_add_task_main_view.png` - Add task dialog overlay
  - `2.2_add_task_date_view.png` - Date picker interaction
  - `3_edit_task_view.png` - Edit task dialog overlay
  - `4_delete_task_view.png` - Delete confirmation dialog
- **Backend APIs**: `docs/ToDoListOpenApi.json` - OpenAPI specification for backend integration
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
    baseUrl: 'https://localhost:5025',
    endpoints: { tasks: '/api/tasks', health: '/health' }
  }
};
```

---

**Usage Examples**:
- `/setup-project framework=react` - Setup React frontend project
- `/setup-project framework=angular` - Setup Angular frontend project