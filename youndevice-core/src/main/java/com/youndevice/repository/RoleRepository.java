package com.youndevice.repository;

import com.youndevice.domain.Role;
import com.youndevice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long> {

    Role findByAuthority(String authority);

    Long countByAuthority(String authority);
}
