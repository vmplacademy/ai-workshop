namespace TodoApp.Domains.Services;

using TodoApp.Dtos;
using TodoApp.Domains.Models;
using TodoApp.Domains.Repositories;

public class TaskService : ITaskService
{
    private readonly TodoAppDbContext _db;

    public TaskService(TodoAppDbContext db)
    {
        _db = db;
    }

    public TaskQuery CreateTask(CreateTaskCommand command)
    {
        var entity = new TaskEntity
        {
            TaskName = command.TaskName,
            DueDate = command.DueDate,
            Status = TaskStatus.CREATED,
            Description = command.Description,
            CreatedAt = DateTime.UtcNow
        };
        
        _db.Tasks.Add(entity);
        _db.SaveChanges();
        return ToQuery(entity);
    }

    public TaskQuery? GetTaskById(long id)
    {
        var entity = _db.Tasks.Find(id);
        return entity == null ? null : ToQuery(entity);
    }

    public TaskQuery? UpdateTask(long id, UpdateTaskCommand command)
    {
        var entity = _db.Tasks.Find(id);

        if (entity == null) return null;

        entity.TaskName = command.TaskName;
        entity.DueDate = command.DueDate;
        entity.Status = command.Status;
        entity.Description = command.Description;
        entity.UpdatedAt = DateTime.UtcNow;

        _db.SaveChanges();
        return ToQuery(entity);
    }

    public bool DeleteTask(long id)
    {
        var entity = _db.Tasks.Find(id);
        if (entity == null) return false;
        _db.Tasks.Remove(entity);
        _db.SaveChanges();
        return true;
    }

    public IEnumerable<TaskQuery> GetAllTasks()
    {
        return _db.Tasks.Select(ToQuery).ToList();
    }

    private static TaskQuery ToQuery(TaskEntity entity) =>
        new TaskQuery(
            entity.Id,
            entity.TaskName,
            entity.DueDate,
            entity.Status,
            entity.Description,
            entity.CreatedAt,
            entity.UpdatedAt
        );
}
