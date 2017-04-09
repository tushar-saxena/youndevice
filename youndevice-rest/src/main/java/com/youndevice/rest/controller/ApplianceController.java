package com.youndevice.rest.controller;

import com.youndevice.dto.ResponseDTO;
import com.youndevice.rest.dto.ApiResponseDTO;
import com.youndevice.rest.service.apiServices.ApplianceApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/appliance")
public class ApplianceController extends BaseController {


    @Autowired
    ApplianceApiService applianceApiService;

    @RequestMapping("/list")
    public ResponseDTO listAppliancesByUserId(@RequestParam(value = "userId", defaultValue = "5") Long userId,
                                              @RequestParam(value = "max", defaultValue = "10") Integer max,
                                              @RequestParam(value = "offset", defaultValue = "0") Integer offset) {
        return applianceApiService.findByUserId(userId, max, offset);
    }


    @RequestMapping("/register")
    public ApiResponseDTO registerAppliance() {
        //TODO Ask the flow to register any Appliances
        return null;
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ApiResponseDTO<String> getApplianceStatus(@RequestParam(value = "appliance_id", defaultValue = "5") Long applianceId) {
        return applianceApiService.getApplianceStatus(applianceId);
    }


    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public ApiResponseDTO<String> setApplianceStatus(@RequestParam(value = "appliance_id", defaultValue = "5") Long applianceId,
                                                     @RequestParam(value = "appliance_status", defaultValue = "0") String applianceStatus) {
        return applianceApiService.setApplianceStatus(applianceId, applianceStatus);
    }
}