import { Component, OnInit, inject } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';
import { HeaderComponent } from './components/header/header.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { TaskListComponent } from './components/task-list/task-list.component';
import { TaskService } from './services/task.service';
import { TaskApiService } from './services/task-api.service';
import { Task } from './models/task.models';

@Component({
  selector: 'app-root',
  imports: [HeaderComponent, SidebarComponent, TaskListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'Todo App';
  private taskService = inject(TaskService);
  private taskApiService = inject(TaskApiService);

  ngOnInit() {
    // Load tasks from the .NET backend API
    this.loadTasksFromApi();
  }

  private loadTasksFromApi() {
    this.taskApiService.getAllTasks().subscribe({
      next: (tasks) => {
        console.log('Loaded tasks from API:', tasks);
        this.taskService.setTasks(tasks);
      },
      error: (error) => {
        console.error('Error loading tasks from API:', error);
        console.log('Falling back to sample data for development...');
        // Fallback to sample data if API is not available
        this.loadSampleData();
      }
    });
  }

  private loadSampleData() {
    const sampleTasks: Task[] = [
      {
        id: '1',
        taskName: 'Complete Angular Frontend',
        description: 'Implement all components for the todo application using Angular 20 with Tailwind CSS.',
        status: 'IN_PROGRESS',
        dueDate: '2025-10-05'
      },
      {
        id: '2',
        taskName: 'Review Code',
        description: 'Conduct code review for the new features and ensure quality standards.',
        status: 'TODO',
        dueDate: '2025-10-03'
      },
      {
        id: '3',
        taskName: 'Setup CI/CD Pipeline',
        description: 'Configure automated testing and deployment pipeline for the project.',
        status: 'DONE',
        dueDate: '2025-09-28'
      },
      {
        id: '4',
        taskName: 'Write Documentation',
        description: 'Create comprehensive documentation for the API endpoints and frontend components.',
        status: 'TODO',
        dueDate: '2025-10-10'
      },
      {
        id: '5',
        taskName: 'Database Optimization',
        description: 'Optimize database queries and add proper indexing for better performance.',
        status: 'IN_PROGRESS',
        dueDate: '2025-10-07'
      }
    ];

    this.taskService.setTasks(sampleTasks);
  }
}
