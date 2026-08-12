import { Component, Inject, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { DialogRef, DIALOG_DATA } from '@angular/cdk/dialog';
import { Task, TaskStatus } from '../../models/task.models';

@Component({
  selector: 'app-task-dialog',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './task-dialog.component.html',
  styleUrl: './task-dialog.component.css',
})
export class TaskDialogComponent {
  private fb = inject(FormBuilder);
  private dialogRef = inject(DialogRef<Task | null>);

  taskForm: FormGroup;
  isEditMode: boolean;

  statusOptions = [
    { value: 'CREATED', label: 'To Do' },
    { value: 'IN_PROGRESS', label: 'In Progress' },
    { value: 'DONE', label: 'Done' },
  ];

  constructor(@Inject(DIALOG_DATA) public data: Task | null) {
    this.isEditMode = !!data;

    this.taskForm = this.fb.group({
      taskName: [
        data?.taskName || '',
        [Validators.required, Validators.maxLength(100)],
      ],
      description: [data?.description || '', [Validators.maxLength(500)]],
      status: [data?.status || 'CREATED', [Validators.required]],
      dueDate: [this.formatDateForInput(data?.dueDate), [Validators.required]],
    });
  }

  private formatDateForInput(dateStr?: string): string {
    if (!dateStr) {
      // Default to tomorrow
      const tomorrow = new Date();
      tomorrow.setDate(tomorrow.getDate() + 1);
      return tomorrow.toISOString().split('T')[0];
    }
    return new Date(dateStr).toISOString().split('T')[0];
  }

  get title(): string {
    return this.isEditMode ? 'Edit Task' : 'Add New Task';
  }

  get submitButtonText(): string {
    return this.isEditMode ? 'Update Task' : 'Create Task';
  }

  onSubmit() {
    if (this.taskForm.valid) {
      const formValue = this.taskForm.value;

      const task: Task = {
        id: this.data?.id || this.generateId(),
        taskName: formValue.taskName.trim(),
        description: formValue.description?.trim() || undefined,
        status: formValue.status as TaskStatus,
        dueDate: formValue.dueDate,
      };

      this.dialogRef.close(task);
    } else {
      // Mark all fields as touched to show validation errors
      Object.keys(this.taskForm.controls).forEach((key) => {
        this.taskForm.get(key)?.markAsTouched();
      });
    }
  }

  onCancel() {
    this.dialogRef.close(null);
  }

  private generateId(): string {
    return 'task_' + Date.now() + '_' + Math.random().toString(36).substr(2, 9);
  }

  getFieldError(fieldName: string): string | null {
    const field = this.taskForm.get(fieldName);
    if (field && field.errors && field.touched) {
      if (field.errors['required']) {
        return `${this.getFieldLabel(fieldName)} is required.`;
      }
      if (field.errors['maxlength']) {
        const maxLength = field.errors['maxlength'].requiredLength;
        return `${this.getFieldLabel(fieldName)} cannot exceed ${maxLength} characters.`;
      }
    }
    return null;
  }

  private getFieldLabel(fieldName: string): string {
    switch (fieldName) {
      case 'taskName':
        return 'Task name';
      case 'description':
        return 'Description';
      case 'status':
        return 'Status';
      case 'dueDate':
        return 'Due date';
      default:
        return fieldName;
    }
  }
}
