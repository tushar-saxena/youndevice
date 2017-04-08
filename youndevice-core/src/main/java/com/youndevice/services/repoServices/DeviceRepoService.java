package com.youndevice.services.repoServices;

import com.youndevice.domain.Device;
import com.youndevice.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceRepoService extends BaseRepoService<Device, Long> {
    @Autowired
    DeviceRepository deviceRepository;

    @Override
    JpaRepository<Device, Long> getRepository() {
        return deviceRepository;
    }

    //TODO Note:- Before adding any method here, please check if that method is present in its super class or not.
    // NOTE:- If you think that method may be required by multiple RepoServices,
    // NOTE:-  then add that method to the super class instead of here for better re-usability

    public List<Device> findAllByUserId(Long userId, Pageable pageable) {
        return deviceRepository.findAllByUser_Id(userId, pageable);
    }
}
