package com.youndevice.rest.controller;

import com.youndevice.domain.User;
import com.youndevice.rest.dto.ApiResponseDTO;
import com.youndevice.rest.dto.UserDTO;
import com.youndevice.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
public class UtilController {


    @RequestMapping("/info")
    public ApiResponseDTO getInfo() {
        return new ApiResponseDTO("Welcome to ynd", true,200);
    }


}
