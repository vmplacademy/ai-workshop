package pl.vm.aiworkshop.domain.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.vm.aiworkshop.domain.model.TaskEntity;
import pl.vm.aiworkshop.domain.model.TaskStatus;
import pl.vm.aiworkshop.domain.repository.TaskRepository;
import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.EditTaskCommand;
import pl.vm.aiworkshop.dto.TaskQuery;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceAdapterTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceAdapter taskServiceAdapter;

    @Nested
    class AddTaskTests {
        @Test
        void should_add_task_when_is_correct() {
            // given
            CreateTaskCommand command = CreateTaskCommand.builder()
                    .taskName("New Task")
                    .dueDate("2023-12-31")
                    .description("Description of the new task")
                    .build();
            TaskEntity savedTask = new TaskEntity(1L, "New Task", "2023-12-31", TaskStatus.CREATED, "Description of the new task");

            when(taskRepository.save(any(TaskEntity.class))).thenReturn(savedTask);

            // when
            TaskQuery result = taskServiceAdapter.addTask(command);

            // then
            assertNotNull(result);
            assertEquals("New Task", result.taskName());
            verify(taskRepository, times(1)).save(any(TaskEntity.class));
        }

        @Test
        void should_throw_exception_when_task_name_is_empty() {
            // given
            CreateTaskCommand command = CreateTaskCommand.builder()
                    .taskName("")
                    .dueDate("2023-12-31")
                    .description("Description of the new task")
                    .build();

            // when
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> taskServiceAdapter.addTask(command));

            // then
            assertEquals("Task name cannot be empty", exception.getMessage());
        }
    }

    @Nested
    class GetTaskByIdTests {
        @Test
        void should_get_task_by_id_when_found() {
            // given
            TaskEntity taskEntity = new TaskEntity(1L, "Existing Task", "2023-12-31", TaskStatus.CREATED, "Description of the existing task");

            when(taskRepository.findById(1L)).thenReturn(Optional.of(taskEntity));

            // when
            Optional<TaskQuery> result = taskServiceAdapter.getTaskById(1L);

            // then
            assertTrue(result.isPresent());
            assertEquals("Existing Task", result.get().taskName());
        }

        @Test
        void should_get_task_by_id_when_not_found() {
            // given
            when(taskRepository.findById(1L)).thenReturn(Optional.empty());

            // when
            Optional<TaskQuery> result = taskServiceAdapter.getTaskById(1L);

            // then
            assertFalse(result.isPresent());
        }
    }

    @Nested
    class EditTaskTests {
        @Test
        void should_edit_task_when_is_correct() {
            // given
            EditTaskCommand command = EditTaskCommand.builder()
                    .taskName("Updated Task")
                    .dueDate("2023-12-31")
                    .status(TaskStatus.IN_PROGRESS)
                    .description("Updated description of the task")
                    .build();
            TaskEntity existingTask = new TaskEntity(1L, "Existing Task", "2023-12-31", TaskStatus.CREATED, "Description of the existing task");
            TaskEntity updatedTask = new TaskEntity(1L, "Updated Task", "2023-12-31", TaskStatus.IN_PROGRESS, "Updated description of the task");

            when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
            when(taskRepository.save(any(TaskEntity.class))).thenReturn(updatedTask);

            // when
            Optional<TaskQuery> result = taskServiceAdapter.editTask(1L, command);

            // then
            assertTrue(result.isPresent());
            assertEquals("Updated Task", result.get().taskName());
            verify(taskRepository, times(1)).save(any(TaskEntity.class));
        }

        @Test
        void should_edit_task_when_not_found() {
            // given
            EditTaskCommand command = EditTaskCommand.builder()
                    .taskName("Updated Task")
                    .dueDate("2023-12-31")
                    .status(TaskStatus.IN_PROGRESS)
                    .description("Updated description of the task")
                    .build();

            when(taskRepository.findById(1L)).thenReturn(Optional.empty());

            // when
            Optional<TaskQuery> result = taskServiceAdapter.editTask(1L, command);

            // then
            assertFalse(result.isPresent());
        }
    }

    @Nested
    class DeleteTaskTests {
        @Test
        void should_delete_task_when_found() {
            // given
            when(taskRepository.existsById(1L)).thenReturn(true);

            // when
            boolean result = taskServiceAdapter.deleteTask(1L);

            // then
            assertTrue(result);
            verify(taskRepository, times(1)).deleteById(1L);
        }

        @Test
        void should_delete_task_when_not_found() {
            // given
            when(taskRepository.existsById(1L)).thenReturn(false);

            // when
            boolean result = taskServiceAdapter.deleteTask(1L);

            // then
            assertFalse(result);
            verify(taskRepository, never()).deleteById(1L);
        }
    }
}