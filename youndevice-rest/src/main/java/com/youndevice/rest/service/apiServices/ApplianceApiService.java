package com.youndevice.rest.service.apiServices;

import com.youndevice.domain.Appliance;
import com.youndevice.domain.Device;
import com.youndevice.dto.ResponseDTO;
import com.youndevice.rest.dto.ApiResponseDTO;
import com.youndevice.rest.dto.ApplianceDTO;
import com.youndevice.rest.dto.DeviceDTO;
import com.youndevice.services.repoServices.AppliancesRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplianceApiService {

    @Autowired
    private AppliancesRepoService appliancesRepoService;

    //TODO Instead of returning Domain object, return DTO
    public ResponseEntity<List<ApplianceDTO>> findByUserId(Long userId, Integer max, Integer offset) {
        Pageable pageable = new PageRequest(0, max);
        List<Appliance> appliances = appliancesRepoService.findAllByUserId(userId, pageable);
        List<ApplianceDTO> applianceDTOList = new ArrayList<ApplianceDTO>();
        Device device;
        DeviceDTO deviceDTO;
        for (Appliance appliance : appliances) {
            device  = appliance.getDevice();
            deviceDTO = new DeviceDTO(device.getId(),device.getEnabled(),device.getDeviceType(),device.getUserFriendlyName(),device.getStatus());
            applianceDTOList.add(new ApplianceDTO(appliance.getId(),appliance.getWebStatus(),appliance.getActualDeviceStatus(),appliance.getUserFriendlyName(),deviceDTO));
        }
        ResponseEntity<List<ApplianceDTO>> responseEntity = new ResponseEntity<List<ApplianceDTO>>(applianceDTOList, HttpStatus.OK);
//        responseDTO.setData(applianceDTOList);
//        responseDTO.setStatus(Boolean.TRUE);
//        responseDTO.setMessage("Appliances Fetched Successfully");
        return responseEntity;
    }

    public ApiResponseDTO<String> getApplianceStatus(Long applianceId) {
        Appliance appliance = appliancesRepoService.getOne(applianceId);
        return new ApiResponseDTO<String>("Appliance status fetched successfully", Boolean.TRUE, appliance.getWebStatus());
    }

    public ApiResponseDTO<String> setApplianceStatus(Long applianceId, String applianceStatus) {
        Appliance appliance = appliancesRepoService.getOne(applianceId);
        appliance.setActualDeviceStatus(applianceStatus);
        appliance = appliancesRepoService.save(appliance);
        return new ApiResponseDTO<String>("Appliance status updated successfully", Boolean.TRUE, appliance.getActualDeviceStatus());
    }

    public ApiResponseDTO<String> setApplianceWebStatus(Long applianceId, String applianceWebStatus) {
        Appliance appliance = appliancesRepoService.getOne(applianceId);
        appliance.setWebStatus(applianceWebStatus);
        appliance = appliancesRepoService.save(appliance);
        return new ApiResponseDTO<String>("Appliance Web status updated successfully", Boolean.TRUE, appliance.getWebStatus());
    }
}
