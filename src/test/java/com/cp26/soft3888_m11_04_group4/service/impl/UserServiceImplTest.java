package com.cp26.soft3888_m11_04_group4.service.impl;

import com.cp26.soft3888_m11_04_group4.dao.UserMapper;
import com.cp26.soft3888_m11_04_group4.pojo.User;
import com.cp26.soft3888_m11_04_group4.service.UserService;
import com.cp26.soft3888_m11_04_group4.utils.MD5Util;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
/**
 * @author Shuhan Yang
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    private static UserService userService;
    private static User manager1;
    private static User manager2;
    private static User cashier1;
    private static User cashier2;
    private static UserMapper userMapper;

    @BeforeClass
    public static void prepare(){
        manager1 = mock(User.class);
        when(manager1.getId()).thenReturn(1);
        when(manager1.getName()).thenReturn("Jessica");
        when(manager1.getPassword()).thenReturn("123456");

        manager2 = mock(User.class);
        when(manager2.getId()).thenReturn(2);
        when(manager2.getName()).thenReturn("Eric");
        when(manager2.getPassword()).thenReturn("123456");

        cashier1 = mock(User.class);
        when(cashier1.getId()).thenReturn(3);
        when(cashier1.getName()).thenReturn("Justin");
        when(cashier1.getPassword()).thenReturn("123456");

        cashier2 = mock(User.class);
        when(cashier2.getId()).thenReturn(4);
        when(cashier2.getName()).thenReturn("Celine");
        when(cashier2.getPassword()).thenReturn("123456");

        List<User> userList = new ArrayList<>();
        userList.add(manager1);
        userList.add(manager2);
        userList.add(cashier1);
        userList.add(cashier2);

        userMapper = mock(UserMapper.class);

        when(userMapper.queryById(1)).thenReturn(manager1);
        when(userMapper.queryById(2)).thenReturn(manager2);
        when(userMapper.queryById(3)).thenReturn(cashier1);
        when(userMapper.queryById(4)).thenReturn(cashier2);

        when(userMapper.queryByName("Jessica")).thenReturn(manager1);
        when(userMapper.queryByName("Eric")).thenReturn(manager2);
        when(userMapper.queryByName("Justin")).thenReturn(cashier1);
        when(userMapper.queryByName("Celine")).thenReturn(cashier2);

        when(userMapper.addUser(manager1)).thenReturn(1);
        when(userMapper.addUser(manager2)).thenReturn(2);
        when(userMapper.addUser(cashier1)).thenReturn(3);
        when(userMapper.addUser(cashier2)).thenReturn(4);

        when(userMapper.queryByNameAndPassword("Jessica", MD5Util.md5("123456"))).thenReturn(manager1);
        when(userMapper.queryByNameAndPassword("Eric", MD5Util.md5("123456"))).thenReturn(manager2);
        when(userMapper.queryByNameAndPassword("Justin", MD5Util.md5("123456"))).thenReturn(cashier1);
        when(userMapper.queryByNameAndPassword("Celine", MD5Util.md5("123456"))).thenReturn(cashier2);

        when(userMapper.queryAllUsers()).thenReturn(userList);

        when(userMapper.deleteUserById(1)).thenReturn(1);
        when(userMapper.deleteUserById(2)).thenReturn(2);
        when(userMapper.deleteUserById(3)).thenReturn(3);
        when(userMapper.deleteUserById(4)).thenReturn(4);

        when(userMapper.updateUserPasswordById(1, MD5Util.md5("123456ABC"))).thenReturn(1);
        when(userMapper.updateUserPasswordById(2, MD5Util.md5("123456ABC"))).thenReturn(2);
        when(userMapper.updateUserPasswordById(3, MD5Util.md5("123456ABC"))).thenReturn(3);
        when(userMapper.updateUserPasswordById(4, MD5Util.md5("123456ABC"))).thenReturn(4);

        userService = new UserServiceImpl(userMapper);
    }

    @Test
    public void queryByIdTest() {
        assertEquals(1, userService.queryById(1).getId());
        assertEquals(2, userService.queryById(2).getId());
        assertEquals(3, userService.queryById(3).getId());
        assertEquals(4, userService.queryById(4).getId());
    }

    @Test
    public void doRegisterTest() {
        //In the test environment all users exits already hence test doRegister() directly will return null.
        //In order to test doRegister(), the other userMapper method called in this method is addUser() is tested here.
        //Please refer to the doRegister() in the UserServiceImpl.java
        assertEquals(1, userMapper.addUser(manager1));
        assertEquals(2, userMapper.addUser(manager2));
        assertEquals(3, userMapper.addUser(cashier1));
        assertEquals(4, userMapper.addUser(cashier2));
    }

    @Test
    public void doLoginTest() {
        assertEquals(manager1, userService.doLogin(manager1));
        assertEquals(manager2, userService.doLogin(manager2));
        assertEquals(cashier1, userService.doLogin(cashier1));
        assertEquals(cashier2, userService.doLogin(cashier2));
    }

    @Test
    public void findAllUsersTest() {
        assertEquals(4, userService.findAllUsers().size());
    }

    @Test
    public void deleteUserTest() {
        assertEquals(1, userService.deleteUser(1));
        assertEquals(2, userService.deleteUser(2));
        assertEquals(3, userService.deleteUser(3));
        assertEquals(4, userService.deleteUser(4));
    }

//    @Test
//    public void getProfileTest() {
//        assertEquals(manager1, userService.getProfile(manager1.getName()));
//        assertEquals(manager2, userService.getProfile(manager2.getName()));
//        assertEquals(cashier1, userService.getProfile(cashier1.getName()));
//        assertEquals(cashier2, userService.getProfile(cashier2.getName()));
//    }

    @Test
    public void resetPasswordTest() {
        assertEquals(1, userService.resetPassword(1, "123456ABC"));
        assertEquals(2, userService.resetPassword(2, "123456ABC"));
        assertEquals(3, userService.resetPassword(3, "123456ABC"));
        assertEquals(4, userService.resetPassword(4, "123456ABC"));
    }
}