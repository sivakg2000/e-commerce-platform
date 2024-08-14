package com.sivakg200.ecom.userservice.repositories;

import com.sivakg200.ecom.userservice.entities.Role;
import com.sivakg200.ecom.userservice.entities.User;
import com.sivakg200.ecom.userservice.entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles,Integer> {
    Optional<List<UserRoles>> findByUser(User user);
}
