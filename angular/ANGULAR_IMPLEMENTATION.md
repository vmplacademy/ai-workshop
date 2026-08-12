# Angular 20 Todo App - Implementation Guide

## 🎯 Core Requirements
Single-screen todo application with dialog-based forms. NO routing, NO navigation.

## 🛠️ Setup Commands (Execute in Order)
```bash
# Create a workspace named "angular" with the app inside
ng new angular --standalone --routing=false --style=css --skip-git --ssr=false

# Navigate into the workspace
cd angular

# Install and configure Tailwind CSS 4
# Follow official Angular guide: https://tailwindcss.com/docs/installation/framework-guides/angular
npm install tailwindcss @tailwindcss/postcss postcss --force

# Clear the Angular placeholder template
# Remove all default Angular welcome content from src/app/app.component.html
echo '<router-outlet></router-outlet>' > src/app/app.component.html
# Or for standalone without routing:
echo '<div class="container mx-auto p-4"><!-- Your app here --></div>' > src/app/app.component.html

# Clear default styles from src/app/app.component.css
echo '' > src/app/app.component.css

# To run the application (the script picks the backend via proxy):
npm run start:spring   # or: npm run start:dotnet
# App will be available at http://localhost:4200
```

### Clean Slate Setup
After running `ng new`, Angular creates a welcome page with placeholder content. Clear it before implementing:

1. **Clear `src/app/app.component.html`** - Remove all default HTML
2. **Clear `src/app/app.component.css`** - Remove default component styles
3. **Update `src/app/app.component.ts`** - Set title to 'Todo App'

This gives you a clean canvas to start implementing the todo application.

### Tailwind Configuration
Follow the official Angular integration guide at: https://tailwindcss.com/docs/installation/framework-guides/angular

Key configuration steps:
1. Install Tailwind CSS and PostCSS:
```bash
npm install tailwindcss @tailwindcss/postcss postcss --force
```

2. Create `.postcssrc.json` in the project root:
```json
{
  "plugins": {
    "@tailwindcss/postcss": {}
  }
}
```

3. Import Tailwind in `src/styles.css`:
```css
@import "tailwindcss";
```

**Note**: The official guide ensures proper PostCSS integration with Angular's build system.

## 📁 Component Structure → Mockup Mapping

| Component | Mockup Reference | Purpose |
|-----------|-----------------|---------|
| `AppComponent` | Layout container | Main application shell |
| `HeaderComponent` | `1_main_view.png` top | Logo left, "Add Task" button right |
| `SidebarComponent` | `1_main_view.png` left | Status filters, search, sort |
| `TaskListComponent` | `1_main_view.png` center | Displays all tasks |
| `TaskItemComponent` | Task cards in main view | Individual task with inline actions |
| `TaskDialogComponent` | `2.3_add_task_dialog_view.png`, `3.2_edit_task_dialog_view.png` | Create/Edit overlay |

## 💾 State Management - Use Angular Signals ONLY
```typescript
// app.service.ts - NO NgRx, NO Akita needed
import { Injectable, signal, computed } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class TaskService {
  tasks = signal<Task[]>([]);
  filter = signal<FilterType>('all');

  filteredTasks = computed(() => {
    const allTasks = this.tasks();
    const currentFilter = this.filter();
    return currentFilter === 'all' ? allTasks : allTasks.filter(t => t.status === currentFilter);
  });
}
```

## 🏗️ Modern Angular 20 Syntax
```typescript
// Use new control flow - NO *ngIf, *ngFor
@Component({
  template: `
    <div class="grid grid-cols-[250px_1fr] h-screen">
      <app-sidebar />
      <main class="p-6 overflow-y-auto">
        @if (loading()) {
          <div class="text-center py-8">Loading...</div>
        } @else if (tasks().length === 0) {
          <div class="text-center py-12">
            <p>No tasks yet</p>
            <button (click)="openDialog()" class="mt-4 btn-primary">Create First Task</button>
          </div>
        } @else {
          @for (task of filteredTasks(); track task.id) {
            <app-task-item [task]="task" />
          }
        }
      </main>
    </div>
  `
})
```

## 📦 Required Dependencies
```json
{
  "dependencies": {
    "@angular/animations": "^20.0.0",
    "@angular/cdk": "^20.0.0",
    "@angular/common": "^20.0.0",
    "@angular/core": "^20.0.0",
    "@angular/forms": "^20.0.0",
    "@angular/platform-browser": "^20.0.0"
  },
  "devDependencies": {
    "tailwindcss": "^4.0.0",
    "typescript": "~5.5.0"
  }
}
```

## 🎨 Dialog Implementation
```typescript
// Use Angular CDK Dialog - NO custom modals
import { Dialog } from '@angular/cdk/dialog';

openTaskDialog(task?: Task) {
  const dialogRef = this.dialog.open(TaskDialogComponent, {
    data: task,
    panelClass: 'task-dialog',
    hasBackdrop: true,
    backdropClass: 'bg-black/50'
  });
}
```

## 🔌 API Integration
```typescript
// environment.ts — relative URL; the dev-server proxy picks the backend
// npm run start:spring → :8080   |   npm run start:dotnet → :5025
export const environment = {
  apiUrl: '/api',
  endpoints: {
    tasks: '/tasks'
  }
};

// task.service.ts
getTasks() {
  return this.http.get<Task[]>(`${environment.apiUrl}${environment.endpoints.tasks}`);
}
```

## ❌ DO NOT USE
- Angular Router (no routing needed)
- NgRx/Akita (use Signals instead)
- Modules (use standalone components)
- Legacy directives (*ngIf, *ngFor)
- Custom modal implementations

## ✅ Task Model
```typescript
interface Task {
  id: string | number; // both backends are supported, ids differ in type
  taskName: string;
  description?: string;
  status: 'CREATED' | 'IN_PROGRESS' | 'DONE';
  dueDate: string;
}
```

## 🎯 Success Criteria
1. Single screen with all tasks visible
2. Dialog overlays for create/edit (CDK Dialog)
3. "Add Task" button top-right in header
4. Each task shows Edit/Delete buttons
5. Sidebar filters working with Signals
6. Tailwind 4 styling throughout
7. NO routing anywhere in the app