package org.mycash.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.mycash.domain.Usuario;
import org.mycash.domain.UsuarioRole;
import org.mycash.exception.UsuarioException;
import org.mycash.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public Usuario registraUsuarioUser(String email, String senha) {
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(passwordEncoder.encode(senha));
		usuario.setRole(UsuarioRole.ROLE_USER);
		
		if (repo.findByEmail(usuario.getEmail()).isPresent())
			throw new UsuarioException("Já existe um usuário com este e-mail");
			
		return repo.save(usuario);
	}
	
	public void registraUsuarioAdmin(String email, String senha) {
		if (repo.findByEmail(email).isEmpty()) {
			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setSenha(passwordEncoder.encode(senha));
			usuario.setRole(UsuarioRole.ROLE_ADMIN);
			
			repo.save(usuario);
		}
		
	}

	public Usuario findByEmail(String email) {
		return repo
				.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException());
	}

	public Usuario trocarSenha(String email, String senhaAntiga, String senhaNova) {
		Usuario usuario = findByEmail(email);
		
		if (!passwordEncoder.matches(senhaAntiga, usuario.getSenha()))
			throw new UsuarioException("Senha antiga não confere");
		
		usuario.setSenha(passwordEncoder.encode(senhaNova));

		return repo.save(usuario);
	}

}