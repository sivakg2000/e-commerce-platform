package com.sivakg200.ecom.userservice.controller;

import com.sivakg200.ecom.userservice.entities.User;
import com.sivakg200.ecom.userservice.entities.UserRoles;
import com.sivakg200.ecom.userservice.model.APIResponse;
import com.sivakg200.ecom.userservice.services.UserService;
import com.sivakg200.ecom.userservice.util.ResourceNotFoundException;
import com.sivakg200.ecom.userservice.util.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public ResponseEntity<APIResponse<List<User>>> getAll(HttpServletRequest req) {

		List<User> allUsers = userService.getAll();
		if (allUsers == null) {
			throw  new ResourceNotFoundException("Users not found");
		}
		APIResponse<List<User>> resp = ResponseUtil.success(allUsers, "Success", req.getRequestURI());
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<User>>  getById(@PathVariable int id,HttpServletRequest req) {
		 
		
		User user=  userService.getById(id);
		if (user == null) {
			throw  new ResourceNotFoundException("Users not found with id "+id);
		}
		APIResponse<User> resp = ResponseUtil.success(user, "Success", req.getRequestURI());
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}

	@GetMapping("by-email/{email}")
	public User getByEmail(@PathVariable String email) {
		return userService.getByEmail(email);

	}

	@PostMapping("/")
	public ResponseEntity<User> create(@RequestBody @Valid User user) {
		if (userService.getByEmail(user.getEmail()) == null) {
			userService.create(user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} else {
			throw new DuplicateKeyException("Email Already Exist.");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateById(@PathVariable int id, @RequestBody @Valid User user) {
		if (userService.getByEmail(user.getEmail()) == null) {
			userService.update(id, user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} else {
			throw new DuplicateKeyException("Email Already Exist.");
		}
	}

	@GetMapping("/{id}/roles")
	public List<UserRoles> getRolesById(@PathVariable int id) {
		return userService.getRolesByUserId(id);
	}

	@GetMapping("/{id}/roles/{rid}")
	public UserRoles getRolesById(@PathVariable int id, @PathVariable int rid) {
		return userService.getRolesById(id, rid);
	}

	@PostMapping("/{id}/roles/{rid}")
	public List<UserRoles> addRolesById(@PathVariable int id, @PathVariable int rid) {
		return userService.addRolesById(id, rid);
	}

	@DeleteMapping("/{id}/roles/{rid}")
	public List<UserRoles> deleteRolesById(@PathVariable int id, @PathVariable int rid) {
		return userService.deleteRolesById(id, rid);
	}
}
