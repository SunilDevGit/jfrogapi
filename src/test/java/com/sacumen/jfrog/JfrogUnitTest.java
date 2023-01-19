package com.sacumen.jfrog;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.sacumen.jfrog.controller.AuthController;
import com.sacumen.jfrog.controller.JfrogController;
import com.sacumen.jfrog.model.JfrogProject;
import com.sacumen.jfrog.model.JfrogUser;
import com.sacumen.jfrog.request.LoginRequest;
import com.sacumen.jfrog.serviceImpl.JfrogServiceImpl;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
 class JfrogUnitTest {


	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private JfrogServiceImpl jfrogService;
	
	
	
	public List<JfrogProject> projectList;
	public List<JfrogUser> userList;
	
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(AuthController.class,JfrogController.class).build();
	}

	
	@Test
	@WithMockUser
	 void test_fetchProjects() throws Exception {
		
		Mockito.when(jfrogService.fetchAllProject()).thenReturn(this.projectList);
		this.mockMvc.perform(get("/jfrog/projects")
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().is(200))
				.andDo(print()).andReturn();
	}
	
	
	@Test
	@WithMockUser
	 void test_fetchUsers() throws Exception {

		Mockito.when(jfrogService.fetchUsers()).thenReturn(userList);
		this.mockMvc.perform(get("/jfrog/users")
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)).andExpect(status().is(200))
				.andDo(print()).andReturn();
	}
	
	@Test
	void test_NonUserAuthentication() throws Exception {
		
		
		LoginRequest loginRequest=new LoginRequest();
		loginRequest.setPassword("password");
		loginRequest.setUsername("wrong_user");
		 
		Gson gson=new Gson();
		
		 String body= gson.toJson(loginRequest);
		
		 
		this.mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isUnauthorized()).andDo(print()).andReturn();
	}
	
	
	}




