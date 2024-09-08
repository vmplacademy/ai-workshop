package pl.vm.aiworkshop.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.vm.aiworkshop.domain.model.TaskEntity;
import pl.vm.aiworkshop.domain.model.TaskStatus;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void findByIdReturnsTaskEntityWhenIdExists() {
        // given
        TaskEntity task = new TaskEntity();
        task.setTaskName("Sample Task");
        task.setDescription("Sample Description");
        task.setDueDate(LocalDateTime.now().plusDays(1));
        task.setStatus(TaskStatus.CREATED);
        task = taskRepository.save(task);

        // when
        Optional<TaskEntity> foundTask = taskRepository.findById(task.getId());

        // then
        assertThat(foundTask).isPresent();
        assertThat(foundTask.get().getTaskName()).isEqualTo("Sample Task");
    }

    @Test
    void findByIdReturnsEmptyWhenIdDoesNotExist() {
        // given
        Long nonExistentId = 999L;

        // when
        Optional<TaskEntity> foundTask = taskRepository.findById(nonExistentId);

        // then
        assertThat(foundTask).isNotPresent();
    }

    @Test
    void savePersistsTaskEntity() {
        // given
        TaskEntity task = new TaskEntity();
        task.setTaskName("New Task");
        task.setDescription("New Description");
        task.setDueDate(LocalDateTime.now().plusDays(1));
        task.setStatus(TaskStatus.CREATED);

        // when
        TaskEntity savedTask = taskRepository.save(task);

        // then
        assertThat(savedTask.getId()).isNotNull();
        assertThat(savedTask.getTaskName()).isEqualTo("New Task");
    }

    @Test
    void deleteRemovesTaskEntity() {
        // given
        TaskEntity task = new TaskEntity();
        task.setTaskName("Task to be deleted");
        task.setDescription("Description to be deleted");
        task.setDueDate(LocalDateTime.now().plusDays(1));
        task.setStatus(TaskStatus.CREATED);
        task = taskRepository.save(task);

        // when
        taskRepository.delete(task);
        Optional<TaskEntity> foundTask = taskRepository.findById(task.getId());

        // then
        assertThat(foundTask).isNotPresent();
    }
}