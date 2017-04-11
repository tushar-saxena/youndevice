package com.youndevice.repository;

import com.youndevice.domain.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findByTokenValue(String tokenValue);
}
