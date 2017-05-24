package com.youndevice.rest.controller;

import com.youndevice.rest.dto.ApiResponseDTO;
import com.youndevice.rest.dto.DeviceDTO;
import com.youndevice.rest.service.apiServices.DeviceApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/device")
public class DeviceController extends BaseController {


    @Autowired
    DeviceApiService deviceApiService;

    @RequestMapping(value = "/list", produces = "application/json")
    public ApiResponseDTO<List<DeviceDTO>> listDevicesByUserId(@RequestParam(value = "userId", defaultValue = "5") Long userId,
                                                                  @RequestParam(value = "max", defaultValue = "10") Integer max,
                                                                  @RequestParam(value = "offset", defaultValue = "0") Integer offset) {
        return deviceApiService.findByUserId(userId, max, offset);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResponseDTO registerDevice(@RequestBody DeviceDTO deviceDTO) {
        //TODO Ask the flow to register any Devices
        return deviceApiService.registerDevice(deviceDTO);
    }

    //THis api will be used by device to get current Status
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ApiResponseDTO<String> getDeviceStatus(@RequestParam(value = "device_id", defaultValue = "5") Long deviceId) {
        return deviceApiService.getDeviceStatus(deviceId);
    }


    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public ApiResponseDTO<String> setDeviceActualDeviceStatus(@RequestParam(value = "device_id", defaultValue = "5") Long deviceId,
                                                                 @RequestParam(value = "device_status", defaultValue = "0") String deviceStatus) {
        return deviceApiService.setDeviceStatus(deviceId, deviceStatus);
    }
}