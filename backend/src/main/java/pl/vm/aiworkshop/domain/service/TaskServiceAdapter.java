package pl.vm.aiworkshop.domain.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pl.vm.aiworkshop.domain.model.TaskEntity;
import pl.vm.aiworkshop.domain.model.TaskStatus;
import pl.vm.aiworkshop.domain.repository.TaskRepository;
import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.TaskQuery;
import pl.vm.aiworkshop.dto.UpdateTaskCommand;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceAdapter implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Optional<TaskQuery> create(CreateTaskCommand command) {

        if (StringUtils.isEmpty(command.taskName())) {
            return Optional.empty();
        }

        TaskEntity taskEntity = toTaskEntity(command);

        TaskEntity savedEntity = taskRepository.save(taskEntity);

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
    public Optional<TaskQuery> update(Long id, UpdateTaskCommand command) {

        if (StringUtils.isEmpty(command.taskName()) || Objects.isNull(command.status())) {
            return Optional.empty();
        }

        return taskRepository.findById(id)
                .map(taskEntity -> {
                    TaskEntity updatedEntity = update(taskEntity, command);
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