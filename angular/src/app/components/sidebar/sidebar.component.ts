import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TaskService } from '../../services/task.service';
import { FilterType } from '../../models/task.models';

@Component({
  selector: 'app-sidebar',
  imports: [FormsModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css',
})
export class SidebarComponent {
  private taskService = inject(TaskService);

  // Expose service signals for template
  filter = this.taskService.filter;
  searchQuery = this.taskService.searchQuery;
  sortBy = this.taskService.sortBy;
  sortOrder = this.taskService.sortOrder;
  taskCounts = this.taskService.taskCounts;

  // Filter options
  filterOptions: { value: FilterType; label: string; color: string }[] = [
    { value: 'all', label: 'All Tasks', color: 'text-gray-700' },
    { value: 'CREATED', label: 'To Do', color: 'text-red-600' },
    { value: 'IN_PROGRESS', label: 'In Progress', color: 'text-yellow-600' },
    { value: 'DONE', label: 'Done', color: 'text-green-600' },
  ];

  // Sort options
  sortOptions = [
    { value: 'dueDate', label: 'Due Date' },
    { value: 'taskName', label: 'Task Name' },
    { value: 'status', label: 'Status' },
  ];

  onFilterChange(filter: FilterType) {
    this.taskService.setFilter(filter);
  }

  onSearchChange(query: string) {
    this.taskService.setSearch(query);
  }

  onSortChange(sortBy: 'dueDate' | 'status' | 'taskName') {
    this.taskService.setSorting(sortBy, this.sortOrder());
  }

  onSortOrderToggle() {
    const newOrder = this.sortOrder() === 'asc' ? 'desc' : 'asc';
    this.taskService.setSorting(this.sortBy(), newOrder);
  }
}
