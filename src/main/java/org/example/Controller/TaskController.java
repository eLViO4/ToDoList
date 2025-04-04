package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Entity.*;

import java.util.*;

import org.example.Service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
            return "task-info";
        } catch (Exception e) {
            return "redirect:/not-found";
        }
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        try {
            taskService.createTask(task);
            return "redirect:/all-todos-tasks";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PutMapping("/{id}")
    public String updateTask(@PathVariable("id") long id, @ModelAttribute Task task) {
        try {
            task.setId(id);
            taskService.updateTask(task);
            return "redirect:/task-info";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable("id") long id) {
        try {
            taskService.deleteTask(id);
            return "redirect:/all-todos-tasks";
        } catch (Exception e) {
            return "redirect:/not-found";
        }
    }

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "all-todos-tasks";
    }

    @GetMapping("/todo/{todoId}")
    public String getByToDoId(@PathVariable long todoId, Model model) {
        List<Task> tasks = taskService.getByToDoId(todoId);
        model.addAttribute("tasks", tasks);
        return "task-info";
    }
}

