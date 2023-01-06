package pl.baranmateusz.todoprojektjava;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
@Table(name = "todo_task")
public class TodoTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "Opis jest wymagany!")
    private String taskDescription;

    @Getter
    @Setter
    private boolean complete;

    @Setter
    @Getter
    private boolean priority;

    @Getter
    @Setter
    private Instant createdTaskDate;

    @Getter
    @Setter
    private Instant finishTaskDate;

    public TodoTask() {}

    public TodoTask (String taskDescription)
    {
        this.taskDescription = taskDescription;
        this.complete = false;
        this.priority = false;
        this.createdTaskDate = Instant.now();
    }

    @Override
    public String toString()
    {
        return String.format("TodoTask{id=%d, taskDescription=%s, complete=%s, priority=%s, createdTaskDate=%s, finishTaskDate=%s}",
                id, taskDescription, complete, priority, createdTaskDate, finishTaskDate);
    }
}
