package org.example.ControllerTest;

import lombok.RequiredArgsConstructor;
import org.example.Entity.Task;
import org.example.Service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskControllerTest {
   /* private final TaskService taskService;

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
    }*/
}
