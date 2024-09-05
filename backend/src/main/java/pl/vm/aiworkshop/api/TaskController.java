package pl.vm.aiworkshop.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.vm.aiworkshop.domain.service.TaskService;
import pl.vm.aiworkshop.dto.CreateTaskCommand;
import pl.vm.aiworkshop.dto.TaskQuery;
import pl.vm.aiworkshop.dto.UpdateTaskCommand;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskQuery> create(@RequestBody CreateTaskCommand command) {
        Optional<TaskQuery> taskQuery = taskService.create(command);

        return taskQuery.map(
                task -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(task)
        ).orElseGet(
                () -> ResponseEntity
                        .badRequest()
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskQuery> get(@PathVariable Long id) {
        Optional<TaskQuery> taskQuery = taskService.get(id);

        return ResponseEntity.of(taskQuery);
    }

    @GetMapping
    public ResponseEntity<List<TaskQuery>> getAll() {
        List<TaskQuery> tasks = taskService.getAll();

        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskQuery> update(@PathVariable Long id, @RequestBody UpdateTaskCommand command) {
        Optional<TaskQuery> taskQuery = taskService.update(id, command);

        return ResponseEntity.of(taskQuery);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = taskService.delete(id);

        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}