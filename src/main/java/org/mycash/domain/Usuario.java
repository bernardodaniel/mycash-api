package org.mycash.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank
	@Size(max = 20)
	private String email;
	
	@NotBlank
	@Size(max = 120)
	private String senha;
	
	private UsuarioRole role;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsuarioRole getRole() {
		return role;
	}

	public void setRole(UsuarioRole role) {
		this.role = role;
	}
	
}
