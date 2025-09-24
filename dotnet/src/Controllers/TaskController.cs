using System;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using TodoApp.Dtos;
using TodoApp.Domains.Services;

namespace TodoApp.Controllers;

/// <summary>
/// Controller for Task CRUD operations.
/// </summary>
[ApiController]
[Route("api/tasks")]
[Produces("application/json")]
public class TaskController : ControllerBase
{
    private readonly ITaskService _taskService;

    public TaskController(ITaskService taskService)
    {
        _taskService = taskService;
    }

    /// <summary>
    /// Creates a new task.
    /// </summary>
    [HttpPost]
    [ProducesResponseType(StatusCodes.Status201Created)]
    [ProducesResponseType(StatusCodes.Status400BadRequest)]
    public ActionResult<TaskQuery> Create([FromBody] CreateTaskCommand command)
    {
        if (!ModelState.IsValid) return BadRequest(ModelState);

        var created = _taskService.CreateTask(command);
        return CreatedAtAction(nameof(Get), new { id = created.Id }, created);
    }

    /// <summary>
    /// Gets a task by id.
    /// </summary>
    [HttpGet("{id:long}")]
    [ProducesResponseType(StatusCodes.Status200OK)]
    [ProducesResponseType(StatusCodes.Status404NotFound)]
    public ActionResult<TaskQuery> Get(long id)
    {
        var task = _taskService.GetTaskById(id);
        if (task == null) return NotFound();
        return Ok(task);
    }

    /// <summary>
    /// Updates an existing task.
    /// </summary>
    [HttpPut("{id:long}")]
    [ProducesResponseType(StatusCodes.Status200OK)]
    [ProducesResponseType(StatusCodes.Status400BadRequest)]
    [ProducesResponseType(StatusCodes.Status404NotFound)]
    public ActionResult<TaskQuery> Update(long id, [FromBody] UpdateTaskCommand command)
    {
        if (!ModelState.IsValid) return BadRequest(ModelState);

        var updated = _taskService.UpdateTask(id, command);
        if (updated == null) return NotFound();
        return Ok(updated);
    }

    /// <summary>
    /// Deletes a task by id.
    /// </summary>
    [HttpDelete("{id:long}")]
    [ProducesResponseType(StatusCodes.Status204NoContent)]
    [ProducesResponseType(StatusCodes.Status404NotFound)]
    public IActionResult Delete(long id)
    {
        var deleted = _taskService.DeleteTask(id);
        if (!deleted) return NotFound();
        return NoContent();
    }
}