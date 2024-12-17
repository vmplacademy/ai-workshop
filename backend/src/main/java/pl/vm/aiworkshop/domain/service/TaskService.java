package pl.vm.aiworkshop.domain.service;

import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.EditTaskCommand;
import pl.vm.aiworkshop.dto.TaskQuery;

import java.util.Optional;

/**
 * Service interface for managing tasks.
 */
public interface TaskService {

    /**
     * Adds a new task based on the provided command.
     *
     * @param command the command containing the details of the task to be created
     * @return the created task as a {@link TaskQuery}
     */
    TaskQuery addTask(CreateTaskCommand command);

    /**
     * Retrieves a task by its ID.
     *
     * @param id the ID of the task to retrieve
     * @return an Optional containing the task if found, or empty if not found
     */
    Optional<TaskQuery> getTaskById(Long id);

    /**
     * Edits an existing task based on the provided command.
     *
     * @param id the ID of the task to edit
     * @param command the command containing the updated details of the task
     * @return an Optional containing the updated task if found, or empty if not found
     */
    Optional<TaskQuery> editTask(Long id, EditTaskCommand command);

    /**
     * Deletes a task by its ID.
     *
     * @param id the ID of the task to delete
     * @return true if the task was successfully deleted, false otherwise
     */
    boolean deleteTask(Long id);
}