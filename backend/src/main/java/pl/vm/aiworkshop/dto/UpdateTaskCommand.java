package pl.vm.aiworkshop.dto;

import jakarta.validation.constraints.FutureOrPresent;
import pl.vm.aiworkshop.domain.model.TaskStatus;

import java.time.LocalDateTime;

public record UpdateTaskCommand(
    String taskName,
    
    @FutureOrPresent(message = "Due date must be in the present or future")
    LocalDateTime dueDate,
    
    TaskStatus status,
    
    String description
) {}