package com.cp26.soft3888_m11_04_group4.controller;

import com.cp26.soft3888_m11_04_group4.pojo.User;
import com.cp26.soft3888_m11_04_group4.service.UserService;
import com.cp26.soft3888_m11_04_group4.vo.JsonResult;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Shuhan Yang
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * The function is to capture the URL—/user/createAccount for managers to create a new account.
     * @param user
     * @return JosonResult(a json object for the front-end)
     */
    @PostMapping("/createAccount")
    public JsonResult doRegister(@RequestBody User user){
        try{
            int result = userService.doRegister(user);
            System.out.println("result"+result);
            if(result == 0){
                return JsonResult.build(400, "failed: the account name already exists, please enter another one.", null);
            }else{
                return JsonResult.build(20000, "success: user account created!", null);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return JsonResult.build(400, "failed: the account already exists, please try log in.", null);
        }
    }


    /**
     * The function is to capture the URL—/user/login for users to login.
     * @param user
     * @return JosonResult(a json object for the front-end)
     */
    @PostMapping("/login")
    public JsonResult doLogin(@RequestBody User user) {
        try{
            User result = userService.doLogin(user);
            if(result == null){
                return JsonResult.build(400, "failed: the username or password is wrong, please try again.", null);
            }
            if("manager".equals(result.getTitle())){
                JSONObject obj = new JSONObject();
                obj.put("token", result.getId().toString());
                return JsonResult.build(20000, "success: user logged in successfully!", obj);
            }else if("cashier".equals(result.getTitle())){
                JSONObject obj = new JSONObject();
                obj.put("token", result.getId().toString());
                return JsonResult.build(20000, "success: user logged in successfully!", obj);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return JsonResult.build(400, "failed: the username or password is wrong, please try again.", null);
        }
        return JsonResult.build(400, "failed: the username or password is wrong, please try again.", null);
    }

    /**
     * The function is to capture the URL—/user/info for maintain logged-in status.
     * @param token
     * @return JosonResult(a json object for the front-end)
     */
    @GetMapping("/info")
    public JsonResult getInfo(@RequestParam String token) {
        Integer id = Integer.parseInt(token);
        User user = userService.queryById(id);
        return JsonResult.build(20000, "Success", user);
    }

    /**
     * The function is to capture the URL—/user/getAccounts for managers to see all accounts.
     * @return JsonResult<List<User>>(the list of all users)
     */
    @GetMapping("/getAccounts")
    public JsonResult<List<User>> findAllUsers(){
        System.out.println("getAccounts");
        List<User> result = userService.findAllUsers();
        return JsonResult.build(20000, "success", result);
    }

    /**
     * The function is to capture the URL—/user/logout for users to log out.
     * @return JosonResult(a json object for the front-end)
     */
    @PostMapping("/logout")
    public JsonResult logOut(){
        return JsonResult.build(20000, "success", null);
    }

    /**
     * The function is to capture the URL—/user/deleteAccount for managers to delete an account by its id.
     * @param id
     * @return JosonResult(a json object for the front-end)
     */
    @DeleteMapping("/deleteAccount")
    public JsonResult deleteUserById(@RequestParam int id){
        try{
            int result = userService.deleteUser(id);
            if(result == 0){
                return JsonResult.build(400, "failed: Error, please try again.", null);
            }else{
                return JsonResult.build(20000, "success: user deleted!", null);
            }
        }catch (Exception e){
            return JsonResult.build(400, "failed: Error, please try again.", null);
        }

    }

    /**
     * The function is to capture the URL—/user/resetPassword for users to reset her/his password by id and password.
     * @param id
     * @param password
     * @return JosonResult(a json object for the front-end)
     */
    @PostMapping("/resetPassword")
    public JsonResult resetPassword(@RequestParam int id, @RequestParam String password){
        try{
            int result = userService.resetPassword(id, password);
            if(result == 1){
                return JsonResult.build(20000, "success: Password Updated!", null);
            }else{
                return JsonResult.build(400, "failed: Error, please try again.", null);
            }
        }catch (Exception e){
            return JsonResult.build(400, "failed: Error, please try again.", null);
        }

    }


}
