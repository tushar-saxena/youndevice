package com.youndevice.rest.controller;

import com.youndevice.rest.dto.ApiResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
public class UtilController {


    @RequestMapping("/info")
    public ApiResponseDTO getInfo() {
        return new ApiResponseDTO("Welcome to ynd", true,200);
    }


}
