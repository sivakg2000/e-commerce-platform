package com.sivakg200.ecom.userservice.repositories;

import com.sivakg200.ecom.userservice.entities.Role;
import com.sivakg200.ecom.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

}
