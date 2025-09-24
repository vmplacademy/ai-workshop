namespace TodoApp.Domains.Services.Tests;

using System;
using Microsoft.EntityFrameworkCore;
using Xunit;
using TodoApp.Dtos;
using TodoApp.Domains.Repositories;

public class DbContextFixture : IDisposable
{
    public TodoAppDbContext Db { get; }

    public DbContextFixture()
    {
        var options = new DbContextOptionsBuilder<TodoAppDbContext>()
            .UseInMemoryDatabase("SharedTestDb")
            .Options;
        Db = new TodoAppDbContext(options);
    }

    public void Dispose()
    {
        GC.SuppressFinalize(this);
    }
}

public class TaskServiceTest(DbContextFixture fixture) : IClassFixture<DbContextFixture>
{
    private readonly TodoAppDbContext _db = fixture.Db;

    public class CreateTask(DbContextFixture fixture) : TaskServiceTest(fixture)
    {
        [Fact]
        public void Should_Return_TaskQuery_When_CreateTask_WithValidCommand()
        {
            // given
            var service = new TaskService(_db);
            var command = new CreateTaskCommand("Test task", DateTime.UtcNow.AddDays(3), "Desc");

            // when
            var result = service.CreateTask(command);

            // then
            Assert.NotNull(result);
            Assert.True(result.Id > 0);
            Assert.Equal(command.TaskName, result.TaskName);
            Assert.Equal(command.DueDate, result.DueDate);
            Assert.Equal(command.Description, result.Description);
            Assert.Equal(TaskStatus.TODO, result.Status);
            Assert.True(result.CreatedAt <= DateTime.UtcNow);
        }

        [Fact]
        public void Should_Set_Status_To_TODO_When_CreateTask()
        {
            // given
            var service = new TaskService(_db);
            var command = new CreateTaskCommand("Another task", DateTime.UtcNow.AddDays(1), "desc");

            // when
            var result = service.CreateTask(command);

            // then
            Assert.Equal(TaskStatus.TODO, result.Status);
        }
    }

    public class GetTaskById(DbContextFixture fixture) : TaskServiceTest(fixture)
    {
        [Fact]
        public void Should_Return_Null_When_GetTaskById_AndTaskDoesNotExist()
        {
            // given
            var service = new TaskService(_db);

            // when
            var result = service.GetTaskById(12345);

            // then
            Assert.Null(result);
        }

        [Fact]
        public void Should_Return_Task_When_GetTaskById_AndTaskExists()
        {
            // given
            var service = new TaskService(_db);
            var created = service.CreateTask(new CreateTaskCommand("Find me", DateTime.UtcNow.AddDays(1), "desc"));

            // when
            var fetched = service.GetTaskById(created.Id);

            // then
            Assert.NotNull(fetched);
            Assert.Equal(created.Id, fetched!.Id);
            Assert.Equal(created.TaskName, fetched.TaskName);
        }
    }

    public class UpdateTask(DbContextFixture fixture) : TaskServiceTest(fixture)
    {
        [Fact]
        public void Should_Update_Task_When_UpdateTask_AndTaskExists()
        {
            // given
            var service = new TaskService(_db);
            var created = service.CreateTask(new CreateTaskCommand("To be updated", DateTime.UtcNow.AddDays(2), "old"));
            var updateCmd = new UpdateTaskCommand(
                "Updated name",
                DateTime.UtcNow.AddDays(5),
                TaskStatus.IN_PROGRESS,
                "new desc"
            );

            // when
            var updated = service.UpdateTask(created.Id, updateCmd);

            // then
            Assert.NotNull(updated);
            Assert.Equal(created.Id, updated!.Id);
            Assert.Equal(updateCmd.TaskName, updated.TaskName);
            Assert.Equal(updateCmd.DueDate, updated.DueDate);
            Assert.Equal(updateCmd.Description, updated.Description);
            Assert.Equal(updateCmd.Status, updated.Status);
            Assert.NotNull(updated.UpdatedAt);
            Assert.True(updated.UpdatedAt > created.CreatedAt);
        }

        [Fact]
        public void Should_Return_Null_When_UpdateTask_AndTaskDoesNotExist()
        {
            // given
            var service = new TaskService(_db);
            var updateCmd = new UpdateTaskCommand(
                "Nonexistent",
                DateTime.UtcNow.AddDays(1),
                TaskStatus.DONE,
                "desc"
            );

            // when
            var result = service.UpdateTask(9999, updateCmd);

            // then
            Assert.Null(result);
        }
    }

    public class DeleteTask(DbContextFixture fixture) : TaskServiceTest(fixture)
    {
        [Fact]
        public void Should_Remove_And_Return_True_When_DeleteTask_AndTaskExists()
        {
            // given
            var service = new TaskService(_db);
            var created = service.CreateTask(new CreateTaskCommand("To be deleted", DateTime.UtcNow.AddDays(1), "x"));

            // when
            var deleted = service.DeleteTask(created.Id);

            // then
            Assert.True(deleted);
            var fetched = service.GetTaskById(created.Id);
            Assert.Null(fetched);
        }

        [Fact]
        public void Should_Return_False_When_DeleteTask_AndTaskDoesNotExist()
        {
            // given
            var service = new TaskService(_db);

            // when
            var result = service.DeleteTask(9999);

            // then
            Assert.False(result);
        }
    }
}