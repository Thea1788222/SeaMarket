package com.example.seamarket.dao;

import com.example.seamarket.model.User;
import java.sql.SQLException;

public interface UserDao {

    int insert(User user) throws SQLException;           // 插入用户
    int update(User user) throws SQLException;           // 更新用户信息
    int deleteById(int id) throws SQLException;          // 根据 ID 删除用户
    User findById(int id) throws SQLException;           // 根据 ID 查询用户
    User findByUsername(String username) throws SQLException; // 根据用户名查询用户
}
