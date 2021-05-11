package org.mycash.web.api;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.mycash.domain.Categoria;
import org.mycash.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	List<Categoria> todos() {
		return repository.findAll();
	}
	
	@PostMapping
	Categoria criar(@RequestBody Categoria categoria) {
		return repository.save(categoria);
	}
	
	@GetMapping("/{id}")
	Categoria apenasUm(@PathVariable Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	@PutMapping("/{id}")
	Categoria atualizar(
			@PathVariable Integer id, 
			@RequestBody Categoria novoCategoria) {
	
		return repository.findById(id)
			.map(categoria -> {
				categoria.setNome(novoCategoria.getNome());
				
				return repository.save(categoria);
			})
			.orElseThrow(() -> new EntityNotFoundException());
	}
	
	@DeleteMapping("/{id}")
	void excluir(@PathVariable Integer id) {
		repository.deleteById(id);
	}
	
}
