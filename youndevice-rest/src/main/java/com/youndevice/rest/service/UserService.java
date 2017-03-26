package com.youndevice.rest.service;

import com.youndevice.domain.Role;
import com.youndevice.domain.User;
import com.youndevice.repository.RoleRepository;
import com.youndevice.repository.UserRepository;
import com.youndevice.rest.dto.ApiResponseDTO;
import com.youndevice.rest.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public ApiResponseDTO<User> saveUser(UserDTO userDTO) {

        User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmailId(), new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.findByAuthority("ROLE_USER"));
        user.setRoles(roleSet);
        userRepository.save(user);
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        apiResponseDTO.setMessage("User Saved Successfully");
        return apiResponseDTO;
    }

}
