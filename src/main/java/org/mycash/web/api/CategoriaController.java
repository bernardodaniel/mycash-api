package org.mycash.web.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.mycash.domain.Categoria;
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

	// Somente exemplo
	private static List<Categoria> categorias = new ArrayList<>();
	
	@GetMapping
	List<Categoria> todos() {
		return categorias;
	}
	
	@PostMapping
	Categoria criar(@RequestBody Categoria categoria) {
		categorias.add(categoria);
		return categoria;
	}
	
	@GetMapping("/{id}")
	Categoria apenasUm(@PathVariable Integer id) {
		Optional<Categoria> categoriaOp = buscaPorId(id);
		
		return categoriaOp.get();
	}
	
	@PutMapping("/{id}")
	Categoria atualizar(
			@PathVariable Integer id, 
			@RequestBody Categoria novoCategoria) {
	
		return buscaPorId(id)
			.map(categoria -> {
				categoria.setNome(novoCategoria.getNome());
				
				categorias.remove(categoria);
				categorias.add(categoria);
				
				return categoria;
			})
			.orElseThrow(() -> new EntityNotFoundException());
	}
	
	@DeleteMapping("/{id}")
	void excluir(@PathVariable Integer id) {
		buscaPorId(id)
		.map(categoria -> {
			categorias.remove(categoria);
			
			return categoria;
		})
		.orElseThrow(() -> new EntityNotFoundException());
	}

	private Optional<Categoria> buscaPorId(Integer id) {
		Optional<Categoria> categoriaOp = categorias
				.stream()
				.filter(l -> l.getId() == id)
				.reduce((u, v) -> { throw new IllegalStateException(); });
		return categoriaOp;
	}
	
	
	
}
