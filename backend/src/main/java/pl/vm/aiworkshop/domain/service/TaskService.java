package pl.vm.aiworkshop.domain.service;

import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.TaskQuery;
import pl.vm.aiworkshop.dto.UpdateTaskCommand;

import java.util.Optional;

/**
 * Service interface for managing tasks in the ToDo List application.
 * Provides operations for creating, retrieving, updating, and deleting tasks.
 */
public interface TaskService {

    /**
     * Creates a new task based on the provided command data.
     *
     * @param command the data transfer object containing task creation details
     * @return an Optional containing the created task as a TaskQuery if successful, empty Optional otherwise
     */
    Optional<TaskQuery> createTask(CreateTaskCommand command);

    /**
     * Retrieves a task by its unique identifier.
     *
     * @param id the unique identifier of the task to retrieve
     * @return an Optional containing the task as a TaskQuery if found, empty Optional otherwise
     */
    Optional<TaskQuery> getTaskById(Long id);

    /**
     * Updates an existing task identified by its ID with the provided command data.
     *
     * @param id      the unique identifier of the task to update
     * @param command the data transfer object containing the task update details
     * @return an Optional containing the updated task as a TaskQuery if successful, empty Optional otherwise
     */
    Optional<TaskQuery> updateTask(Long id, UpdateTaskCommand command);

    /**
     * Deletes a task by its unique identifier.
     *
     * @param id the unique identifier of the task to delete
     */
    boolean deleteTask(Long id);
}