package com.youndevice.rest.service.apiServices;

import com.youndevice.domain.Device;
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
    List<Device> findByUserId(Long userId) {
        Pageable pageable = new PageRequest(0, 10);
        return deviceRepoService.findAllByUserId(userId, pageable);
    }
}
