using System;
using System.ComponentModel.DataAnnotations;

namespace TodoApp.Dtos;

public record UpdateTaskCommand(
    [Required]
    string TaskName,
    DateTime DueDate,
    [Required]
    string TaskStatus,
    string? Description
);