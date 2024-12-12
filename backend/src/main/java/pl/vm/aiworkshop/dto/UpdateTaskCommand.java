package pl.vm.aiworkshop.dto;

import lombok.Builder;
import pl.vm.aiworkshop.domain.model.TaskStatus;

import java.time.LocalDateTime;

@Builder
public record UpdateTaskCommand(
        Long id,
        String taskName,
        LocalDateTime dueDate,
        TaskStatus status,
        String description
) {
}