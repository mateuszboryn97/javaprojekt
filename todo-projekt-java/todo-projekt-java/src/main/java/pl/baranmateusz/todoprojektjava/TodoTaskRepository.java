package pl.baranmateusz.todoprojektjava;

import org.springframework.data.repository.CrudRepository;

public interface TodoTaskRepository extends CrudRepository<TodoTask, Long> {
}
