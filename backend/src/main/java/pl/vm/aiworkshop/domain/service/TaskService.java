package pl.vm.aiworkshop.domain.service;

import pl.vm.aiworkshop.dto.TaskQuery;
import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.UpdateTaskCommand;

import java.util.Optional;

public interface TaskService {

    Optional<TaskQuery> create(CreateTaskCommand command);

    Optional<TaskQuery> get(Long id);

    Optional<TaskQuery> update(Long id, UpdateTaskCommand command);

    boolean delete(Long id);
}