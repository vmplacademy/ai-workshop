package pl.vm.aiworkshop.domain.service.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import pl.vm.aiworkshop.dto.TaskQuery;
import pl.vm.aiworkshop.dto.UpdateTaskCommand;

import java.time.LocalDateTime;
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
    @DisplayName("create")
    class Create {

        @Test
        @DisplayName("should create task successfully")
        void should_create_task_successfully() {
            // given
            CreateTaskCommand command = new CreateTaskCommand("New Task", LocalDateTime.now(), "Description");
            TaskEntity savedTask = new TaskEntity(1L, "New Task", LocalDateTime.now(), TaskStatus.CREATED, "Description");

            when(taskRepository.save(any(TaskEntity.class))).thenReturn(savedTask);

            // when
            TaskQuery result = taskServiceAdapter.create(command);

            // then
            assertNotNull(result);
            assertEquals("New Task", result.taskName());
            verify(taskRepository, times(1)).save(any(TaskEntity.class));
        }

        @Test
        @DisplayName("should not create task when validation error")
        void should_not_create_task_when_validation_error() {
            // when
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> taskServiceAdapter.create(null));

            // then
            assertEquals("Command cannot be null", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("get")
    class Get {

        @Test
        @DisplayName("should return task when found")
        void should_return_task_when_found() {
            // given
            TaskEntity taskEntity = new TaskEntity(1L, "Task", LocalDateTime.now(), TaskStatus.CREATED, "Description");
            when(taskRepository.findById(1L)).thenReturn(Optional.of(taskEntity));

            // when
            Optional<TaskQuery> result = taskServiceAdapter.get(1L);

            // then
            assertTrue(result.isPresent());
            assertEquals("Task", result.get().taskName());
        }

        @Test
        @DisplayName("should return empty when task not found")
        void should_return_empty_when_task_not_found() {
            // given
            when(taskRepository.findById(1L)).thenReturn(Optional.empty());

            // when
            Optional<TaskQuery> result = taskServiceAdapter.get(1L);

            // then
            assertFalse(result.isPresent());
        }
    }

    @Nested
    @DisplayName("update")
    class Update {

        @Test
        @DisplayName("should update task successfully")
        void should_update_task_successfully() {
            // given
            UpdateTaskCommand command = new UpdateTaskCommand(1L, "Updated Task", LocalDateTime.now(), TaskStatus.IN_PROGRESS, "Updated Description");
            TaskEntity taskEntity = new TaskEntity(1L, "Task", LocalDateTime.now(), TaskStatus.CREATED, "Description");

            when(taskRepository.findById(1L)).thenReturn(Optional.of(taskEntity));
            when(taskRepository.save(any(TaskEntity.class))).thenReturn(taskEntity);

            // when
            Optional<TaskQuery> result = taskServiceAdapter.update(1L, command);

            // then
            assertTrue(result.isPresent());
            assertEquals("Updated Task", result.get().taskName());
            verify(taskRepository, times(1)).save(any(TaskEntity.class));
        }

        @Test
        @DisplayName("should return empty when task not found")
        void should_return_empty_when_task_not_found() {
            // given
            UpdateTaskCommand command = new UpdateTaskCommand(1L, "Updated Task", LocalDateTime.now(), TaskStatus.IN_PROGRESS, "Updated Description");

            when(taskRepository.findById(1L)).thenReturn(Optional.empty());

            // when
            Optional<TaskQuery> result = taskServiceAdapter.update(1L, command);

            // then
            assertFalse(result.isPresent());
        }
    }

    @Nested
    @DisplayName("delete")
    class Delete {

        @Test
        @DisplayName("should delete task successfully")
        void should_delete_task_successfully() {
            // given
            when(taskRepository.existsById(1L)).thenReturn(true);

            // when
            boolean result = taskServiceAdapter.delete(1L);

            // then
            assertTrue(result);
            verify(taskRepository, times(1)).deleteById(1L);
        }

        @Test
        @DisplayName("should return false when task not found")
        void should_return_false_when_task_not_found() {
            // given
            when(taskRepository.existsById(1L)).thenReturn(false);

            // when
            boolean result = taskServiceAdapter.delete(1L);

            // then
            assertFalse(result);
            verify(taskRepository, never()).deleteById(1L);
        }
    }
}