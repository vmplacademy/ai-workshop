import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';

import { TaskItemComponent } from './task-item.component';
import { Task } from '../../models/task.models';

const testTask: Task = {
  id: 1,
  taskName: 'Zadanie testowe',
  description: 'Opis zadania testowego',
  status: 'CREATED',
  dueDate: '2026-12-31T00:00:00',
};

describe('TaskItemComponent', () => {
  let component: TaskItemComponent;
  let fixture: ComponentFixture<TaskItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TaskItemComponent],
      providers: [provideHttpClient(), provideHttpClientTesting()],
    }).compileComponents();

    fixture = TestBed.createComponent(TaskItemComponent);
    component = fixture.componentInstance;
    // task jest wymaganym wejściem — bez niego szablon nie ma czego wyrenderować
    fixture.componentRef.setInput('task', testTask);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render the task name', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.textContent).toContain('Zadanie testowe');
  });
});
