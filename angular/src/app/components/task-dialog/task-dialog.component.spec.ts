import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DialogRef, DIALOG_DATA } from '@angular/cdk/dialog';

import { TaskDialogComponent } from './task-dialog.component';

describe('TaskDialogComponent', () => {
  let component: TaskDialogComponent;
  let fixture: ComponentFixture<TaskDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TaskDialogComponent],
      providers: [
        // dialog normalnie dostaje te zależności od CDK przy otwarciu przez Dialog.open()
        { provide: DialogRef, useValue: { close: () => {} } },
        { provide: DIALOG_DATA, useValue: null },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(TaskDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should start with an invalid form when no task is given', () => {
    // DIALOG_DATA = null oznacza tryb tworzenia — nazwa zadania jest pusta i wymagana
    expect(component.taskForm.valid).toBeFalse();
  });
});
