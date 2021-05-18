package org.mycash.web.dto;

import org.mycash.domain.UsuarioRole;

public class UsuarioDTO {

	private Integer id;
	
	private String email;
	
	private UsuarioRole role;
	
	public boolean isAdmin() {
		return UsuarioRole.ROLE_ADMIN.equals(this.role);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(UsuarioRole role) {
		this.role = role;
	}
	
}
