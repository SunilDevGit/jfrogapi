package com.sacumen.jfrog.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sacumen.jfrog.model.JfrogProject;
import com.sacumen.jfrog.model.JfrogUser;

@Component
public class RestClientUtil {

	private static final Logger LOG = LoggerFactory.getLogger(RestClientUtil.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${access.token}")
	private String accessToken;

	private HttpEntity<String> httpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(AppConstants.AUTH_HEADER, AppConstants.TOKEN_TYPE + accessToken);
		return new HttpEntity<>(null, headers);

	}

	public JfrogUser[] getUsers(String uri) {
		LOG.info(" Inside RestClientUtil class ");
		return this.restTemplate.exchange(uri, HttpMethod.GET, httpEntity(), JfrogUser[].class).getBody();

	}

	public JfrogProject[] getProjects(String uri) {
		LOG.info(" Inside ResytClientUtil class ");
		return this.restTemplate.exchange(uri, HttpMethod.GET, httpEntity(), JfrogProject[].class).getBody();
	}
}