package org.mycash.web.api;

import java.util.List;

import javax.validation.Valid;

import org.mycash.domain.Usuario;
import org.mycash.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@GetMapping
	List<Usuario> todos() {
		return service.findAll();
	}
	
	@PostMapping
	Usuario criar(@Valid @RequestBody Usuario usuario) {
		return service.save(usuario);
	}
	
	@GetMapping("/{email}")
	Usuario apenasUm(@PathVariable("email") String email) {
		return service.findByEmail(email);
	}
	
	@PutMapping("/{email}/senha")
	Usuario trocarSenha(
			@PathVariable("email") String email, 
			@RequestParam(required = true) String senhaAntiga, 
			@RequestParam(required = true) String senhaNova) {
	
		return service.trocarSenha(email, senhaAntiga, senhaNova);
	}
	
}
