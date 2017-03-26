package com.youndevice.rest;

import com.youndevice.domain.Role;
import com.youndevice.domain.User;
import com.youndevice.repository.RoleRepository;
import com.youndevice.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
public class YounDeviceRestAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(YounDeviceRestAppApplication.class, args);
    }

    @Bean
    public InitializingBean insertDefaultUsers() {
        return new InitializingBean() {
            @Autowired
            private UserRepository userRepository;

            @Autowired
            private RoleRepository roleRepository;

            @Override
            public void afterPropertiesSet() {
                addUser("admin", "admin");
                addUser("user", "user");
            }

            private void addUser(String emailId, String password) {
                User user = new User(emailId, new BCryptPasswordEncoder().encode(password));
                //user.setPassword(new BCryptPasswordEncoder().encode(password));
                HashSet<Role> roleSet = new HashSet<Role>();
                HashSet<User> users = new HashSet<User>();
                users.add(user);
                roleSet.add(emailId.equals("admin") ? new Role("ROLE_ADMIN", users) : new Role("ROLE_USER", users));
                roleRepository.save(roleSet);
                user.setRoles(roleSet);
                userRepository.save(user);
            }
        };
    }

}
