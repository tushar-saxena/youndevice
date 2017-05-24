package com.youndevice.rest.service.apiServices;

import com.youndevice.domain.Device;
import com.youndevice.domain.User;
import com.youndevice.rest.dto.ApiResponseDTO;
import com.youndevice.rest.dto.DeviceDTO;
import com.youndevice.rest.service.CustomUserDetailsService;
import com.youndevice.services.repoServices.DeviceRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceApiService {

    @Autowired
    private DeviceRepoService deviceRepoService;

    private CustomUserDetailsService customUserDetailsService;

    //TODO Instead of returning Domain object, return DTO
    public ApiResponseDTO<List<DeviceDTO>> findByUserId(Long userId, Integer max, Integer offset) {
        Pageable pageable = new PageRequest(0, max);
        List<Device> devices = deviceRepoService.findAllByUserId(userId, pageable);
        ApiResponseDTO<List<DeviceDTO>> responseDTO = new ApiResponseDTO<>();
        if (CollectionUtils.isEmpty(devices)) {
            List<DeviceDTO> deviceDTOList = new ArrayList<>(devices.size());
            for (Device device : devices) {
                deviceDTOList.add(DeviceDTO.createDeviceDTO(device));
            }
            responseDTO.setData(deviceDTOList);
        }
        responseDTO.setStatus(Boolean.TRUE);
        responseDTO.setMessage("Devices Fetched Successfully");
        return responseDTO;
    }

    public ApiResponseDTO<String> getDeviceStatus(Long deviceId) {
        Device device = deviceRepoService.getOne(deviceId);
        return new ApiResponseDTO<>("Device status fetched successfully", Boolean.TRUE, device.getStatus());
    }

    public ApiResponseDTO<String> setDeviceStatus(Long deviceId, String deviceStatus) {
        Device device = deviceRepoService.getOne(deviceId);
        device.setStatus(deviceStatus);
        device = deviceRepoService.save(device);
        return new ApiResponseDTO<String>("Device status updated successfully", Boolean.TRUE, device.getStatus());
    }

    public ApiResponseDTO<DeviceDTO> registerDevice(DeviceDTO deviceDTO) {
        ApiResponseDTO<DeviceDTO> responseDTO = new ApiResponseDTO<DeviceDTO>("Device saved successfully", Boolean.TRUE, deviceDTO);
        //TODO
        User user = customUserDetailsService.loadUserByUsername("ajay.kumar1@abc.com");

        //TODO Add validation
        //TODO Handle the failure case
        Device device = new Device();
        device.setEnabled(device.getEnabled());
        device.setDeviceType(deviceDTO.getDeviceType());
        device.setUserFriendlyName(deviceDTO.getUserFriendlyName());
        device.setStatus(device.getStatus());
        device.addUser(user);
        device = deviceRepoService.save(device);
        deviceDTO.setId(device.getId());
        return responseDTO;

    }
}
