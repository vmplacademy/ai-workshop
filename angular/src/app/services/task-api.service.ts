import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError, catchError } from 'rxjs';
import { Task, CreateTaskCommand, UpdateTaskCommand } from '../models/task.models';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TaskApiService {
  private http = inject(HttpClient);
  private readonly baseUrl = environment.apiUrl;
  private readonly tasksEndpoint = `${this.baseUrl}${environment.endpoints.tasks}`;

  /**
   * Get all tasks from the backend
   */
  getAllTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(this.tasksEndpoint)
      .pipe(catchError(this.handleError));
  }

  /**
   * Get a specific task by ID
   */
  getTaskById(id: string | number): Observable<Task> { // Support both string and number IDs
    return this.http.get<Task>(`${this.tasksEndpoint}/${id}`)
      .pipe(catchError(this.handleError));
  }

  /**
   * Create a new task
   */
  createTask(command: CreateTaskCommand): Observable<Task> {
    return this.http.post<Task>(this.tasksEndpoint, command)
      .pipe(catchError(this.handleError));
  }

  /**
   * Update an existing task
   */
  updateTask(command: UpdateTaskCommand): Observable<Task> {
    return this.http.put<Task>(`${this.tasksEndpoint}/${command.id}`, command)
      .pipe(catchError(this.handleError));
  }

  /**
   * Delete a task by ID
   */
  deleteTask(id: string | number): Observable<void> { // Support both string and number IDs
    return this.http.delete<void>(`${this.tasksEndpoint}/${id}`)
      .pipe(catchError(this.handleError));
  }

  /**
   * Health check endpoint to test backend connectivity
   */
  healthCheck(): Observable<any> {
    // Different backends have different health check endpoints
    const healthEndpoints = {
      springBoot: '/actuator/health',
      dotnet: '/health',
      nodejs: '/health'
    };
    
    // Default to Spring Boot health check
    const healthUrl = `${this.baseUrl.replace('/api', '')}${healthEndpoints.springBoot}`;
    
    return this.http.get(healthUrl)
      .pipe(catchError(this.handleError));
  }

  /**
   * Handle HTTP errors
   */
  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred';
    
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Client Error: ${error.error.message}`;
    } else {
      // Backend error
      switch (error.status) {
        case 400:
          errorMessage = 'Bad Request: Please check your input data.';
          break;
        case 404:
          errorMessage = 'Not Found: The requested resource was not found.';
          break;
        case 500:
          errorMessage = 'Internal Server Error: Please try again later.';
          break;
        case 0:
          errorMessage = 'Network Error: Please check your connection and that the backend server is running.';
          break;
        default:
          errorMessage = `Server Error: ${error.status} - ${error.message}`;
      }
    }
    
    console.error('TaskApiService Error:', error);
    return throwError(() => new Error(errorMessage));
  }

  /**
   * Switch backend configuration at runtime
   */
  switchBackend(backendType: 'springBoot' | 'dotnet' | 'nodejs') {
    // This would require more complex implementation to dynamically change the base URL
    console.log(`Switching to ${backendType} backend`);
    // In a real application, you might want to update the environment or reload the app
  }
}