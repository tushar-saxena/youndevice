package com.youndevice.rest.service.apiServices;

import com.youndevice.domain.Appliance;
import com.youndevice.dto.ResponseDTO;
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
    public ResponseDTO<List<Appliance>> findByUserId(Long userId) {
        Pageable pageable = new PageRequest(0, 10);
        List<Appliance> appliances = appliancesRepoService.findAllByUserId(userId, pageable);
        ResponseDTO<List<Appliance>> responseDTO = new ResponseDTO<List<Appliance>>();
        responseDTO.setData(appliances);
        responseDTO.setStatus(Boolean.TRUE);
        responseDTO.setMessage("Appliances Fetched Successfully");
        return responseDTO;
    }
}
