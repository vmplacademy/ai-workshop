using System;

namespace TodoApp.Dtos;

public record TaskQuery(
    long Id,
    string TaskName,
    DateTime DueDate,
    TaskStatus Status,
    string? Description,
    DateTime CreatedAt,
    DateTime? UpdatedAt
);