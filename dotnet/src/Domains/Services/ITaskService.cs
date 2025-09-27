namespace TodoApp.Domains.Services;

using TodoApp.Dtos;

/// <summary>
/// Service contract for managing tasks within the Todo domain.
/// Provides methods for creating, retrieving, updating, deleting, and listing tasks.
/// </summary>
public interface ITaskService
{
    /// <summary>
    /// Creates a new task based on the provided command.
    /// </summary>
    /// <param name="command">The command containing task creation details.</param>
    /// <returns>The created <see cref="TaskQuery"/> DTO.</returns>
    /// <example>
    /// <code>
    /// var createdTask = taskService.CreateTask(new CreateTaskCommand { ... });
    /// </code>
    /// </example>
    TaskQuery CreateTask(CreateTaskCommand command);

    /// <summary>
    /// Retrieves a task by its unique identifier.
    /// </summary>
    /// <param name="id">The unique identifier of the task.</param>
    /// <returns>The <see cref="TaskQuery"/> DTO if found; otherwise, <c>null</c>.</returns>
    /// <example>
    /// <code>
    /// var task = taskService.GetTaskById(42);
    /// </code>
    /// </example>
    TaskQuery? GetTaskById(long id);

    /// <summary>
    /// Updates an existing task with the specified identifier using the provided command.
    /// </summary>
    /// <param name="id">The unique identifier of the task to update.</param>
    /// <param name="command">The command containing updated task details.</param>
    /// <returns>The updated <see cref="TaskQuery"/> DTO if successful; otherwise, <c>null</c>.</returns>
    /// <example>
    /// <code>
    /// var updatedTask = taskService.UpdateTask(42, new UpdateTaskCommand { ... });
    /// </code>
    /// </example>
    TaskQuery? UpdateTask(long id, UpdateTaskCommand command);

    /// <summary>
    /// Deletes a task by its unique identifier.
    /// </summary>
    /// <param name="id">The unique identifier of the task to delete.</param>
    /// <returns><c>true</c> if the task was deleted; otherwise, <c>false</c>.</returns>
    /// <example>
    /// <code>
    /// bool deleted = taskService.DeleteTask(42);
    /// </code>
    /// </example>
    bool DeleteTask(long id);

    /// <summary>
    /// Retrieves all tasks in the system.
    /// </summary>
    /// <returns>An enumerable collection of <see cref="TaskQuery"/> DTOs.</returns>
    /// <example>
    /// <code>
    /// var allTasks = taskService.GetAllTasks();
    /// </code>
    /// </example>
    IEnumerable<TaskQuery> GetAllTasks();
}