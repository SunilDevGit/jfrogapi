package com.sacumen.jfrog.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sacumen.jfrog.commons.AppConstants;
import com.sacumen.jfrog.commons.RestClientUtil;
import com.sacumen.jfrog.model.JfrogProject;
import com.sacumen.jfrog.model.JfrogUser;
import com.sacumen.jfrog.service.JfrogService;

@Service
public class JfrogServiceImpl implements JfrogService {

	private static final Logger LOG = LoggerFactory.getLogger(JfrogServiceImpl.class);
	
	@Autowired
	private RestClientUtil clientUtil;
	
	

	@Override
	public List<JfrogUser> fetchUsers() {
		JfrogUser[] objArray=this.clientUtil.getUsers(AppConstants.USER_URI);
		return Arrays.asList(objArray);
	}

	@Override
	public List<JfrogProject> fetchAllProject() {
		LOG.info(" Inside Service class get Projects");
		JfrogProject[] objArray=this.clientUtil.getProjects(AppConstants.PROJECT_URI);
		return Arrays.asList(objArray);

	}

}
