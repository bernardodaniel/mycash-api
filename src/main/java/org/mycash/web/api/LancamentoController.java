package org.mycash.web.api;

import java.util.List;

import javax.validation.Valid;

import org.mycash.domain.Lancamento;
import org.mycash.service.LancamentoService;
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
@RequestMapping("/api/lancamento")
public class LancamentoController {

	@Autowired
	private LancamentoService service;
	
	@GetMapping
	List<Lancamento> todos() {
		return service.findAll();
	}
	
	@PostMapping
	Lancamento criar(@Valid @RequestBody Lancamento lancamento) {
		return service.save(lancamento);
	}
	
	@GetMapping("/{id}")
	Lancamento apenasUm(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PutMapping("/{id}")
	Lancamento atualizar(
			@PathVariable Integer id, 
			@RequestBody Lancamento novoLancamento) {
	
		return service.atualizar(id, novoLancamento);
	}
	
	@DeleteMapping("/{id}")
	void excluir(@PathVariable Integer id) {
		service.deleteById(id);
	}
	
}
