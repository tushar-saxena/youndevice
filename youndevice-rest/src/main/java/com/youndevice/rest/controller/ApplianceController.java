package com.youndevice.rest.controller;

import com.youndevice.dto.ResponseDTO;
import com.youndevice.rest.service.apiServices.ApplianceApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/appliance")
public class ApplianceController extends BaseController {


    @Autowired
    ApplianceApiService applianceApiService;

    @RequestMapping("/list")
    public ResponseDTO register(@RequestParam(value = "userId", defaultValue = "5") Long userId) {
        return applianceApiService.findByUserId(userId);
    }
}