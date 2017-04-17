package com.youndevice.rest.controller;

import com.youndevice.domain.User;
import com.youndevice.rest.dto.ApiResponseDTO;
import com.youndevice.rest.dto.UserDTO;
import com.youndevice.rest.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController extends BaseController {

    Log log = LogFactory.getLog(UserController.class);

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

    @RequestMapping(value = "/register")
    public ApiResponseDTO register(@Validated @RequestBody UserDTO userDTO) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Registration");
        log.info("firstname = " + userDTO.getFirstName());
        return userService.saveUser(userDTO);

    }
}