package pl.baranmateusz.todoprojektjava.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.baranmateusz.todoprojektjava.Model.TodoTask;
import pl.baranmateusz.todoprojektjava.Repository.TodoTaskRepository;

import java.time.Instant;

@Controller
public class TodoTaskFormController {

    @Autowired
    private TodoTaskRepository todoTaskRepository;

    @GetMapping("/create-todo")
    public String showCreateForm(TodoTask todoTask)
    {
        return "create-todo-task";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model)
    {
        TodoTask todoTask = todoTaskRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoTask id: " + id + " not found"));

        model.addAttribute("todo", todoTask);
        return "update-todo-task";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoTask(@PathVariable("id") long id, Model model)
    {
        TodoTask todoTask = todoTaskRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoTask id: " + id + " not found"));

        todoTaskRepository.delete(todoTask);
        return "redirect:/";
    }
    @GetMapping("/finish/{id}")
    public String finishTodoTask(@PathVariable("id") long id, Model model)
    {
        TodoTask todoTask = todoTaskRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoTask id: " + id + " not found"));

        if(!todoTask.isComplete())
        {
            todoTask.setComplete(true);
            todoTask.setPriority(false);
            todoTask.setFinishTaskDate(Instant.now());
        }
        else
        {
            todoTask.setComplete(false);
            todoTask.setFinishTaskDate(null);
        }

        todoTaskRepository.save(todoTask);
        return "redirect:/";
    }

    @GetMapping("/priority/{id}")
    public String setPriorityTodoTask(@PathVariable("id") long id, Model model)
    {
        TodoTask todoTask = todoTaskRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoTask id: " + id + " not found"));
        if(!todoTask.isComplete())
        {
            todoTask.setPriority(!todoTask.isPriority());
            todoTaskRepository.save(todoTask);
        }
        return "redirect:/";
    }
}
