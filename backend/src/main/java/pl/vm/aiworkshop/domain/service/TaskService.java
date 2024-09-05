package pl.vm.aiworkshop.domain.service;

import pl.vm.aiworkshop.dto.TaskQuery;
import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.UpdateTaskCommand;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Optional<TaskQuery> create(CreateTaskCommand command);

    Optional<TaskQuery> get(Long id);

    List<TaskQuery> getAll();

    Optional<TaskQuery> update(Long id, UpdateTaskCommand command);

    boolean delete(Long id);
}