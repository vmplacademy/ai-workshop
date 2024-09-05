package pl.vm.aiworkshop.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private TestRestTemplate restTemplate;

    @Autowired
    private TaskRepository taskRepository;

    private TaskEntity taskEntity;

    @BeforeEach
    void setUp() {
        taskEntity = taskRepository.save(
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
    void createTask() {
        // given
        CreateTaskCommand command = CreateTaskCommand.builder()
                .taskName("New Task")
                .dueDate(LocalDateTime.of(2023, 12, 10, 10, 0))
                .description("New Task Description")
                .build();

        // when
        ResponseEntity<TaskQuery> response = restTemplate.postForEntity(API_TASKS, command, TaskQuery.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().taskName()).isEqualTo("New Task");
        assertThat(response.getBody().status()).isEqualTo(TaskStatus.CREATED.name());
    }

    @Test
    void getTask() {
        // given
        Long taskId = taskEntity.getId();

        // when
        ResponseEntity<TaskQuery> response = restTemplate.getForEntity(API_TASKS + "/" + taskId, TaskQuery.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().id()).isEqualTo(taskId);
    }

    @Test
    void getAllTasks() {
        // given & when
        ResponseEntity<TaskQuery[]> response = restTemplate.getForEntity(API_TASKS, TaskQuery[].class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isEqualTo(1);
    }

    @Test
    void updateTask() {
        // given
        Long taskId = taskEntity.getId();

        UpdateTaskCommand command = UpdateTaskCommand.builder()
                .taskName("Updated Task")
                .dueDate(LocalDateTime.of(2023, 12, 10, 10, 0))
                .status("IN_PROGRESS")
                .description("Updated Description")
                .build();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<UpdateTaskCommand> entity = new HttpEntity<>(command, headers);

        // when
        ResponseEntity<TaskQuery> response = restTemplate.exchange(API_TASKS + "/" + taskId, HttpMethod.PUT, entity, TaskQuery.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().taskName()).isEqualTo("Updated Task");
    }

    @Test
    void deleteTask() {
        // given
        Long taskId = taskEntity.getId();

        // when
        ResponseEntity<Void> response = restTemplate.exchange(API_TASKS + "/" + taskId, HttpMethod.DELETE, null, Void.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}