package com.sacumen.jfrog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JfrogProject {
	
	
	private String display_name;

	private String description;

	
	private AdminPrivileges admin_privileges;

	private Long storage_quota_bytes;

	private Boolean soft_limit;

	private Boolean storage_quota_email_notification;

	private String project_key;
	
	
}
