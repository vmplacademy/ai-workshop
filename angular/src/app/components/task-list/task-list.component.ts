import { Component, inject } from '@angular/core';
import { TaskService } from '../../services/task.service';
import { TaskApiService } from '../../services/task-api.service';
import { TaskItemComponent } from '../task-item/task-item.component';
import { FilterType } from '../../models/task.models';

@Component({
  selector: 'app-task-list',
  imports: [TaskItemComponent],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css',
})
export class TaskListComponent {
  private taskService = inject(TaskService);
  private taskApiService = inject(TaskApiService);

  // Expose service signals for template
  filteredTasks = this.taskService.filteredTasks;
  filter = this.taskService.filter;
  searchQuery = this.taskService.searchQuery;

  // Loading state
  loading = false;

  clearFilters() {
    this.taskService.setFilter('all');
    this.taskService.setSearch('');
  }

  getFilterLabel(filter: FilterType): string {
    switch (filter) {
      case 'TODO':
        return 'To Do';
      case 'IN_PROGRESS':
        return 'In Progress';
      case 'DONE':
        return 'Done';
      default:
        return 'All';
    }
  }
}
