package com.sivakg200.ecom.userservice.controller;

import com.sivakg200.ecom.userservice.entities.Role;
import com.sivakg200.ecom.userservice.entities.User;
import com.sivakg200.ecom.userservice.services.RoleService;
import com.sivakg200.ecom.userservice.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public List<Role> getAll(){
        return roleService.getAll();

    }

    @GetMapping("/{id}")
    public Role getById(@PathVariable int id){
        return roleService.getById(id);

    }



    @PostMapping("/")
    public ResponseEntity<Role> create(@RequestBody @Valid Role newRole){
            return new ResponseEntity<>( roleService.create(newRole),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateById(@PathVariable int id, @RequestBody @Valid Role updateRole){
            return new ResponseEntity<>( roleService.update(id,updateRole),HttpStatus.CREATED);
    }
}
