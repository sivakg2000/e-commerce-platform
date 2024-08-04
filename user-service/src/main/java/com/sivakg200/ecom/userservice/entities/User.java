package com.sivakg200.ecom.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="user")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "First name is required")
    private String firstname;

    @NotEmpty(message = "Last name is required")
    private String lastname;

    @Email
    @NotEmpty(message = "email is required")
    @Column(nullable = false,unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "Password is required")
    private String password;


    @CreationTimestamp
    @Column(name = "last_login_at")
    private Date lastLoginAt;

    @Column(nullable = false)
    private int status;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;




}
