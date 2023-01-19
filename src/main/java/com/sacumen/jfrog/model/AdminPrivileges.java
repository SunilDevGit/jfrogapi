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
public class AdminPrivileges {

	private Boolean manage_members;
	private Boolean manage_resources;
	private Boolean index_resources;

}
