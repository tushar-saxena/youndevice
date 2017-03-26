package com.youndevice.rest.controller;
import com.youndevice.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/index")
    public User getDetails(@RequestParam(value="name", defaultValue="World") String name,
                           @RequestParam(value="id", defaultValue="1") String id) {
        return new User(id,name);
    }


    @RequestMapping("/admin")
    public User getAdminDetails(@RequestParam(value="name", defaultValue="World") String name,
                           @RequestParam(value="id", defaultValue="1") String id) {
        return new User(id,name);
    }
}