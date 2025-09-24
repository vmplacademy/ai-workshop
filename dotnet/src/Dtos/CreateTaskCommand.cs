using System;
using System.ComponentModel.DataAnnotations;

namespace TodoApp.Dtos;

public record CreateTaskCommand(
    [Required]
    string TaskName,
    DateTime DueDate,
    string TaskStatus,
    string? Description
);