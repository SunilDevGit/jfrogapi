package com.sacumen.jfrog.service;

import java.util.List;

import com.sacumen.jfrog.model.JfrogProject;
import com.sacumen.jfrog.model.JfrogUser;


public interface JfrogService {

	List<JfrogUser> fetchUsers( );

	List<JfrogProject> fetchAllProject();

}
