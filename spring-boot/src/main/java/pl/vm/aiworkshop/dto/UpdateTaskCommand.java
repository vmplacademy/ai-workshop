package pl.vm.aiworkshop.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UpdateTaskCommand(
        String taskName,
        LocalDateTime dueDate,
        String status,
        String description
) {

}