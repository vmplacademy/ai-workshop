package pl.vm.aiworkshop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.vm.aiworkshop.domain.model.TaskEntity;
import pl.vm.aiworkshop.domain.model.TaskStatus;
import pl.vm.aiworkshop.domain.repository.TaskRepository;
import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.EditTaskCommand;
import pl.vm.aiworkshop.dto.TaskQuery;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceAdapter implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskQuery addTask(CreateTaskCommand command) {

        validateCommand(command);

        TaskEntity taskEntity = createTaskEntity(command);

        return mapToTaskQuery(taskRepository.save(taskEntity));
    }

    private void validateCommand(CreateTaskCommand command) {
        if (command == null || command.taskName().isBlank()) {
            throw new IllegalArgumentException("Task name cannot be empty");
        }
    }

    private TaskEntity createTaskEntity(CreateTaskCommand command) {
        return new TaskEntity(null, command.taskName(), command.dueDate(), TaskStatus.CREATED, command.description());
    }

    private TaskQuery mapToTaskQuery(TaskEntity taskEntity) {
        return TaskQuery.builder()
                .id(taskEntity.getId())
                .taskName(taskEntity.getTaskName())
                .dueDate(taskEntity.getDueDate())
                .status(taskEntity.getStatus())
                .description(taskEntity.getDescription())
                .build();
    }

    @Override
    public Optional<TaskQuery> getTaskById(Long id) {
        return taskRepository.findById(id).map(this::mapToTaskQuery);
    }

    @Override
    public Optional<TaskQuery> editTask(Long id, EditTaskCommand command) {
        return taskRepository.findById(id).map(taskEntity -> {
            updateTaskEntity(taskEntity, command);
            return mapToTaskQuery(taskRepository.save(taskEntity));
        });
    }

    private void updateTaskEntity(TaskEntity taskEntity, EditTaskCommand command) {
        taskEntity.setTaskName(command.taskName());
        taskEntity.setDueDate(command.dueDate());
        taskEntity.setStatus(command.status());
        taskEntity.setDescription(command.description());
    }

    @Override
    public boolean deleteTask(Long id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}