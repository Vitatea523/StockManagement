package com.cp26.soft3888_m11_04_group4.dao;

import com.cp26.soft3888_m11_04_group4.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaonan
 */
@Mapper
public interface UserMapper {

    /**
     * @param user
     * @return int(the result of running the relevant SQLs in the database)
     */
    @Transactional
    int addUser(User user);

    /**
     * @param name
     * @return User
     */
    @Transactional
    User queryByName(String name);

    /**
     * @param id
     * @return User
     */
    @Transactional
    User queryById(int id);

    /**
     * @param name
     * @param password
     * @return User
     */
    @Transactional
    User queryByNameAndPassword(String name, String password);

    /**
     * @return List<User>(the list of all users)
     */
    @Transactional
    List<User> queryAllUsers();

    /**
     * @param id
     * @return int(the result of running the relevant SQLs in the database)
     */
    @Transactional
    int deleteUserById(int id);

    /**
     * @param id
     * @param password
     * @return int(the result of running the relevant SQLs in the database)
     */
    @Transactional
    int updateUserPasswordById(int id, String password);

    /**
     * Reset the AUTO_INCREMENT primary key starting from 1.
     * @return int(the result of running the relevant SQLs in the database)
     */
    int restartIndex();
}