package com.cp26.soft3888_m11_04_group4.service;

import com.cp26.soft3888_m11_04_group4.pojo.User;

import java.util.List;

/**
 * @author xiaonan
 */
public interface UserService {

    User queryById(int id);

    /**
     * @author Shuhan Yang
     */
    int doRegister(User user);

    User doLogin(User user);

    List<User> findAllUsers();

    int deleteUser(int id);

    int resetPassword(int id, String password);
}
