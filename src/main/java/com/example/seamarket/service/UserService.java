package com.example.seamarket.service;

import com.example.seamarket.model.User;

public interface UserService {

    boolean register(String username, String rawPassword, String email);

    User login(String username, String rawPassword);

    //封装dao层用法
    User findById(int id);
    User findByUsername(String username);
    User findByEmail(String email);
}
