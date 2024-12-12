package pl.vm.aiworkshop.domain;

import java.util.List;
import java.util.Optional;

import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.TaskQuery;
import pl.vm.aiworkshop.dto.UpdateTaskCommand;

/**
 * Service interface for managing tasks.
 */
public interface TaskService {

    /**
     * Creates a new task.
     *
     * @param command the command containing the details of the task to create
     * @return the created task as a TaskQuery object
     */
    TaskQuery create(CreateTaskCommand command);

    /**
     * Retrieves a task by its ID.
     *
     * @param id the ID of the task to retrieve
     * @return an Optional containing the task if found, or empty if not found
     */
    Optional<TaskQuery> get(Long id);

    /**
     * Updates an existing task.
     *
     * @param id      the ID of the task to update
     * @param command the command containing the updated details of the task
     * @return an Optional containing the updated task if found, or empty if not found
     */
    Optional<TaskQuery> update(Long id, UpdateTaskCommand command);

    /**
     * Deletes a task by its ID. If the task is not found, the method returns false.
     *
     * @param id the ID of the task to delete
     * @return true if the task was deleted, false if the task was not found
     */
    boolean delete(Long id);

    /**
     * Retrieves all tasks.
     *
     * @return a list of all tasks as TaskQuery objects
     */
    List<TaskQuery> getAllTaskQueries();
}