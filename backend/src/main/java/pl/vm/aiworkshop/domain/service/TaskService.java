package pl.vm.aiworkshop.domain.service;

import pl.vm.aiworkshop.dto.TaskQuery;
import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.UpdateTaskCommand;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing tasks.
 */
public interface TaskService {

    /**
     * Creates a new task.
     *
     * @param command the command containing the details for creating the task
     * @return an Optional containing the created TaskQuery if successful, otherwise empty
     */
    Optional<TaskQuery> create(CreateTaskCommand command);

    /**
     * Retrieves a task by its ID.
     *
     * @param id the ID of the task to retrieve
     * @return an Optional containing the TaskQuery if found, otherwise empty
     */
    Optional<TaskQuery> get(Long id);

    /**
     * Retrieves all tasks.
     *
     * @return a list of all TaskQuery objects
     */
    List<TaskQuery> getAll();

    /**
     * Updates an existing task.
     *
     * @param id the ID of the task to update
     * @param command the command containing the updated details for the task
     * @return an Optional containing the updated TaskQuery if successful, otherwise empty
     */
    Optional<TaskQuery> update(Long id, UpdateTaskCommand command);

    /**
     * Deletes a task by its ID.
     *
     * @param id the ID of the task to delete
     * @return true if the task was successfully deleted, otherwise false
     */
    boolean delete(Long id);
}