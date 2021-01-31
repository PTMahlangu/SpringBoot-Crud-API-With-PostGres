package com.example.SpingBootJPA.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getStudent(){

        return userService.getUser();
    }

    @PostMapping
    public void registerUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public  void deleteUser(@PathVariable("userId") Long id){
        userService.deleteUser(id);
    }
    @PutMapping(path ="{userId}" )
    public void updateUser(@PathVariable("userId") Long userId ,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email
                           ){
        userService.updateUser(userId,name,email);
    }
}
