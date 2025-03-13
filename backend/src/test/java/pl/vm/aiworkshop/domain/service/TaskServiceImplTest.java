package pl.vm.aiworkshop.domain.service;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.vm.aiworkshop.domain.model.TaskEntity;
import pl.vm.aiworkshop.domain.model.TaskStatus;
import pl.vm.aiworkshop.domain.repository.TaskRepository;
import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.TaskQuery;
import pl.vm.aiworkshop.dto.UpdateTaskCommand;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Nested
    class CreateTask {

        @Test
        void should_create_multiple_tasks_with_same_content() {
            // given
            int numberOfTasks = 3;
            CreateTaskCommand command = new CreateTaskCommand("Task 1", LocalDateTime.parse("2023-12-31T23:59:59"), "Description");
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setId(1L);
            taskEntity.setTaskName("Task 1");
            taskEntity.setDueDate(LocalDateTime.parse("2023-12-31T23:59:59"));
            taskEntity.setStatus(TaskStatus.CREATED);
            taskEntity.setDescription("Description");

            when(taskRepository.save(any(TaskEntity.class))).thenReturn(taskEntity);

            // when
            for (int i = 0; i < numberOfTasks; i++) {
                taskService.createTask(command);
            }

            // then
            verify(taskRepository, times(numberOfTasks)).save(any(TaskEntity.class));
        }

        @Test
        void should_return_task_query_when_command_is_valid() {
            // given
            CreateTaskCommand command = new CreateTaskCommand("Task 1", LocalDateTime.parse("2023-12-31T23:59:59"), "Description");
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setId(1L);
            taskEntity.setTaskName("Task 1");
            taskEntity.setDueDate(LocalDateTime.parse("2023-12-31T23:59:59"));
            taskEntity.setStatus(TaskStatus.CREATED);
            taskEntity.setDescription("Description");

            when(taskRepository.save(any(TaskEntity.class))).thenReturn(taskEntity);

            // when
            Optional<TaskQuery> result = taskService.createTask(command);

            // then
            assertTrue(result.isPresent());
            assertEquals("Task 1", result.get().taskName());
        }

        @Test
        void should_return_empty_optional_when_command_is_null() {
            // given

            // when
            Optional<TaskQuery> result = taskService.createTask(null);

            // then
            assertFalse(result.isPresent());
        }
    }

    @Nested
    class GetTaskById {

        @Test
        void should_return_task_query_when_id_exists() {
            // given
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setId(1L);
            taskEntity.setTaskName("Task 1");
            taskEntity.setDueDate(LocalDateTime.parse("2023-12-31T23:59:59"));
            taskEntity.setStatus(TaskStatus.CREATED);
            taskEntity.setDescription("Description");

            when(taskRepository.findById(1L)).thenReturn(Optional.of(taskEntity));

            // when
            Optional<TaskQuery> result = taskService.getTaskById(1L);

            // then
            assertTrue(result.isPresent());
            assertEquals("Task 1", result.get().taskName());
        }

        @Test
        void should_return_empty_optional_when_id_does_not_exist() {
            // given
            when(taskRepository.findById(1L)).thenReturn(Optional.empty());

            // when
            Optional<TaskQuery> result = taskService.getTaskById(1L);

            // then
            assertFalse(result.isPresent());
        }
    }

    @Nested
    class UpdateTask {

        @Test
        void should_return_updated_task_query_when_id_and_command_are_valid() {
            // given
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setId(1L);
            taskEntity.setTaskName("Task 1");
            taskEntity.setDueDate(LocalDateTime.parse("2023-12-31T23:59:59"));
            taskEntity.setStatus(TaskStatus.CREATED);
            taskEntity.setDescription("Description");

            UpdateTaskCommand command = new UpdateTaskCommand("Updated Task", LocalDateTime.parse("2023-12-31T23:59:59"), TaskStatus.IN_PROGRESS, "Updated Description");

            when(taskRepository.findById(1L)).thenReturn(Optional.of(taskEntity));

            // when
            Optional<TaskQuery> result = taskService.updateTask(1L, command);

            // then
            assertTrue(result.isPresent());
            assertEquals("Updated Task", result.get().taskName());
        }

        @Test
        void should_return_empty_optional_when_id_does_not_exist() {
            // given
            UpdateTaskCommand command = new UpdateTaskCommand("Updated Task", LocalDateTime.parse("2023-12-31T23:59:59"), TaskStatus.IN_PROGRESS, "Updated Description");

            when(taskRepository.findById(1L)).thenReturn(Optional.empty());

            // when
            Optional<TaskQuery> result = taskService.updateTask(1L, command);

            // then
            assertFalse(result.isPresent());
        }
    }

    @Nested
    class DeleteTask {

        @Test
        void should_return_true_when_id_exists() {
            // given
            when(taskRepository.existsById(1L)).thenReturn(true);

            // when
            boolean result = taskService.deleteTask(1L);

            // then
            assertTrue(result);
            verify(taskRepository, times(1)).deleteById(1L);
        }

        @Test
        void should_return_false_when_id_does_not_exist() {
            // given
            when(taskRepository.existsById(1L)).thenReturn(false);

            // when
            boolean result = taskService.deleteTask(1L);

            // then
            assertFalse(result);
            verify(taskRepository, never()).deleteById(1L);
        }
    }
}