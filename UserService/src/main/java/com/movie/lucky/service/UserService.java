package com.movie.lucky.service;

import com.movie.lucky.entity.User;

import java.util.List;

public interface UserService {

    public User getUserProfile(String jwt);
    public List<User> getAllUsers();
}
