package pl.vm.aiworkshop.domain.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pl.vm.aiworkshop.domain.legacy.NotificationService;
import pl.vm.aiworkshop.domain.legacy.NotificationType;
import pl.vm.aiworkshop.domain.model.TaskEntity;
import pl.vm.aiworkshop.domain.model.TaskStatus;
import pl.vm.aiworkshop.domain.repository.TaskRepository;
import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.TaskQuery;
import pl.vm.aiworkshop.dto.UpdateTaskCommand;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceAdapter implements TaskService {

    private final TaskRepository taskRepository;
    private final NotificationService notificationService;

    @Override
    public Optional<TaskQuery> create(CreateTaskCommand command) {

        if (StringUtils.isEmpty(command.taskName())) {
            return Optional.empty();
        }

        TaskEntity taskEntity = toTaskEntity(command);

        TaskEntity savedEntity = taskRepository.save(taskEntity);

        // Send SMS notification when task is created
        String message = String.format("Task %s has been created. Creation date: %s", 
                savedEntity.getTaskName(), LocalDateTime.now());
        notificationService.sendNotification(message, NotificationType.SMS);

        return Optional.of(toTaskQuery(savedEntity));
    }

    private TaskEntity toTaskEntity(CreateTaskCommand command) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskName(command.taskName());
        taskEntity.setDueDate(command.dueDate());
        taskEntity.setDescription(command.description());
        taskEntity.setStatus(TaskStatus.CREATED);

        return taskEntity;
    }

    private TaskQuery toTaskQuery(TaskEntity taskEntity) {
        return TaskQuery.builder()
                .id(taskEntity.getId())
                .taskName(taskEntity.getTaskName())
                .dueDate(taskEntity.getDueDate())
                .status(taskEntity.getStatus().name())
                .description(taskEntity.getDescription())
                .build();
    }

    @Override
    public Optional<TaskQuery> get(Long id) {
        return taskRepository.findById(id)
                .map(this::toTaskQuery);
    }

    @Override
    public List<TaskQuery> getAll() {
        return taskRepository.findAll().stream()
                .map(this::toTaskQuery)
                .toList();
    }

    @Override
    public Optional<TaskQuery> update(Long id, UpdateTaskCommand command) {

        if (StringUtils.isEmpty(command.taskName()) || Objects.isNull(command.status())) {
            return Optional.empty();
        }

        return taskRepository.findById(id)
                .map(taskEntity -> {
                    TaskEntity updatedEntity = update(taskEntity, command);

                    // Send email notification when task is updated to IN_PROGRESS status
                    if (updatedEntity.getStatus() == TaskStatus.IN_PROGRESS) {
                        String message = String.format("Task %s has been updated. Update date: %s. Current status: %s", 
                                updatedEntity.getTaskName(), LocalDateTime.now(), updatedEntity.getStatus());
                        notificationService.sendNotification(message, NotificationType.EMAIL);
                    }

                    // Send email notification when task is updated to DONE status
                    if (updatedEntity.getStatus() == TaskStatus.DONE) {
                        String message = String.format("Task %s has been done. Keep it going!", 
                                updatedEntity.getTaskName());
                        notificationService.sendNotification(message, NotificationType.EMAIL);
                    }

                    return toTaskQuery(updatedEntity);
                });
    }

    private TaskEntity update(TaskEntity taskEntity, UpdateTaskCommand command) {
        taskEntity.setTaskName(command.taskName());
        taskEntity.setDueDate(command.dueDate());
        taskEntity.setStatus(TaskStatus.valueOf(command.status()));
        taskEntity.setDescription(command.description());

        return taskEntity;
    }

    @Override
    public boolean delete(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
