package pl.vm.aiworkshop.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateTaskCommand(
    @NotBlank(message = "Task name is required")
    String taskName,
    
    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date must be in the present or future")
    LocalDateTime dueDate,
    
    String description
) {}