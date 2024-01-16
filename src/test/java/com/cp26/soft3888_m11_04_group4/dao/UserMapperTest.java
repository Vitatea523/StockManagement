package com.cp26.soft3888_m11_04_group4.dao;

import com.cp26.soft3888_m11_04_group4.pojo.*;
import com.cp26.soft3888_m11_04_group4.utils.MD5Util;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private static User manager1;
    private static User manager2;
    private static User cashier1;
    private static User cashier2;

    @BeforeClass
    public static void prepare(){
        manager1 = new User(1, "Jessica", MD5Util.md5("123456"), "manager");
        manager2 = new User(2, "Eric", MD5Util.md5("123456"), "manager");

        cashier1 = new User(3, "Justin", MD5Util.md5("123456"), "cashier");
        cashier2 = new User(4, "Celine", MD5Util.md5("123456"), "cashier");
    }

    private void setup(){
        userMapper.addUser(manager1);
        userMapper.addUser(manager2);
        userMapper.addUser(cashier1);
        userMapper.addUser(cashier2);
    }

    private void destroy(){
        userMapper.deleteUserById(manager1.getId());
        userMapper.deleteUserById(manager2.getId());
        userMapper.deleteUserById(cashier1.getId());
        userMapper.deleteUserById(cashier2.getId());
        userMapper.restartIndex();
    }


    @Test
    public void queryByNameForManagerTest() {
        setup();

        User mangerJessica = userMapper.queryByName("Jessica");

        assertNotNull(mangerJessica);

        assertTrue(mangerJessica instanceof User);
        assertTrue(mangerJessica.getName().equals("Jessica"));
        assertTrue(mangerJessica.getPassword().equals(MD5Util.md5("123456")));
        assertTrue(mangerJessica.getTitle().equals("manager"));

        User mangerEric = userMapper.queryByName("Eric");

        assertNotNull(mangerEric);

        assertTrue(mangerEric instanceof User);
        assertTrue(mangerEric.getName().equals("Eric"));
        assertTrue(mangerEric.getPassword().equals(MD5Util.md5("123456")));
        assertTrue(mangerEric.getTitle().equals("manager"));

        destroy();
    }

    @Test
    public void queryByNameForCashierTest() {
        setup();

        User cashierJustin = userMapper.queryByName("Justin");

        assertNotNull(cashierJustin);

        assertTrue(cashierJustin instanceof User);
        assertTrue(cashierJustin.getName().equals("Justin"));
        assertTrue(cashierJustin.getPassword().equals(MD5Util.md5("123456")));
        assertTrue(cashierJustin.getTitle().equals("cashier"));

        User cashierCeline = userMapper.queryByName("Celine");

        assertNotNull(cashierCeline);

        assertTrue(cashierCeline instanceof User);
        assertTrue(cashierCeline.getName().equals("Celine"));
        assertTrue(cashierCeline.getPassword().equals(MD5Util.md5("123456")));
        assertTrue(cashierCeline.getTitle().equals("cashier"));

        destroy();

    }

    @Test
    public void queryByIdForManagerTest() {
        setup();

        User mangerJessica = userMapper.queryById(1);

        assertNotNull(mangerJessica);

        assertTrue(mangerJessica instanceof User);
        assertTrue(mangerJessica.getName().equals("Jessica"));
        assertTrue(mangerJessica.getPassword().equals(MD5Util.md5("123456")));
        assertTrue(mangerJessica.getTitle().equals("manager"));

        User mangerEric = userMapper.queryById(2);

        assertNotNull(mangerEric);

        assertTrue(mangerEric instanceof User);
        assertTrue(mangerEric.getName().equals("Eric"));
        assertTrue(mangerEric.getPassword().equals(MD5Util.md5("123456")));
        assertTrue(mangerEric.getTitle().equals("manager"));


        destroy();
    }

    @Test
    public void queryByIdForCashierTest() {
        setup();

        User cashierJustin = userMapper.queryById(3);

        assertNotNull(cashierJustin);

        assertTrue(cashierJustin instanceof User);
        assertTrue(cashierJustin.getName().equals("Justin"));
        assertTrue(cashierJustin.getPassword().equals(MD5Util.md5("123456")));
        assertTrue(cashierJustin.getTitle().equals("cashier"));

        User cashierCeline = userMapper.queryById(4);

        assertNotNull(cashierCeline);

        assertTrue(cashierCeline instanceof User);
        assertTrue(cashierCeline.getName().equals("Celine"));
        assertTrue(cashierCeline.getPassword().equals(MD5Util.md5("123456")));
        assertTrue(cashierCeline.getTitle().equals("cashier"));

        destroy();
    }

    @Test
    public void queryByNameAndPasswordForManagerTest() {
        setup();

        User mangerJessica = userMapper.queryByNameAndPassword("Jessica", MD5Util.md5("123456"));

        assertNotNull(mangerJessica);

        assertTrue(mangerJessica instanceof User);
        assertTrue(mangerJessica.getName().equals("Jessica"));
        assertTrue(mangerJessica.getPassword().equals(MD5Util.md5("123456")));
        assertTrue(mangerJessica.getTitle().equals("manager"));

        User mangerEric = userMapper.queryByNameAndPassword("Eric", MD5Util.md5("123456"));

        assertNotNull(mangerEric);

        assertTrue(mangerEric instanceof User);
        assertTrue(mangerEric.getName().equals("Eric"));
        assertTrue(mangerEric.getPassword().equals(MD5Util.md5("123456")));
        assertTrue(mangerEric.getTitle().equals("manager"));

        destroy();
    }

    @Test
    public void queryByNameAndPasswordForCashierTest() {
        setup();

        User cashierJustin = userMapper.queryByNameAndPassword("Justin", MD5Util.md5("123456"));

        assertNotNull(cashierJustin);

        assertTrue(cashierJustin instanceof User);
        assertTrue(cashierJustin.getName().equals("Justin"));
        assertTrue(cashierJustin.getPassword().equals(MD5Util.md5("123456")));
        assertTrue(cashierJustin.getTitle().equals("cashier"));

        User cashierCeline = userMapper.queryByNameAndPassword("Celine", MD5Util.md5("123456"));

        assertNotNull(cashierCeline);

        assertTrue(cashierCeline instanceof User);
        assertTrue(cashierCeline.getName().equals("Celine"));
        assertTrue(cashierCeline.getPassword().equals(MD5Util.md5("123456")));
        assertTrue(cashierCeline.getTitle().equals("cashier"));

        destroy();
    }

    @Test
    public void queryAllUsers() {
        setup();

        List<User> allUsers = userMapper.queryAllUsers();

        assertNotNull(allUsers);

        assertTrue(allUsers.size() == 4); //2 extra users in the cloud database are the existing users Tom—Manager and Lisa—Cashier for demonstration or first-time login.

        assertTrue(allUsers.get(0).getName().equals("Jessica"));
        assertTrue(allUsers.get(0).getPassword().equals(MD5Util.md5("123456")));
        assertTrue(allUsers.get(0).getTitle().equals("manager"));

        assertTrue(allUsers.get(1).getName().equals("Eric"));
        assertTrue(allUsers.get(1).getPassword().equals(MD5Util.md5("123456")));
        assertTrue(allUsers.get(1).getTitle().equals("manager"));

        assertTrue(allUsers.get(2).getName().equals("Justin"));
        assertTrue(allUsers.get(2).getPassword().equals(MD5Util.md5("123456")));
        assertTrue(allUsers.get(2).getTitle().equals("cashier"));

        assertTrue(allUsers.get(3).getName().equals("Celine"));
        assertTrue(allUsers.get(3).getPassword().equals(MD5Util.md5("123456")));
        assertTrue(allUsers.get(3).getTitle().equals("cashier"));

        destroy();
    }

    @Test
    public void updateUserPasswordByIdForManagerTest() {
        setup();

        int resultForJessica = userMapper.updateUserPasswordById(1, MD5Util.md5("123456ABC"));

        assertNotNull(resultForJessica);

        assertTrue(resultForJessica == 1);

        User mangerJessica = userMapper.queryByName("Jessica");

        assertNotNull(mangerJessica);

        assertTrue(mangerJessica instanceof User);
        assertTrue(mangerJessica.getName().equals("Jessica"));
        assertTrue(mangerJessica.getPassword().equals(MD5Util.md5("123456ABC")));
        assertTrue(mangerJessica.getTitle().equals("manager"));


        int resultForJustin = userMapper.updateUserPasswordById(2, MD5Util.md5("123456ABC"));

        assertNotNull(resultForJustin);
        assertTrue(resultForJustin == 1);

        User mangerEric = userMapper.queryByName("Eric");

        assertNotNull(mangerEric);

        assertTrue(mangerEric instanceof User);
        assertTrue(mangerEric.getName().equals("Eric"));
        assertTrue(mangerEric.getPassword().equals(MD5Util.md5("123456ABC")));
        assertTrue(mangerEric.getTitle().equals("manager"));

        destroy();
    }

    @Test
    public void updateUserPasswordByIdForCashierTest() {
        setup();

        int resultForJustin = userMapper.updateUserPasswordById(3, MD5Util.md5("123456ABC"));

        assertNotNull(resultForJustin);

        assertTrue(resultForJustin == 1);

        User mangerJustin = userMapper.queryByName("Justin");

        assertNotNull(mangerJustin);

        assertTrue(mangerJustin instanceof User);
        assertTrue(mangerJustin.getName().equals("Justin"));
        assertTrue(mangerJustin.getPassword().equals(MD5Util.md5("123456ABC")));
        assertTrue(mangerJustin.getTitle().equals("cashier"));


        int resultForCeline = userMapper.updateUserPasswordById(4, MD5Util.md5("123456ABC"));

        assertNotNull(resultForCeline);
        assertTrue(resultForCeline == 1);

        User mangerCeline = userMapper.queryByName("Celine");

        assertNotNull(mangerCeline);

        assertTrue(mangerCeline instanceof User);
        assertTrue(mangerCeline.getName().equals("Celine"));
        assertTrue(mangerCeline.getPassword().equals(MD5Util.md5("123456ABC")));
        assertTrue(mangerCeline.getTitle().equals("cashier"));

        destroy();
    }
}