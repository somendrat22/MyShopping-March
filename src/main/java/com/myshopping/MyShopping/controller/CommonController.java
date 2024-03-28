package com.myshopping.MyShopping.controller;

import com.myshopping.MyShopping.models.AppUser;
import com.myshopping.MyShopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // If any request is staring with /api
// all those request are going to come at the common controller
public class CommonController {
    // 9:55 -> Break
    @Autowired
    UserService userService;

    @PostMapping("/user/register")
    public String registerUser(@RequestBody AppUser appUser){
        // Save User -> We need to write some kind of logic
        // So to write logic we require service layer where we can write logic
        userService.registerUser(appUser);
        return "User got successfully saved";
    }
}
