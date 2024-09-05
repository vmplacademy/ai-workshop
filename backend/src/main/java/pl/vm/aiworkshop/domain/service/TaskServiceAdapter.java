package pl.vm.aiworkshop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.TaskQuery;
import pl.vm.aiworkshop.dto.UpdateTaskCommand;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceAdapter implements TaskService {

    @Override
    public Optional<TaskQuery> create(CreateTaskCommand command) {
        // Method logic will be implemented here
        return Optional.empty();
    }

    @Override
    public Optional<TaskQuery> get(Long id) {
        // Method logic will be implemented here
        return Optional.empty();
    }

    @Override
    public Optional<TaskQuery> update(Long id, UpdateTaskCommand command) {
        // Method logic will be implemented here
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        // Method logic will be implemented here
        return false;
    }
}