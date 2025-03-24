package org.example.Service;

import jakarta.persistence.EntityNotFoundException;
import org.example.Entity.ToDo;
import org.example.Repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    public void createToDo(ToDo toDo) {
        if (toDo == null) {
            throw new IllegalArgumentException("ToDo cannot be null");
        }
        if (toDoRepository.existsById(toDo.getId())) {
            throw new IllegalArgumentException("ToDo already exists");
        }
        toDoRepository.save(toDo);
    }

    public Optional<ToDo> readById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id cannot be null or less than 0");
        }
        if (toDoRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("ToDo does not exist.");
        }
        return toDoRepository.findById(id);
    }

    public void updateToDo(ToDo toDo) {
        if (toDo == null) {
            throw new IllegalArgumentException("ToDo cannot be null");
        }
        if (!toDoRepository.existsById(toDo.getId())) {
            throw new EntityNotFoundException("ToDo does not exist.");
        }
        toDoRepository.save(toDo);
    }

    public void deleteToDo(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id cannot be null or less than 0");
        }
        toDoRepository.deleteById(id);
    }

    public List<ToDo> getAll() {
        return toDoRepository.findAll();
    }

    public List<ToDo> getByUserId(long userId) {
        return toDoRepository.findByOwnerId(userId);
    }

}
