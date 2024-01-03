package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public void postUser(@RequestBody User user){ service.createUser(user);}

    @GetMapping
    public List<User> getUsers(){ return service.getUsers(); }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){ return service.getUserById(id); }

    @GetMapping("/sort/{sortBy}")
    public ResponseEntity<Page<User>> sortUserByLogin(@RequestParam(defaultValue = "0") int pageNo,
                                @RequestParam(defaultValue = "10") int pageSize,
                                @PathVariable String sortBy,
                                @RequestParam(defaultValue = "asc") String sortOrder)
    {
        Page<User> usersPage = service.sortUsersByLogin(pageNo, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(usersPage, HttpStatus.OK);
    }

    @GetMapping("/{login}")
    public List<User> getUserByLogin(@PathVariable String login){ return service.getUserByLogin(login); }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user){service.updateUser(user, id);}

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable Long id){service.deleteUser(id);}
}
