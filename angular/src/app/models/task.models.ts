export interface Task {
  id: string | number; // Support both string and number IDs for backend compatibility
  taskName: string;
  description?: string;
  status: TaskStatus;
  dueDate: string;
  createdAt?: string; // Optional fields from backend
  updatedAt?: string | null;
}

export type TaskStatus = 'TODO' | 'IN_PROGRESS' | 'DONE';

export type FilterType = 'all' | 'TODO' | 'IN_PROGRESS' | 'DONE';

export interface CreateTaskCommand {
  taskName: string;
  description?: string;
  status: TaskStatus;
  dueDate: string;
}

export interface UpdateTaskCommand {
  id: string | number; // Support both string and number IDs
  taskName: string;
  description?: string;
  status: TaskStatus;
  dueDate: string;
}
