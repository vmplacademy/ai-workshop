package pl.vm.aiworkshop.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.vm.aiworkshop.TestcontainersConfiguration;
import pl.vm.aiworkshop.domain.model.TaskEntity;
import pl.vm.aiworkshop.domain.model.TaskStatus;
import pl.vm.aiworkshop.domain.repository.TaskRepository;
import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.TaskQuery;
import pl.vm.aiworkshop.dto.UpdateTaskCommand;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerTest {

    private static final String API_TASKS = "/api/tasks";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void createTask() {
        // given
        CreateTaskCommand command = CreateTaskCommand.builder()
                .taskName("New Task")
                .dueDate(LocalDateTime.of(2023, 12, 10, 10, 0))
                .description("New Task Description")
                .build();

        // when & then
        webTestClient.post()
                .uri(API_TASKS)
                .bodyValue(command)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CREATED)
                .expectBody(TaskQuery.class)
                .value(task -> {
                    assertThat(task).isNotNull();
                    assertThat(task.taskName()).isEqualTo("New Task");
                    assertThat(task.status()).isEqualTo(TaskStatus.CREATED.name());
                });
    }

    @Test
    void getTask() {
        // given
        Long taskId = saveTaskEntity().getId();

        // when & then
        webTestClient.get()
                .uri(API_TASKS + "/" + taskId)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody(TaskQuery.class)
                .value(task -> {
                    assertThat(task).isNotNull();
                    assertThat(task.id()).isEqualTo(taskId);
                });
    }

    private TaskEntity saveTaskEntity() {
        return taskRepository.save(
                new TaskEntity(
                        null,
                        "Task 1",
                        LocalDateTime.of(2023, 10, 10, 10, 0),
                        TaskStatus.CREATED,
                        "Description"
                )
        );
    }

    @Test
    void getAllTasks() {
        // given
        saveTaskEntity();

        // when
        webTestClient.get()
                .uri(API_TASKS)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBodyList(TaskQuery.class)
                .value(tasks -> {
                    assertThat(tasks).isNotNull();
                    assertThat(tasks).hasSize(1);
                });
    }

    @Test
    void updateTask() {
        // given
        Long taskId = saveTaskEntity().getId();

        UpdateTaskCommand command = UpdateTaskCommand.builder()
                .taskName("Updated Task")
                .dueDate(LocalDateTime.of(2023, 12, 10, 10, 0))
                .status("IN_PROGRESS")
                .description("Updated Description")
                .build();

        // when & then
        webTestClient.put()
                .uri(API_TASKS + "/" + taskId)
                .bodyValue(command)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody(TaskQuery.class)
                .value(task -> {
                    assertThat(task).isNotNull();
                    assertThat(task.taskName()).isEqualTo("Updated Task");
                });
    }

    @Test
    void deleteTask() {
        // given
        Long taskId = saveTaskEntity().getId();

        // when & then
        webTestClient.delete()
                .uri(API_TASKS + "/" + taskId)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NO_CONTENT);
    }
}