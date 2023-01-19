package com.sacumen.jfrog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sacumen.jfrog.model.JfrogProject;
import com.sacumen.jfrog.model.JfrogUser;
import com.sacumen.jfrog.service.JfrogService;

@RestController
@RequestMapping("/jfrog")
public class JfrogController {

	
	private static final Logger LOG = LoggerFactory.getLogger(JfrogController.class);

	
	@Autowired
	private JfrogService jfrogService;

	@GetMapping("/users")
	public ResponseEntity<List<JfrogUser>> getJfrogUsers() {
		LOG.info("Inside method get User");
		List<JfrogUser> userList=this.jfrogService.fetchUsers();
		return new ResponseEntity<>(userList,HttpStatus.OK);
	}

	@GetMapping("/projects")
	public ResponseEntity<List<JfrogProject>> fetchJfrogProjects() {
		LOG.info("Inside method get Projects");
		List<JfrogProject> jfrogProjects= this.jfrogService.fetchAllProject();
		return new ResponseEntity<>(jfrogProjects,HttpStatus.OK);
	}

}
