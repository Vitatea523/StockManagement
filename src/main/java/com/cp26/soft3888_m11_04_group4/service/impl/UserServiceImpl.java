package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.UserMapper;
import com.cp26.soft3888_m11_04_group4.pojo.User;
import com.cp26.soft3888_m11_04_group4.service.UserService;
import com.cp26.soft3888_m11_04_group4.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author xiaonan
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }


    /**
     * The function is find the user's all information by user's id.
     * @param id
     * @return User
     */
    @Override
    public User queryById(int id) {
        return userMapper.queryById(id);
    }


    /**
     * The function is for managers to create a new account.
     * @param user
     * @return int(the result of running the relevant SQLs in the database)
     */
    @Override
    public int doRegister(User user) {
        //Encrypt the password.
        user.setPassword(MD5Util.md5(user.getPassword()));
        User exist = userMapper.queryByName(user.getName());
        if(exist == null){
            return userMapper.addUser(user);
        }
        return 0;
    }

    /**
     * The function is for users to login by name and password.
     * @param user
     * @return User
     */
    @Override
    public User doLogin(User user) {
        String password = MD5Util.md5(user.getPassword());
        User exist = userMapper.queryByNameAndPassword(user.getName(), password);
        return exist;
    }

    /**
     * The function is for managers to see all accounts.
     * @return List<User>(the list of all users)
     */
    @Override
    public List<User> findAllUsers() {
        return userMapper.queryAllUsers();
    }

    /**
     * The function is for managers to delete an account by its id.
     * @param id
     * @return int(the result of running the relevant SQLs in the database)
     */
    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUserById(id);
    }

//    /**
//     * The function is for managers to delete an account by its id.
//     * @param name
//     * @return
//     */
//    @Override
//    public User getProfile(String name) {
//        return userMapper.queryByName(name);
//    }

    /**
     * The function is for users to reset her/his password by id and password.
     * @param id
     * @param password
     * @return int(the result of running the relevant SQLs in the database)
     */
    @Override
    public int resetPassword(int id, String password) {
        return userMapper.updateUserPasswordById(id, MD5Util.md5(password));
    }


}
