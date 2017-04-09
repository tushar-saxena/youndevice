package com.youndevice.rest.service.apiServices;

import com.youndevice.domain.Device;
import com.youndevice.dto.ResponseDTO;
import com.youndevice.rest.dto.ApiResponseDTO;
import com.youndevice.services.repoServices.DeviceRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceApiService {

    @Autowired
    private DeviceRepoService deviceRepoService;

    //TODO Instead of returning Domain object, return DTO
    public ResponseDTO<List<Device>> findByUserId(Long userId, Integer max, Integer offset) {
        Pageable pageable = new PageRequest(0, max);
        List<Device> appliances = deviceRepoService.findAllByUserId(userId, pageable);
        ResponseDTO<List<Device>> responseDTO = new ResponseDTO<List<Device>>();
        responseDTO.setData(appliances);
        responseDTO.setStatus(Boolean.TRUE);
        responseDTO.setMessage("Devices Fetched Successfully");
        return responseDTO;
    }

    public ApiResponseDTO<String> getDeviceStatus(Long applianceId) {
        Device appliance = deviceRepoService.getOne(applianceId);
        return new ApiResponseDTO<String>("Device status fetched successfully", Boolean.TRUE, appliance.getStatus());
    }

    public ApiResponseDTO<String> setDeviceStatus(Long applianceId, String applianceStatus) {
        Device appliance = deviceRepoService.getOne(applianceId);
        appliance.setStatus(applianceStatus);
        appliance = deviceRepoService.save(appliance);
        return new ApiResponseDTO<String>("Device status updated successfully", Boolean.TRUE, appliance.getStatus());
    }
}
