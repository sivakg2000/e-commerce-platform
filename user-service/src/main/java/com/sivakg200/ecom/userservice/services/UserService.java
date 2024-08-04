package com.sivakg200.ecom.userservice.services;

import com.sivakg200.ecom.userservice.entities.User;
import com.sivakg200.ecom.userservice.repositories.UserRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Server
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(User newUser){
       return userRepository.save(newUser);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }


    public User getById(int id){
        Optional<User> user=userRepository.findById(id);
        return user.orElse(null);
    }

    public  User getByEmail(String email){
        Optional<User> user=userRepository.findByEmail(email);
        return user.orElse(null);
    }

    public User update(int id,User updateUser){
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent() && user.get().getId()==id){
            userRepository.save(updateUser);
            return updateUser;
        }else{
            return null;
        }
    }

    public void deleteById(int id){
        userRepository.deleteById(id);
    }
}
