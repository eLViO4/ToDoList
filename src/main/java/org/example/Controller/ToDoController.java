package org.example.Controller;


import lombok.RequiredArgsConstructor;
import org.example.Entity.ToDo;
import org.example.Service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;

    @GetMapping("/{id}")
    public String getToDo(@PathVariable long id, Model model) {
        Optional<ToDo> toDo = toDoService.readById(id);
        if (toDo.isPresent()) {
            model.addAttribute("toDo", toDo.get());
            return "todo-tasks";
        } else return "redirect:/not-found";
    }

    @PostMapping("/create")
    public String createToDo(@ModelAttribute ToDo toDo) {
        try {
            toDoService.createToDo(toDo);
            return "redirect:/todo/getAll";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PutMapping("/{id}/update")
    public String updateToDo(@PathVariable long id, @ModelAttribute ToDo toDo) {
        try {
            toDo.setId(id);
            toDoService.updateToDo(toDo);
            return "redirect:/todo/getAll";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @DeleteMapping("/{id}/delete")
    public String deleteToDo(@PathVariable long id) {
        try {
            toDoService.deleteToDo(id);
            return "redirect:/todo/getAll";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping("/getAll")
    public String getAll(Model model) {
        List<ToDo> toDos = toDoService.getAll();
        model.addAttribute("toDos", toDos);
        return "todo-lists";
    }

    @GetMapping("/user/{userId}")
    public String getByUserId(@PathVariable long userId, Model model) {
        List<ToDo> toDos = toDoService.getByUserId(userId);
        model.addAttribute("todos", toDos);
        return "todos-user";
    }

}

