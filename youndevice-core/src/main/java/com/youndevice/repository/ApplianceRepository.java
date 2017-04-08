package com.youndevice.repository;


import com.youndevice.domain.Appliance;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {

    List<Appliance> findAllByDevice_User_Id(Long userId, Pageable pageable);

}