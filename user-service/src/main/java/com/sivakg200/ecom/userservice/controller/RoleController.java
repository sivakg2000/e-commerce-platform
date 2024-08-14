package com.sivakg200.ecom.userservice.controller;

import com.sivakg200.ecom.userservice.entities.Role;
import com.sivakg200.ecom.userservice.entities.User;
import com.sivakg200.ecom.userservice.model.APIResponse;
import com.sivakg200.ecom.userservice.services.RoleService;
import com.sivakg200.ecom.userservice.services.UserService;
import com.sivakg200.ecom.userservice.util.ResourceNotFoundException;
import com.sivakg200.ecom.userservice.util.ResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
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
	public ResponseEntity<APIResponse<List<Role>>> getAll(HttpServletRequest req) {

		List<Role> allRoles = roleService.getAll();
		if (allRoles == null) {
			throw new ResourceNotFoundException("Users not found");
		}
		APIResponse<List<Role>> resp = ResponseUtil.success(allRoles, "Success", req.getRequestURI());
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Role>> getById(@PathVariable int id, HttpServletRequest req) {

		Role role = roleService.getById(id);
		if (role == null) {
			throw new ResourceNotFoundException("Role not found");
		}
		APIResponse<Role> resp = ResponseUtil.success(role, "Success", req.getRequestURI());
		return new ResponseEntity<>(resp, HttpStatus.OK);

	}

	@PostMapping("/")
	public ResponseEntity<APIResponse<Role>> create(@RequestBody @Valid Role newRole, HttpServletRequest req) {

		APIResponse<Role> resp = ResponseUtil.success(roleService.create(newRole), "Success", req.getRequestURI());
		return new ResponseEntity<>(resp, HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<APIResponse<Role>> updateById(@PathVariable int id, @RequestBody @Valid Role updateRole,
			HttpServletRequest req) {

		Role role = roleService.getById(id);
		if (role == null) {
			throw new ResourceNotFoundException("Role not found");
		}
		APIResponse<Role> resp = ResponseUtil.success(roleService.update(id, updateRole), "Success",
				req.getRequestURI());

		return new ResponseEntity<>(resp, HttpStatus.CREATED);
	}
}
