namespace TodoApp.Domains.Repositories;

using Microsoft.EntityFrameworkCore;
using TodoApp.Domains.Models;

public class TodoAppDbContext : DbContext
{
    public TodoAppDbContext(DbContextOptions<TodoAppDbContext> options)
        : base(options)
    {
    }

    public DbSet<TaskEntity> Tasks { get; set; }
}
