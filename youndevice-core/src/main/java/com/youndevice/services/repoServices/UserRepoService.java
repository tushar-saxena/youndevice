package com.youndevice.services.repoServices;

import com.youndevice.domain.User;
import com.youndevice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRepoService extends BaseRepoService<User, Long> {
    @Autowired
    UserRepository userRepository;

    @Override
    JpaRepository<User, Long> getRepository() {
        return userRepository;
    }
//TODO Note:- Before adding any method here, please check if that method is present in its super class or not.
    // NOTE:- If you think that method may be required by multiple RepoServices,
    // NOTE:-  then add that method to the super class instead of here for better re-usability

    public User findUserByEmailId(String emailId) {
        return userRepository.findByEmailId(emailId);
    }

    public Long countUserByEmailId(String emailId) {
        return userRepository.countByEmailId(emailId);
    }
}
