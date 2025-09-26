namespace TodoApp.Dtos;

using System;
using System.ComponentModel.DataAnnotations;

public record UpdateTaskCommand(
    [Required]
    string TaskName,
    DateTime DueDate,
    [Required]
    TaskStatus Status,
    string? Description
);