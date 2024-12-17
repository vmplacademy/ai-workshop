package pl.vm.aiworkshop.dto;

import lombok.Builder;
import pl.vm.aiworkshop.domain.model.TaskStatus;

@Builder
public record EditTaskCommand(String taskName, String dueDate, TaskStatus status, String description) {

}