package pl.vm.aiworkshop.domain.service;

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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceAdapterTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceAdapter taskServiceAdapter;

    @Nested
    @DisplayName("Create Task")
    class CreateTask {

        @Test
        @DisplayName("Successfully create a task")
        void createTaskSuccessfully() {
            // given
            CreateTaskCommand command = CreateTaskCommand.builder()
                    .taskName("Task 1")
                    .dueDate(LocalDateTime.of(2023, 10, 10, 10, 0))
                    .description("Description")
                    .build();

            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setId(1L);
            taskEntity.setTaskName("Task 1");
            taskEntity.setDueDate(LocalDateTime.of(2023, 10, 10, 10, 0));
            taskEntity.setDescription("Description");
            taskEntity.setStatus(TaskStatus.CREATED);

            when(taskRepository.save(any(TaskEntity.class))).thenReturn(taskEntity);

            // when
            Optional<TaskQuery> result = taskServiceAdapter.create(command);

            // then
            assertThat(result).isPresent();
            assertThat(result.get().id()).isEqualTo(1L);
        }

        @Test
        @DisplayName("Fail to create a task with empty name")
        void createTaskWithEmptyName() {
            // given
            CreateTaskCommand command = CreateTaskCommand.builder()
                    .taskName("")
                    .dueDate(LocalDateTime.of(2023, 10, 10, 10, 0))
                    .description("Description")
                    .build();

            // when
            Optional<TaskQuery> result = taskServiceAdapter.create(command);

            // then
            assertThat(result).isNotPresent();
        }
    }

    @Nested
    @DisplayName("Get Task by ID")
    class GetTaskById {

        @Test
        @DisplayName("Successfully get a task by ID")
        void getTaskByIdSuccessfully() {
            // given
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setId(1L);
            taskEntity.setTaskName("Task 1");
            taskEntity.setDueDate(LocalDateTime.of(2023, 10, 10, 10, 0));
            taskEntity.setDescription("Description");
            taskEntity.setStatus(TaskStatus.CREATED);

            when(taskRepository.findById(1L)).thenReturn(Optional.of(taskEntity));

            // when
            Optional<TaskQuery> result = taskServiceAdapter.get(1L);

            // then
            assertThat(result).isPresent();
            assertThat(result.get().id()).isEqualTo(1L);
        }

        @Test
        @DisplayName("Fail to get a task by ID when not found")
        void getTaskByIdNotFound() {
            // given
            when(taskRepository.findById(1L)).thenReturn(Optional.empty());

            // when
            Optional<TaskQuery> result = taskServiceAdapter.get(1L);

            // then
            assertThat(result).isNotPresent();
        }
    }

    @Nested
    @DisplayName("Get All Tasks")
    class GetAllTasks {

        @Test
        @DisplayName("Successfully get all tasks")
        void getAllTasksSuccessfully() {
            // given
            TaskEntity taskEntity1 = new TaskEntity();
            taskEntity1.setId(1L);
            taskEntity1.setTaskName("Task 1");
            taskEntity1.setDueDate(LocalDateTime.of(2023, 10, 10, 10, 0));
            taskEntity1.setDescription("Description");
            taskEntity1.setStatus(TaskStatus.CREATED);

            TaskEntity taskEntity2 = new TaskEntity();
            taskEntity2.setId(2L);
            taskEntity2.setTaskName("Task 2");
            taskEntity2.setDueDate(LocalDateTime.of(2023, 11, 10, 10, 0));
            taskEntity2.setDescription("Description 2");
            taskEntity2.setStatus(TaskStatus.IN_PROGRESS);

            when(taskRepository.findAll()).thenReturn(List.of(taskEntity1, taskEntity2));

            // when
            List<TaskQuery> result = taskServiceAdapter.getAll();

            // then
            assertThat(result).hasSize(2);
        }
    }

    @Nested
    @DisplayName("Update Task")
    class UpdateTask {

        @Test
        @DisplayName("Successfully update a task")
        void updateTaskSuccessfully() {
            // given
            UpdateTaskCommand command = UpdateTaskCommand.builder()
                    .taskName("Updated Task")
                    .dueDate(LocalDateTime.of(2023, 12, 10, 10, 0))
                    .status("IN_PROGRESS")
                    .description("Updated Description")
                    .build();

            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setId(1L);
            taskEntity.setTaskName("Task 1");
            taskEntity.setDueDate(LocalDateTime.of(2023, 10, 10, 10, 0));
            taskEntity.setDescription("Description");
            taskEntity.setStatus(TaskStatus.CREATED);

            when(taskRepository.findById(1L)).thenReturn(Optional.of(taskEntity));

            // when
            Optional<TaskQuery> result = taskServiceAdapter.update(1L, command);

            // then
            assertThat(result).isPresent();
            assertThat(result.get().taskName()).isEqualTo("Updated Task");
        }

        @Test
        @DisplayName("Fail to update a task with empty name")
        void updateTaskWithEmptyName() {
            // given
            UpdateTaskCommand command = UpdateTaskCommand.builder()
                    .taskName("")
                    .dueDate(LocalDateTime.of(2023, 12, 10, 10, 0))
                    .status("IN_PROGRESS")
                    .description("Updated Description")
                    .build();

            // when
            Optional<TaskQuery> result = taskServiceAdapter.update(1L, command);

            // then
            assertThat(result).isNotPresent();
        }
    }

    @Nested
    @DisplayName("Delete Task")
    class DeleteTask {

        @Test
        @DisplayName("Successfully delete a task")
        void deleteTaskSuccessfully() {
            // given
            when(taskRepository.existsById(1L)).thenReturn(true);

            // when
            boolean result = taskServiceAdapter.delete(1L);

            // then
            assertThat(result).isTrue();
        }

        @Test
        @DisplayName("Fail to delete a task when not found")
        void deleteTaskNotFound() {
            // given
            when(taskRepository.existsById(1L)).thenReturn(false);

            // when
            boolean result = taskServiceAdapter.delete(1L);

            // then
            assertThat(result).isFalse();
        }
    }
}