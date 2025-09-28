import { Injectable } from '@angular/core';

export interface Task {
  title: string;
  status: string;
  dueDate: string;
  description: string;
}

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private tasks: Task[] = [
    {
      title: 'Design new landing page',
      status: 'in-progress',
      dueDate: 'Today at 5:00 PM',
      description: 'Create a modern, responsive landing page with hero section, features overview, and call-to-action buttons.'
    },
    {
      title: 'Review project requirements',
      status: 'created',
      dueDate: 'Overdue (2 days)',
      description: 'Go through all project requirements documents and create a summary for the team meeting.'
    },
    {
      title: 'Set up development environment',
      status: 'done',
      dueDate: 'Completed Yesterday',
      description: 'Install Node.js, set up project structure, configure linting and testing tools.'
    },
    {
      title: 'Implement user authentication',
      status: 'in-progress',
      dueDate: 'Oct 1 at 10:00 AM',
      description: 'Build login/signup forms, implement JWT authentication, add password reset functionality.'
    },
    {
      title: 'Write API documentation',
      status: 'created',
      dueDate: 'Due in 5 days',
      description: 'Document all API endpoints with examples, error codes, and response formats using OpenAPI specification.'
    },
    {
      title: 'Test mobile responsiveness',
      status: 'in-progress',
      dueDate: 'Due Today',
      description: 'Test application on various mobile devices and screen sizes, fix responsive design issues.'
    },
    {
      title: 'Create database schema',
      status: 'done',
      dueDate: 'Completed 3 days ago',
      description: 'Design and implement database tables for users, tasks, and relationships with proper indexing.'
    },
    {
      title: 'Plan deployment strategy',
      status: 'created',
      dueDate: '',
      description: 'Plan deployment pipeline and strategy for production releases.'
    }
  ];

  getTasks(): Task[] {
    return this.tasks;
  }

  addTask(task: Task) {
    this.tasks.push(task);
  }

  updateTask(updatedTask: Task) {
    const idx = this.tasks.findIndex(t => t.title === updatedTask.title);
    if (idx !== -1) {
      this.tasks[idx] = { ...updatedTask };
    }
  }
}