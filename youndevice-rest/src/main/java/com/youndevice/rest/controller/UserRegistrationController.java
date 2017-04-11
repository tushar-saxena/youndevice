package com.youndevice.rest.controller;

import com.youndevice.rest.constants.RestConstants;
import com.youndevice.rest.dto.ApiResponseDTO;
import com.youndevice.rest.service.apiServices.UserRegistrationApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {

    @Autowired
    UserRegistrationApiService userRegistrationApiService;

    @RequestMapping("/logout")
    public ApiResponseDTO logout(@RequestHeader(RestConstants.AUTH_TOKEN_HEADER) String authToken) {
        return userRegistrationApiService.performLogoutRelatedOperation(authToken);
    }
}
