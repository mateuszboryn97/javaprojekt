package pl.baranmateusz.todoprojektjava.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.baranmateusz.todoprojektjava.Model.TodoTask;

public interface TodoTaskRepository extends CrudRepository<TodoTask, Long> {
}
