package org.example.ControllerTest;

import lombok.RequiredArgsConstructor;
import org.example.Entity.User;
import org.example.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserControllerTest {
    private final UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.readById(id));
    }

    @PutMapping("/{id}/update")
    public void updateUser(@PathVariable long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}

/*
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        try {
            userService.createUser(user);
            return "redirect:/user/getAll";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable long id, Model model) {
        Optional<User> user = userService.readById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user-info";
        }
        return "redirect:/not-found";
    }

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

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable long id) {
        try {
            userService.deleteUser(id);
            return "redirect:/user/getAll";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping("/getByEmail")
    public String getUserByEmail(@RequestParam String email, Model model) {
        User user = userService.getByEmail(email);
        if (user != null) {
            model.addAttribute("user", user);
            return "user-info";
        }
        return "redirect:/not-found";
    }

    @GetMapping("/getAll")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "/users-list";
    }

}
*/
