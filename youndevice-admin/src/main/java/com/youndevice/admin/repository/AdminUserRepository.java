package com.youndevice.admin.repository;

import com.youndevice.admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
