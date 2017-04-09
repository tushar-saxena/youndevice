package com.youndevice.rest.service.apiServices;

import com.youndevice.domain.Appliance;
import com.youndevice.dto.ResponseDTO;
import com.youndevice.rest.dto.ApiResponseDTO;
import com.youndevice.services.repoServices.AppliancesRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplianceApiService {

    @Autowired
    private AppliancesRepoService appliancesRepoService;

    //TODO Instead of returning Domain object, return DTO
    public ResponseDTO<List<Appliance>> findByUserId(Long userId, Integer max, Integer offset) {
        Pageable pageable = new PageRequest(0, max);
        List<Appliance> appliances = appliancesRepoService.findAllByUserId(userId, pageable);
        ResponseDTO<List<Appliance>> responseDTO = new ResponseDTO<List<Appliance>>();
        responseDTO.setData(appliances);
        responseDTO.setStatus(Boolean.TRUE);
        responseDTO.setMessage("Appliances Fetched Successfully");
        return responseDTO;
    }

    public ApiResponseDTO<String> getApplianceStatus(Long applianceId) {
        Appliance appliance = appliancesRepoService.getOne(applianceId);
        return new ApiResponseDTO<String>("Appliance status fetched successfully", Boolean.TRUE, appliance.getStatus());
    }

    public ApiResponseDTO<String> setApplianceStatus(Long applianceId, String applianceStatus) {
        Appliance appliance = appliancesRepoService.getOne(applianceId);
        appliance.setStatus(applianceStatus);
        appliance = appliancesRepoService.save(appliance);
        return new ApiResponseDTO<String>("Appliance status updated successfully", Boolean.TRUE, appliance.getStatus());
    }
}
