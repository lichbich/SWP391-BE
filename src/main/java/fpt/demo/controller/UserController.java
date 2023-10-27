package fpt.demo.controller;


import fpt.demo.model.User;
import fpt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired

    @RequestMapping("/hello")
    public String hello_world() {
        return "Hello World";
    }

    //add User
    @PostMapping("/add")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "User Added Successfully!!";
    }

    //getUserById
    @RequestMapping("User/{accountId}")
    public User getUserById(@PathVariable("accountId") long accountId ){
        return userService.getUserById(accountId);
    }

        // getListUser
    @RequestMapping("/User")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    //updateUser
    @PutMapping("/User")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    //deleteUser
    @DeleteMapping("User/{accountId}")
    public String deleteUser(@PathVariable("accountId") long accountId){
        userService.deleteUser(accountId);
        return "User Deleted";
    }


}
