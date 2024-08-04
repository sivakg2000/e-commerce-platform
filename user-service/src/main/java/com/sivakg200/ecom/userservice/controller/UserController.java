package com.sivakg200.ecom.userservice.controller;

import com.sivakg200.ecom.userservice.entities.User;
import com.sivakg200.ecom.userservice.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getAll(){
        return userService.getAll();

    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id){
        return userService.getById(id);

    }

    @GetMapping("by-email/{email}")
    public User getByEmail(@PathVariable String email){
        return userService.getByEmail(email);

    }

    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody @Valid User user){
        if(userService.getByEmail(user.getEmail())==null){
            userService.create(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }else{
            throw new DuplicateKeyException("Email Already Exist.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateById(@PathVariable int id, @RequestBody @Valid User user){
        if(userService.getByEmail(user.getEmail())==null){
            userService.update(id,user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }else{
            throw new DuplicateKeyException("Email Already Exist.");
        }
    }
}
