import { Component, inject } from '@angular/core';
import { Dialog } from '@angular/cdk/dialog';
import { TaskDialogComponent } from '../task-dialog/task-dialog.component';
import { TaskService } from '../../services/task.service';
import { TaskApiService } from '../../services/task-api.service';
import { Task, CreateTaskCommand } from '../../models/task.models';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent {
  private dialog = inject(Dialog);
  private taskService = inject(TaskService);
  private taskApiService = inject(TaskApiService);

  onAddTask() {
    const dialogRef = this.dialog.open<Task | null>(TaskDialogComponent, {
      data: null, // null means creating new task
      panelClass: 'task-dialog-panel',
      hasBackdrop: true,
      backdropClass: 'bg-black/50',
      width: '500px',
      maxWidth: '90vw',
    });

    dialogRef.closed.subscribe((result) => {
      if (result) {
        const createCommand: CreateTaskCommand = {
          taskName: result.taskName,
          description: result.description,
          status: result.status,
          dueDate: result.dueDate,
        };

        this.taskApiService.createTask(createCommand).subscribe({
          next: (createdTask) => {
            console.log('Task created successfully:', createdTask);
            this.taskService.addTask(createdTask);
          },
          error: (error) => {
            console.error('Error creating task:', error);
            // Fallback: add to local state if API fails
            this.taskService.addTask(result);
          },
        });
      }
    });
  }
}
