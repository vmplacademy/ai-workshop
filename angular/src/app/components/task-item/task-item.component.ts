import { Component, Input, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Dialog } from '@angular/cdk/dialog';
import { Task, UpdateTaskCommand } from '../../models/task.models';
import { TaskService } from '../../services/task.service';
import { TaskApiService } from '../../services/task-api.service';
import { TaskDialogComponent } from '../task-dialog/task-dialog.component';

@Component({
  selector: 'app-task-item',
  imports: [CommonModule],
  templateUrl: './task-item.component.html',
  styleUrl: './task-item.component.css'
})
export class TaskItemComponent {
  @Input({ required: true }) task!: Task;
  
  private taskService = inject(TaskService);
  private taskApiService = inject(TaskApiService);
  private dialog = inject(Dialog);

  getStatusConfig(status: string) {
    switch (status) {
      case 'TODO':
        return {
          label: 'To Do',
          bgColor: 'bg-red-100',
          textColor: 'text-red-800',
          dotColor: 'bg-red-500'
        };
      case 'IN_PROGRESS':
        return {
          label: 'In Progress',
          bgColor: 'bg-yellow-100',
          textColor: 'text-yellow-800',
          dotColor: 'bg-yellow-500'
        };
      case 'DONE':
        return {
          label: 'Done',
          bgColor: 'bg-green-100',
          textColor: 'text-green-800',
          dotColor: 'bg-green-500'
        };
      default:
        return {
          label: 'Unknown',
          bgColor: 'bg-gray-100',
          textColor: 'text-gray-800',
          dotColor: 'bg-gray-500'
        };
    }
  }

  formatDate(dateStr: string): string {
    const date = new Date(dateStr);
    const now = new Date();
    const diffTime = date.getTime() - now.getTime();
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

    if (diffDays === 0) {
      return 'Due today';
    } else if (diffDays === 1) {
      return 'Due tomorrow';
    } else if (diffDays > 0) {
      return `Due in ${diffDays} days`;
    } else {
      return `Overdue by ${Math.abs(diffDays)} days`;
    }
  }

  getDueDateColor(dateStr: string): string {
    const date = new Date(dateStr);
    const now = new Date();
    const diffTime = date.getTime() - now.getTime();
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

    if (diffDays < 0) {
      return 'text-red-600'; // Overdue
    } else if (diffDays === 0) {
      return 'text-orange-600'; // Due today
    } else if (diffDays <= 3) {
      return 'text-yellow-600'; // Due soon
    } else {
      return 'text-gray-600'; // Future
    }
  }

  onStatusToggle() {
    // Quick status toggle via API
    const statusCycle = {
      'TODO': 'IN_PROGRESS',
      'IN_PROGRESS': 'DONE', 
      'DONE': 'TODO'
    } as const;

    const newStatus = statusCycle[this.task.status];
    const updateCommand: UpdateTaskCommand = {
      id: this.task.id,
      taskName: this.task.taskName,
      description: this.task.description,
      status: newStatus,
      dueDate: this.task.dueDate
    };

    this.taskApiService.updateTask(updateCommand).subscribe({
      next: (updatedTask) => {
        console.log('Task status updated successfully:', updatedTask);
        this.taskService.updateTask(updatedTask);
      },
      error: (error) => {
        console.error('Error updating task status:', error);
        // Fallback: update local state if API fails
        this.taskService.toggleTaskStatus(this.task.id);
      }
    });
  }

  onEdit() {
    const dialogRef = this.dialog.open<Task | null>(TaskDialogComponent, {
      data: this.task,
      panelClass: 'task-dialog-panel',
      hasBackdrop: true,
      backdropClass: 'bg-black/50',
      width: '500px',
      maxWidth: '90vw'
    });

    dialogRef.closed.subscribe(result => {
      if (result) {
        const updateCommand: UpdateTaskCommand = {
          id: result.id,
          taskName: result.taskName,
          description: result.description,
          status: result.status,
          dueDate: result.dueDate
        };

        this.taskApiService.updateTask(updateCommand).subscribe({
          next: (updatedTask) => {
            console.log('Task updated successfully:', updatedTask);
            this.taskService.updateTask(updatedTask);
          },
          error: (error) => {
            console.error('Error updating task:', error);
            // Fallback: update local state if API fails
            this.taskService.updateTask(result);
          }
        });
      }
    });
  }

  onDelete() {
    if (confirm(`Are you sure you want to delete "${this.task.taskName}"?`)) {
      this.taskApiService.deleteTask(this.task.id).subscribe({
        next: () => {
          console.log('Task deleted successfully');
          this.taskService.deleteTask(this.task.id);
        },
        error: (error) => {
          console.error('Error deleting task:', error);
          // Fallback: remove from local state if API fails
          this.taskService.deleteTask(this.task.id);
        }
      });
    }
  }
}
