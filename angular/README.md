# Angular 20 Todo App Frontend

A modern, responsive single-screen todo application built with Angular 20, TypeScript, and Tailwind CSS 4.0. This frontend implements the complete Todo List specification from the PRD document with dialog-based forms and seamless backend integration.

## 🚀 Features

### ✨ Core Functionality
- **Single-Screen Design**: All tasks visible on main screen without navigation
- **Dialog-Based Forms**: Create and edit tasks using overlay modals (Angular CDK Dialog)
- **Real-time Filtering**: Filter tasks by status (All, To Do, In Progress, Done)
- **Advanced Search**: Search tasks by name or description
- **Flexible Sorting**: Sort by due date, task name, or status with ascending/descending order
- **Inline Actions**: Edit and delete buttons directly on each task card
- **Status Management**: Click task checkbox to cycle CREATED → IN_PROGRESS → DONE → CREATED

### 🎨 UI/UX Design
- **Modern Design**: Clean interface following mockup specifications
- **Mobile-First Responsive**: Optimized for mobile, tablet, and desktop
- **Tailwind CSS 4.0**: Utility-first styling with custom components
- **Intuitive Interactions**: Hover effects, loading states, and smooth transitions
- **Accessibility**: Keyboard navigation and screen reader support

### 🏗️ Modern Angular 20 Architecture
- **Standalone Components**: No NgModules required
- **Angular Signals**: Reactive state management (NO NgRx/Akita)
- **Modern Control Flow**: Uses `@if`, `@for`, `@switch` syntax (no `*ngIf`, `*ngFor`)
- **Reactive Forms**: Angular reactive forms with validation
- **Angular CDK**: Dialog components with backdrop and positioning
- **TypeScript 5.9**: Full type safety with interfaces and enums

## 📁 Project Structure

```
angular/
├── src/app/
│   ├── components/              # Standalone UI components
│   │   ├── header/             # Header with logo and Add Task button
│   │   ├── sidebar/            # Filters, search, and sort controls
│   │   ├── task-list/          # Main task display area
│   │   ├── task-item/          # Individual task cards
│   │   └── task-dialog/        # Create/Edit modal forms
│   ├── models/
│   │   └── task.models.ts      # TypeScript interfaces and types
│   ├── services/
│   │   ├── task.service.ts     # Angular Signals state management
│   │   └── task-api.service.ts # HTTP client for backend APIs
│   └── environments/           # Backend configuration
├── ANGULAR_IMPLEMENTATION.md   # Detailed implementation guide
├── package.json               # Dependencies and scripts
├── proxy.conf.spring.json     # Dev-server proxy → Spring Boot (:8080)
├── proxy.conf.dotnet.json     # Dev-server proxy → .NET (:5025)
└── .postcssrc.json           # PostCSS config for Tailwind 4
```

## 🛠️ Technology Stack

- **Framework**: Angular 20.3
- **Language**: TypeScript 5.9
- **Styling**: Tailwind CSS 4.0
- **UI Components**: Angular CDK (Dialog, Layout)
- **Forms**: Angular Reactive Forms
- **State Management**: Angular Signals
- **HTTP Client**: Angular HttpClient
- **Build Tool**: Angular CLI + Vite
- **Package Manager**: npm

## 📦 Installation & Setup

### Prerequisites
- Node.js 20.19+ and npm (required by Angular 20)
- Angular CLI 20+

### Quick Start
```bash
# Navigate to the angular directory
cd angular

# Install dependencies
npm install

# Start the dev server against the backend of your choice
npm run start:dotnet     # .NET (:5025) — also the default `npm start`
npm run start:spring     # Spring Boot (:8080)

# Visit the application
open http://localhost:4200
```

### Backend Integration
Choosing a backend means choosing a script, not editing code. `environment.ts` holds the
relative `apiUrl: '/api'`; the dev-server proxy decides which backend serves it:

| Script | Proxy config | Target |
|---|---|---|
| `npm run start:spring` | `proxy.conf.spring.json` | `http://localhost:8080` |
| `npm run start:dotnet` | `proxy.conf.dotnet.json` | `http://localhost:5025` |

With no backend running, `AppComponent` falls back to built-in demo data — the connection
error logged to the console is expected in that case.

## 🎯 Component Overview

### HeaderComponent
- Logo and app title on the left
- "Add Task" button on the right (matches mockup specifications)
- Opens TaskDialog for creating new tasks

### SidebarComponent
- **Status Filters**: All, To Do, In Progress, Done with task counts
- **Search Box**: Real-time filtering by task name/description  
- **Sort Controls**: Sort by due date, name, or status (asc/desc)
- **Quick Stats**: Overview of task distribution

### TaskListComponent
- Displays filtered and sorted tasks using modern Angular `@for`
- Empty states for no tasks or no search results
- Loading states with spinner animation
- Responsive grid layout

### TaskItemComponent
- Task card with checkbox for status toggling
- Inline Edit and Delete buttons
- Due date with color-coded urgency (overdue, due today, due soon)
- Task description with line clamping
- Status badges with color coding

### TaskDialogComponent
- Angular CDK Dialog for create/edit forms
- Reactive forms with real-time validation
- Date picker with default to tomorrow
- Status dropdown with visual indicators

## 🔗 Backend Integration

### Supported Backends
- **Spring Boot**: `http://localhost:8080/api`
- **.NET**: `http://localhost:5025/api`

### API Endpoints
All endpoints follow the OpenAPI specification in `docs/ToDoListOpenApi.json`:

```typescript
GET    /api/tasks          // Get all tasks
POST   /api/tasks          // Create new task
GET    /api/tasks/{id}     // Get task by ID
PUT    /api/tasks/{id}     // Update existing task
DELETE /api/tasks/{id}     // Delete task
```

## 🎨 Design Implementation

### Mockup Compliance
The implementation follows the exact specifications from `docs/frontend/mockups/`:

1. **Main View** (`1_main_view.png`): Single-screen layout with sidebar and task list
2. **Add Task Dialog** (`2.3_add_task_dialog_view.png`): Modal form overlay
3. **Edit Task Dialog** (`3.2_edit_task_dialog_view.png`): Pre-filled edit form
4. **Delete Confirmation**: Built-in browser confirmation dialog

### Responsive Breakpoints
- **Mobile** (< 768px): Collapsed sidebar, full-width task list
- **Tablet** (768px - 1024px): Reduced sidebar width
- **Desktop** (> 1024px): Full sidebar with optimal task card layout

## 🧪 Development

### Available Scripts
```bash
npm start             # Dev server, proxied to .NET (alias of start:dotnet)
npm run start:spring  # Dev server, proxied to Spring Boot
npm run start:dotnet  # Dev server, proxied to .NET
npm run build         # Build for production
npm run watch         # Build with file watching
npm test              # Run unit tests (opens Chrome, watch mode)
npm run format        # Prettier over src/

# Non-interactive run — for CI and agents
npx ng test --watch=false --browsers=ChromeHeadless
```

### Modern Angular Features Used
- **Standalone Components**: No modules needed
- **Angular Signals**: `signal()`, `computed()`, `effect()`
- **New Control Flow**: `@if`, `@for`, `@switch` syntax
- **Injectable Services**: Dependency injection with `inject()`
- **Reactive Forms**: FormBuilder with validation

## 🌟 Key Features Demonstration

### State Management with Signals
```typescript
// Reactive state that automatically updates UI
tasks = signal<Task[]>([]);
filter = signal<FilterType>('all');

filteredTasks = computed(() => {
  const allTasks = this.tasks();
  const currentFilter = this.filter();
  return currentFilter === 'all' 
    ? allTasks 
    : allTasks.filter(t => t.status === currentFilter);
});
```

### Modern Angular Template Syntax
```html
@if (loading) {
  <div class="loading-spinner">Loading...</div>
} @else if (tasks().length === 0) {
  <div class="empty-state">No tasks yet</div>
} @else {
  @for (task of filteredTasks(); track task.id) {
    <app-task-item [task]="task" />
  }
}
```

## 🚀 Production Build

```bash
# Build for production
ng build --configuration production

# Serve built files (requires http-server)
npx http-server dist/angular -p 4200
```

## 🎯 Success Criteria Met

✅ **Single-screen design** - All tasks visible without navigation  
✅ **Dialog overlays** - Create/Edit forms as modals  
✅ **Header layout** - Add Task button positioned top-right  
✅ **Inline actions** - Edit/Delete buttons on each task  
✅ **Angular Signals** - Modern reactive state management  
✅ **Standalone components** - No NgModules used  
✅ **Mobile responsive** - Mobile-first design approach  
✅ **Backend ready** - API integration for multiple backends  
✅ **PRD compliant** - Matches all requirements and mockups  

**Ready for production deployment!** 🚢
