import { Injectable, signal, computed } from '@angular/core';
import { Task, FilterType, TaskStatus } from '../models/task.models';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  // Signals for state management - NO NgRx, NO Akita
  tasks = signal<Task[]>([]);
  filter = signal<FilterType>('all');
  searchQuery = signal<string>('');
  sortBy = signal<'dueDate' | 'status' | 'taskName'>('dueDate');
  sortOrder = signal<'asc' | 'desc'>('asc');

  // Computed values using Angular Signals
  filteredTasks = computed(() => {
    const allTasks = this.tasks();
    const currentFilter = this.filter();
    const search = this.searchQuery().toLowerCase();
    const sort = this.sortBy();
    const order = this.sortOrder();

    // Apply filter
    let filtered =
      currentFilter === 'all'
        ? allTasks
        : allTasks.filter((task) => task.status === currentFilter);

    // Apply search
    if (search) {
      filtered = filtered.filter(
        (task) =>
          task.taskName.toLowerCase().includes(search) ||
          (task.description && task.description.toLowerCase().includes(search)),
      );
    }

    // Apply sort
    filtered = [...filtered].sort((a, b) => {
      let aValue: string | number;
      let bValue: string | number;

      switch (sort) {
        case 'dueDate':
          aValue = new Date(a.dueDate).getTime();
          bValue = new Date(b.dueDate).getTime();
          break;
        case 'status':
          aValue = a.status;
          bValue = b.status;
          break;
        case 'taskName':
          aValue = a.taskName.toLowerCase();
          bValue = b.taskName.toLowerCase();
          break;
      }

      if (aValue < bValue) return order === 'asc' ? -1 : 1;
      if (aValue > bValue) return order === 'asc' ? 1 : -1;
      return 0;
    });

    return filtered;
  });

  // Computed stats
  taskCounts = computed(() => {
    const allTasks = this.tasks();
    return {
      total: allTasks.length,
      todo: allTasks.filter((t) => t.status === 'TODO').length,
      inProgress: allTasks.filter((t) => t.status === 'IN_PROGRESS').length,
      done: allTasks.filter((t) => t.status === 'DONE').length,
    };
  });

  // Methods for updating state
  setTasks(tasks: Task[]) {
    this.tasks.set(tasks);
  }

  addTask(task: Task) {
    this.tasks.update((tasks) => [...tasks, task]);
  }

  updateTask(updatedTask: Task) {
    this.tasks.update(
      (tasks) =>
        tasks.map((task) => (task.id == updatedTask.id ? updatedTask : task)), // Use == for string/number comparison
    );
  }

  deleteTask(taskId: string | number) {
    // Support both string and number IDs
    this.tasks.update(
      (tasks) => tasks.filter((task) => task.id != taskId), // Use != for string/number comparison
    );
  }

  setFilter(filter: FilterType) {
    this.filter.set(filter);
  }

  setSearch(query: string) {
    this.searchQuery.set(query);
  }

  setSorting(
    sortBy: 'dueDate' | 'status' | 'taskName',
    order: 'asc' | 'desc' = 'asc',
  ) {
    this.sortBy.set(sortBy);
    this.sortOrder.set(order);
  }

  // Helper method to get task by ID
  getTaskById(id: string | number): Task | undefined {
    // Support both string and number IDs
    return this.tasks().find((task) => task.id == id); // Use == for string/number comparison
  }

  // Method to toggle task status (for quick status changes)
  toggleTaskStatus(taskId: string | number) {
    // Support both string and number IDs
    const task = this.getTaskById(taskId);
    if (!task) return;

    const statusCycle: Record<TaskStatus, TaskStatus> = {
      TODO: 'IN_PROGRESS',
      IN_PROGRESS: 'DONE',
      DONE: 'TODO',
    };

    const updatedTask = {
      ...task,
      status: statusCycle[task.status],
    };

    this.updateTask(updatedTask);
  }
}
