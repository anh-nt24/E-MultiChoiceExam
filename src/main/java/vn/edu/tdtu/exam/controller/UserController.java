package vn.edu.tdtu.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.exam.dto.UserDTO;
import vn.edu.tdtu.exam.model.User;
import vn.edu.tdtu.exam.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.add(userDTO);
    }
}





