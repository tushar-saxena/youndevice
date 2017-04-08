package com.youndevice.services.repoServices;

import com.youndevice.domain.Appliance;
import com.youndevice.repository.ApplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppliancesRepoService extends BaseRepoService<Appliance, Long> {
    @Autowired
    ApplianceRepository applianceRepository;

    @Override
    JpaRepository<Appliance, Long> getRepository() {
        return applianceRepository;
    }

    //TODO Note:- Before adding any method here, please check if that method is present in its super class or not.
    // NOTE:- If you think that method may be required by multiple RepoServices,
    // NOTE:-  then add that method to the super class instead of here for better re-usability

    public List<Appliance> findAllByUserId(Long userId, Pageable pageable) {
        return applianceRepository.findAllByDevice_User_Id(userId, pageable);
    }
}
