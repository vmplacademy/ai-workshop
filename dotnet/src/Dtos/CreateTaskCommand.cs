namespace TodoApp.Dtos;

using System;
using System.ComponentModel.DataAnnotations;

public record CreateTaskCommand(
    [Required]
    string TaskName,
    DateTime DueDate,
    string? Description
);