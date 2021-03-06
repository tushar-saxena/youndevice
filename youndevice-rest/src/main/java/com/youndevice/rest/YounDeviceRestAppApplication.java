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
                saveDefaultRole();
                addUser("admin", "admin");
                addUser("user", "user");
            }

            private void addUser(String emailId, String password) {
                User user = new User(emailId, new BCryptPasswordEncoder().encode(password));
                HashSet<Role> roleSet = new HashSet<Role>();
                roleSet.add(emailId.equals("admin") ? roleRepository.findByAuthority("ROLE_ADMIN") : roleRepository.findByAuthority("ROLE_USER"));
                user.setRoles(roleSet);
                userRepository.save(user);
            }

            private void saveDefaultRole() {
                Role role1 = new Role();
                role1.setAuthority("ROLE_ADMIN");
                roleRepository.save(role1);
                Role role2 = new Role();
                role2.setAuthority("ROLE_USER");
                roleRepository.save(role2);
            }
        };
    }

}
