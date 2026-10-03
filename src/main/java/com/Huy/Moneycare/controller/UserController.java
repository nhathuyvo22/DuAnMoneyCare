package com.Huy.Moneycare.controller;

import com.Huy.Moneycare.model.User;
import com.Huy.Moneycare.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @PostMapping
    public String create(@RequestBody User user) {
        service.createUser(user);
        return "User added successfully!";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return service.updateUser(user) ? "Updated!" : "User not found";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteUser(id) ? "Deleted!" : "User not found";
    }
}