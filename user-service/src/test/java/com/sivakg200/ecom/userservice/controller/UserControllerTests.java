package com.sivakg200.ecom.userservice.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sivakg200.ecom.userservice.entities.User;
import com.sivakg200.ecom.userservice.model.APIResponse;
import com.sivakg200.ecom.userservice.services.UserService;
import com.sivakg200.ecom.userservice.util.ResponseUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = UserController.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private User user;
	
	@BeforeEach
	public void init() {
		
	}
	
	@Test
	public void UserController_GettAll() throws Exception {
		
		
		List<User> resp=new ArrayList<>();
	
		when(userService.getAll()).thenReturn(resp);
		ResultActions actResp=mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON));
		
		actResp.andExpect(MockMvcResultMatchers.status().isOk());
				//.andExpect(MockMvcResultMatchers.jsonPath)
	}

}
