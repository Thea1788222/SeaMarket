package com.example.seamarket.service;

import com.example.seamarket.dao.UserDao;
import com.example.seamarket.dao.UserDaoImpl;
import com.example.seamarket.model.User;
import com.example.seamarket.util.PasswordUtil;

import java.time.LocalDateTime;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public boolean register(String username, String rawPassword, String email) {
        try {
            //检查邮箱是否已被注册
            if (userDao.findByEmail(email) != null) {
                System.out.println("邮箱已被注册: " + email);
                return false;
            }

            // 加密密码
            String passwordHash = PasswordUtil.hash(rawPassword);

            User user = new User();
            user.setUsername(username);
            user.setPasswordHash(passwordHash);
            user.setEmail(email);
            user.setCreatedAt(LocalDateTime.now());

            return userDao.insert(user) > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public User login(String email, String rawPassword) {
        try {
            //通过邮箱查找用户
            User user = userDao.findByEmail(email);
            if (user == null) {
                return null; // 邮箱不存在
            }

            //比对密码
            String hashed = PasswordUtil.hash(rawPassword);
            if (hashed.equals(user.getPasswordHash())) {
                return user; // 登录成功
            }

            return null; // 密码错误

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findById(int id) {
        try {
            return userDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            return userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            return userDao.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
