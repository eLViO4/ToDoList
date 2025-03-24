package org.example.Service;

import jakarta.persistence.EntityNotFoundException;
import org.example.Entity.User;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (!isValidUser(user)) {
            throw new IllegalArgumentException("Invalid user object. Fields cannot be null or empty.");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User already exists.");
        }
        userRepository.save(user);
    }

    public Optional<User> readById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Task id cannot be null or less than null");
        }
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("User does not exist.");
        }
        return userRepository.findById(id);
    }

    public void updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (!userRepository.existsById(user.getId())) {
            throw new EntityNotFoundException("User does not exist");
        }

        userRepository.save(user);
    }

    public void deleteUser(long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("User does not exist.");
        }
        userRepository.deleteById(id);
    }

    public User getByEmail(String email) {
        if (userRepository.findByEmail(email) == null) {
            throw new EntityNotFoundException("User does not exist.");
        }
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    private boolean isValidUser(User user) {
        return user != null
                && user.getEmail() != null && !user.getEmail().isBlank()
                && user.getFirstname() != null && !user.getFirstname().isBlank()
                && user.getLastname() != null && !user.getLastname().isBlank();
    }
}
