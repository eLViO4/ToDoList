package org.example.ControllerTest;


import lombok.RequiredArgsConstructor;
import org.example.Entity.ToDo;
import org.example.Service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoControllerTest {
    private final ToDoService toDoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getToDo(@PathVariable long id) {
        return ResponseEntity.ok(toDoService.readById(id));
    }

    @PostMapping("/create")
    public void createToDo(@RequestBody ToDo toDo) {
        toDoService.createToDo(toDo);
    }

    @PutMapping("/{id}/update")
    public void updateToDo(@PathVariable long id, @RequestBody ToDo toDo) {
        toDo.setId(id);
        toDoService.updateToDo(toDo);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteToDo(@PathVariable long id) {
        toDoService.deleteToDo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(toDoService.getAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(toDoService.getByUserId(userId));
    }
}
