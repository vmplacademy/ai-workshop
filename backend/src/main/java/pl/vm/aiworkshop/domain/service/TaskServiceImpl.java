package pl.vm.aiworkshop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.vm.aiworkshop.domain.model.TaskEntity;
import pl.vm.aiworkshop.domain.model.TaskStatus;
import pl.vm.aiworkshop.domain.repository.TaskRepository;
import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.TaskQuery;
import pl.vm.aiworkshop.dto.UpdateTaskCommand;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepo;
    private final TaskRepository taskRepository;

    @Override
    public Optional<TaskQuery> createTask(CreateTaskCommand command) {

        if (command == null) {
            return Optional.empty();
        }

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskName(command.taskName());
        taskEntity.setDueDate(command.dueDate());
        taskEntity.setStatus(TaskStatus.CREATED);
        taskEntity.setDescription(command.description());

        taskRepo.save(taskEntity);

        return Optional.of(
                map(taskEntity)
        );
    }

    private TaskQuery map(TaskEntity taskEntity) {
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
        return taskRepository.findById(id)
                .map(this::map);
    }

    @Override
    public Optional<TaskQuery> updateTask(Long id, UpdateTaskCommand command) {
        return taskRepository.findById(id).map(taskEntity -> {
            taskEntity.setTaskName(command.taskName());
            taskEntity.setDueDate(command.dueDate());
            taskEntity.setDescription(command.description());
            taskEntity.setStatus(command.status());
            return map(taskEntity);
        });
    }

    @Override
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
