package pl.baranmateusz.todoprojektjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.validation.Valid;
import java.time.Instant;

@Controller
public class TodoTaskController {
    private final Logger logger = LoggerFactory.getLogger(TodoTaskController.class);

    @Autowired
    private TodoTaskRepository todoTaskRepository;

    @GetMapping("/")
    public ModelAndView index()
    {
        logger.info("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoTasks", todoTaskRepository.findAll());

        return modelAndView;
    }

    @PostMapping("/todo")
    public  String createTodoTask(@Valid TodoTask todoTask, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "create-todo-task";
        }

        todoTask.setCreatedTaskDate(Instant.now());
        todoTaskRepository.save(todoTask);
        return "redirect:/";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoTask(@PathVariable("id") long id, @Valid TodoTask todoTask, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            todoTask.setId(id);
            return "update-todo-task";
        }

        if(todoTask.isComplete())
        {
            todoTask.setFinishTaskDate(Instant.now());
        }

        todoTaskRepository.save(todoTask);
        return "redirect:/";
    }
}
