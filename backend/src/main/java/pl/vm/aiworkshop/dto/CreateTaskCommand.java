package pl.vm.aiworkshop.dto;

import lombok.Builder;

@Builder
public record CreateTaskCommand(
        String taskName,
        String dueDate,
        String description
) {
}