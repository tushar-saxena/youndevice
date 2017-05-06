package com.youndevice.admin.service;


import com.youndevice.admin.model.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}