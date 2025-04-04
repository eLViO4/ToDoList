package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Entity.User;
import org.example.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        try {
            userService.createUser(user);
            return "redirect:/user/getAll";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    @GetMapping("/{id}")
    public String getUserById(@PathVariable long id, Model model) {
        Optional<User> user = userService.readById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user-info";
        }
        return "redirect:/not-found";
    }

    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    @PutMapping("/{id}/update")
    public String updateUser(@PathVariable long id, @ModelAttribute User user) {
        try {
            user.setId(id);
            userService.updateUser(user);
            return "redirect:/user/getAll";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable long id) {
        try {
            userService.deleteUser(id);
            return "redirect:/user/getAll";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getByEmail")
    public String getUserByEmail(@RequestParam String email, Model model) {
        User user = userService.getByEmail(email);
        if (user != null) {
            model.addAttribute("user", user);
            return "user-info";
        }
        return "redirect:/not-found";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "all-users";
    }

}

