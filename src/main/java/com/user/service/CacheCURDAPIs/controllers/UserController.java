package com.user.service.CacheCURDAPIs.controllers;

import com.user.service.CacheCURDAPIs.entities.User;
import com.user.service.CacheCURDAPIs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ResponseEntity saveUser(@RequestBody User user) {
        String response = userService.saveAndValidate(user);
        if(response.equalsIgnoreCase("Success")) {
            return ResponseEntity.ok("User Added Successfully!!!");
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping(value = "/email")
    public ResponseEntity getUser(@RequestHeader(value = "email") String email) {
        User user = userService.getDataForEmail(email);
        if(user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Data Found!!!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestHeader(value = "email") String email) {
        boolean response = userService.removeKeyData(email);
        if(response) {
            return ResponseEntity.ok("User Deleted!!!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Data Found!!!");
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody User user) {
        String response = userService.validateAndUpdate(user);
        if(response.equalsIgnoreCase("Success")) {
            return ResponseEntity.ok("User updated Successfully!!!");
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
