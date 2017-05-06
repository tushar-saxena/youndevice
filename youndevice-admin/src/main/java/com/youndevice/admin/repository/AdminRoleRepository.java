package com.youndevice.admin.repository;

import com.youndevice.admin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRoleRepository extends JpaRepository<Role, Integer>{
    Role findByRole(String role);

}