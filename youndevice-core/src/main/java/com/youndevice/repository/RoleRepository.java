package com.youndevice.repository;

import com.youndevice.domain.Role;
import com.youndevice.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends CrudRepository<Role, Long> {

    Role findByAuthority(String authority);
}
