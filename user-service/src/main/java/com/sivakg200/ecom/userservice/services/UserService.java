package com.sivakg200.ecom.userservice.services;

import com.sivakg200.ecom.userservice.entities.Role;
import com.sivakg200.ecom.userservice.entities.User;
import com.sivakg200.ecom.userservice.entities.UserRoles;
import com.sivakg200.ecom.userservice.repositories.RoleRepository;
import com.sivakg200.ecom.userservice.repositories.UserRepository;
import com.sivakg200.ecom.userservice.repositories.UserRoleRepository; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

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

    public List<UserRoles> getRolesByUserId(int id){
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent() && user.get().getId()==id){
            return userRoleRepository.findByUser(user.get()).orElseGet(List::of);
        }else{
            return null;
        }
    }


    public UserRoles getRolesById(int id,int rid){
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent() && user.get().getId()==id){

            List<UserRoles> userRoles=userRoleRepository.findByUser(user.get()).orElseGet(List::of);
            return userRoles.stream().filter(u->u.getRole().getId()==rid).findFirst().orElse(null);
        }else{
            return null;
        }
    }


    public List<UserRoles> addRolesById(int id,int rid){
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent() && user.get().getId()==id){
            List<UserRoles> userRoles=userRoleRepository.findByUser(user.get()).orElseGet(List::of);
            Optional<UserRoles> userRole=userRoles.stream().filter(u->u.getRole().getId()==rid).findFirst();
            Role newRole=roleRepository.findById(rid).orElse(null);
            if(userRole.isEmpty() && newRole!=null){
                UserRoles newUserRole=new UserRoles();
                newUserRole.setUser(user.get());
                newUserRole.setRole(newRole);
                userRoleRepository.save(newUserRole);
            }
            return userRoleRepository.findByUser(user.get()).orElseGet(List::of);
        }else{
            return null;
        }
    }


    public List<UserRoles> deleteRolesById(int id,int rid){
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent() && user.get().getId()==id){
            List<UserRoles> userRoles=userRoleRepository.findByUser(user.get()).orElseGet(List::of);
            Optional<UserRoles> userRole=userRoles.stream().filter(u->u.getRole().getId()==rid).findFirst();
            if(userRole.isPresent()){
                userRoleRepository.deleteById(rid);
            }
            return userRoleRepository.findByUser(user.get()).orElseGet(List::of);
        }else{
            return null;
        }
    }

}
