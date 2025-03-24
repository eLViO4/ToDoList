package org.example.ControllerTest;

import lombok.RequiredArgsConstructor;
import org.example.Entity.Task;
import org.example.Service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{id}")
    public String getTask(@PathVariable Long id, Model model) {
        try {
            Task task = taskService.findTaskById(id);
            model.addAttribute("task", task);
            return "todo-tasks";
        } catch (Exception e) {
            return "redirect:/not-found";
        }
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        try {
            taskService.createTask(task);
            return "redirect:/todo-tasks";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PutMapping("/{id}")
    public String updateTask(@PathVariable("id") long id, @ModelAttribute Task task) {
        try {
            task.setId(id);
            taskService.updateTask(task);
            return "redirect:/todo-tasks";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable("id") long id) {
        try {
            taskService.deleteTask(id);
            return "redirect:/todo-tasks";
        } catch (Exception e) {
            return "redirect:/not-found";
        }
    }

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "todo-tasks";
    }

    @GetMapping("/todo/{todoId}")
    public String getByToDoId(@PathVariable long todoId, Model model) {
        List<Task> tasks = taskService.getByToDoId(todoId);
        model.addAttribute("tasks", tasks);
        return "todo-tasks";
    }
}
*/
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskControllerTest {
    private final TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @PostMapping("/create")
    public void createTask(@RequestBody Task task) {
        taskService.createTask(task);
    }

    @PutMapping("/{id}/update")
    public void updateTask(@PathVariable long id, @RequestBody Task task) {
        task.setId(id);
        taskService.updateTask(task);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/todo/{todoId}")
    public ResponseEntity<?> getByToDoId(@PathVariable long todoId) {
        return ResponseEntity.ok(taskService.getByToDoId(todoId));
    }
}
