package com.sivakg200.ecom.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class ServiceController {

    @GetMapping("/{name}")
    public ResponseEntity<String> greeting(@PathVariable String name){
        return new ResponseEntity<String>("Welcome "+name, HttpStatus.OK);

    }
}
