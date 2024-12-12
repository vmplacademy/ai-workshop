package pl.vm.aiworkshop.domain.service.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.vm.aiworkshop.domain.TaskService;
import pl.vm.aiworkshop.domain.model.TaskEntity;
import pl.vm.aiworkshop.domain.model.TaskStatus;
import pl.vm.aiworkshop.domain.repository.TaskRepository;
import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.TaskQuery;
import pl.vm.aiworkshop.dto.UpdateTaskCommand;

@Service
@RequiredArgsConstructor
public class TaskServiceAdapter implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskQuery create(CreateTaskCommand command) {

        if (command == null || command.taskName() == null || command.taskName().isBlank()) {
            throw new IllegalArgumentException("Command or task name cannot be null or blank");
        }
    

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskName(command.taskName());
        taskEntity.setDueDate(command.dueDate());
        taskEntity.setStatus(TaskStatus.CREATED);
        taskEntity.setDescription(command.description());

        TaskEntity savedTask = taskRepository.save(taskEntity);

        return mapToTaskQuery(savedTask);
    }

    private TaskEntity updateTaskEntity(TaskEntity taskEntity, UpdateTaskCommand command) {
        taskEntity.setTaskName(command.taskName());
        taskEntity.setDueDate(command.dueDate());
        taskEntity.setStatus(command.status());
        taskEntity.setDescription(command.description());
        return taskRepository.save(taskEntity);
    }

    @Override
    public Optional<TaskQuery> get(Long id) {
        return taskRepository.findById(id)
                .map(this::mapToTaskQuery);
    }

    @Override
    public List<TaskQuery> getAllTaskQueries() {
        return taskRepository.findAll().stream()
                .map(this::mapToTaskQuery)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskQuery> update(Long id, UpdateTaskCommand command) {

        Optional<TaskEntity> task = taskRepository.findById(command.id())
                .map(taskEntity -> updateTaskEntity(taskEntity, command));

        return task.map(this::mapToTaskQuery);
    }

    @Override
    public boolean delete(Long id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
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
}