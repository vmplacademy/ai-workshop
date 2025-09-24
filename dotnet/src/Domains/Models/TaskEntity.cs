namespace TodoApp.Domains.Models;

using System;
using TodoApp.Dtos;

public class TaskEntity
{
    public long Id { get; set; }
    public string TaskName { get; set; } = string.Empty;
    public DateTime DueDate { get; set; }
    public TaskStatus Status { get; set; }
    public string? Description { get; set; }
    public DateTime CreatedAt { get; set; } = DateTime.UtcNow;
    public DateTime? UpdatedAt { get; set; }
}
