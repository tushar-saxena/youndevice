package com.youndevice.repository;

import com.youndevice.domain.AuthenticationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationTokenRepository extends CrudRepository<AuthenticationToken, Long> {

    AuthenticationToken findByTokenValue(String tokenValue);
}
