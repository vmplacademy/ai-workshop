package pl.vm.aiworkshop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.vm.aiworkshop.domain.model.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
