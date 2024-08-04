package com.sivakg200.ecom.userservice.services;

import com.sivakg200.ecom.userservice.entities.Role;
import com.sivakg200.ecom.userservice.entities.User;
import com.sivakg200.ecom.userservice.repositories.RoleRepository;
import com.sivakg200.ecom.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role create(Role newRole){
       return roleRepository.save(newRole);
    }

    public List<Role> getAll(){
        return roleRepository.findAll();
    }


    public Role getById(int id){
        Optional<Role> user=roleRepository.findById(id);
        return user.orElse(null);
    }



    public Role update(int id,Role updateRole){
        Optional<Role> role=roleRepository.findById(id);
        if(role.isPresent() && role.get().getId()==id){
            return roleRepository.save(updateRole);

        }else{
            return null;
        }
    }

    public void deleteById(int id){
        roleRepository.deleteById(id);
    }
}
