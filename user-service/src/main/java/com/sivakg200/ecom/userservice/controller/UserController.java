package com.sivakg200.ecom.userservice.controller;

import com.sivakg200.ecom.userservice.entities.User;
import com.sivakg200.ecom.userservice.entities.UserRoles;
import com.sivakg200.ecom.userservice.model.APIResponse;
import com.sivakg200.ecom.userservice.services.UserService;
import com.sivakg200.ecom.userservice.util.ResourceNotFoundException;
import com.sivakg200.ecom.userservice.util.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public ResponseEntity<APIResponse<List<User>>> getAll(HttpServletRequest req) {

		log.info("Start getAll User");
		List<User> allUsers = userService.getAll();
		if (allUsers == null) {
			throw new ResourceNotFoundException("Users not found");
		}
		APIResponse<List<User>> resp = ResponseUtil.success(allUsers, "Success", req.getRequestURI());
		log.info("End getAll User");
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<User>> getById(@PathVariable int id, HttpServletRequest req) {

		User user = userService.getById(id);
		if (user == null) {
			throw new ResourceNotFoundException("Users not found with id " + id);
		}
		APIResponse<User> resp = ResponseUtil.success(user, "Success", req.getRequestURI());
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}

	@GetMapping("by-email/{email}")
	public ResponseEntity<APIResponse<User>> getByEmail(@PathVariable String email, HttpServletRequest req) {
		// return userService.getByEmail(email);
		User user = userService.getByEmail(email);
		if (user == null) {
			throw new ResourceNotFoundException("Users not found with email " + email);
		}
		APIResponse<User> resp = ResponseUtil.success(user, "Success", req.getRequestURI());
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}

	@PostMapping("/")
	public ResponseEntity<APIResponse<User>> create(@RequestBody @Valid User user, HttpServletRequest req) {
		if (userService.getByEmail(user.getEmail()) == null) {
			userService.create(user);
			APIResponse<User> resp = ResponseUtil.success(user, "Success", req.getRequestURI());
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		} else {
			throw new DuplicateKeyException("Email Already Exist.");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<APIResponse<User>> updateById(@PathVariable int id, @RequestBody @Valid User user,
			HttpServletRequest req) {
		if (userService.getByEmail(user.getEmail()) == null) {
			userService.update(id, user);
			APIResponse<User> resp = ResponseUtil.success(user, "Success", req.getRequestURI());
			return new ResponseEntity<>(resp, HttpStatus.CREATED);

		} else {
			throw new DuplicateKeyException("Email Already Exist.");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<User>> deleteUserById(@PathVariable int id, HttpServletRequest req) {
		// return userService.deleteRolesById(id, rid);
		User user = userService.getById(id);
		if (user == null) {
			throw new ResourceNotFoundException("Users not found with id " + id);
		}
		userService.deleteById(id);
		APIResponse<User> resp = ResponseUtil.success(user, "Success", req.getRequestURI());
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@GetMapping("/{id}/roles")
	public ResponseEntity<APIResponse<List<UserRoles>>> getRolesById(@PathVariable int id, HttpServletRequest req) {
		List<UserRoles> allUserRoles = userService.getRolesByUserId(id);
		if (allUserRoles == null) {
			throw new ResourceNotFoundException("Users rolse not found");
		}
		APIResponse<List<UserRoles>> resp = ResponseUtil.success(allUserRoles, "Success", req.getRequestURI());
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}

	@GetMapping("/{id}/roles/{rid}")
	public ResponseEntity<APIResponse<UserRoles>> getRolesById(@PathVariable int id, @PathVariable int rid,
			HttpServletRequest req) {
		// return userService.getRolesById(id, rid);
		UserRoles userRole = userService.getRolesById(id, rid);
		if (userRole == null) {
			throw new ResourceNotFoundException("Users Role not found with userid " + id+", role id "+rid);
		}
		APIResponse<UserRoles> resp = ResponseUtil.success(userRole, "Success", req.getRequestURI());
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@PostMapping("/{id}/roles/{rid}")
	public ResponseEntity<APIResponse<List<UserRoles>>> addRolesById(@PathVariable int id, @PathVariable int rid,HttpServletRequest req) {
		 
		List<UserRoles> allUserRoles = userService.addRolesById(id, rid);
		if (allUserRoles == null) {
			throw new ResourceNotFoundException("Users roles not found");
		}
		APIResponse<List<UserRoles>> resp = ResponseUtil.success(allUserRoles, "Success", req.getRequestURI());
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@DeleteMapping("/{id}/roles/{rid}")
	public ResponseEntity<APIResponse<List<UserRoles>>> deleteRolesById(@PathVariable int id, @PathVariable int rid,HttpServletRequest req) {
	
		List<UserRoles> allUserRoles = userService.deleteRolesById(id, rid);
		if (allUserRoles == null) {
			throw new ResourceNotFoundException("Users roles not found");
		}
		APIResponse<List<UserRoles>> resp = ResponseUtil.success(allUserRoles, "Success", req.getRequestURI());
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}
