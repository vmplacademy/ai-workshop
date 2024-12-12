package pl.vm.aiworkshop.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreateTaskCommand(
        String taskName,
        LocalDateTime dueDate,
        String description
) {

}