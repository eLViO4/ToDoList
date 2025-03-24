package org.example.Service;

import jakarta.persistence.EntityNotFoundException;
import org.example.Entity.Task;
import org.example.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public void createTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        taskRepository.save(task);
    }

    public Task findTaskById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Task id cannot be null or less than 0");
        }
        return taskRepository.findById(id).orElse(null);
    }

    public void updateTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        if (!taskRepository.existsById(task.getId())) {
            throw new EntityNotFoundException("Task does not exist");
        }
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = findTaskById(id);
        if (task != null) {
            taskRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Task does not exist");
        }
    }


    public List<Task> getByToDoId(long todoId) {
        return taskRepository.findByToDoId(todoId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
