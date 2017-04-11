package com.youndevice.services.repoServices;

import com.youndevice.domain.AuthenticationToken;
import com.youndevice.repository.AuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenRepoService extends BaseRepoService<AuthenticationToken, Long> {

    @Autowired
    AuthenticationTokenRepository authenticationTokenRepository;

    @Override
    JpaRepository<AuthenticationToken, Long> getRepository() {
        return authenticationTokenRepository;
    }

    public AuthenticationToken findByTokenValue(String tokenValue) {
        return authenticationTokenRepository.findByTokenValue(tokenValue);
    }
}
