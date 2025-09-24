using TodoApp.Dtos;

namespace TodoApp.Domains.Services;

public interface ITaskService
{
    TaskQuery CreateTask(CreateTaskCommand command);
    TaskQuery? GetTaskById(long id);
    TaskQuery? UpdateTask(long id, UpdateTaskCommand command);
    bool DeleteTask(long id);
}