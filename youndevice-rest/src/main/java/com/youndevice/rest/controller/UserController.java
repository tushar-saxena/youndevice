package com.youndevice.rest.controller;

import com.youndevice.domain.User;
import com.youndevice.rest.dto.ApiResponseDTO;
import com.youndevice.rest.dto.UserDTO;
import com.youndevice.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public User getDetails(@RequestParam(value = "name", defaultValue = "Usser") String name,
                           @RequestParam(value = "id", defaultValue = "1") String id) {
        return new User(id, name);
    }


    @RequestMapping("/admin")
    public User getAdminDetails(@RequestParam(value = "name", defaultValue = "admin") String name,
                                @RequestParam(value = "id", defaultValue = "1") String id) {
        return new User(id, name);
    }

    @RequestMapping("/register")
    public ApiResponseDTO register(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);

    }
}