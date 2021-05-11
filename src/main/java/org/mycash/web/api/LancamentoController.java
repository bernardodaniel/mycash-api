package org.mycash.web.api;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.mycash.domain.Lancamento;
import org.mycash.repository.LancamentoRepository;
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
	private LancamentoRepository repository;
	
	@GetMapping
	List<Lancamento> todos() {
		return repository.findAll();
	}
	
	@PostMapping
	Lancamento criar(@RequestBody Lancamento lancamento) {
		return repository.save(lancamento);
	}
	
	@GetMapping("/{id}")
	Lancamento apenasUm(@PathVariable Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	@PutMapping("/{id}")
	Lancamento atualizar(
			@PathVariable Integer id, 
			@RequestBody Lancamento novoLancamento) {
	
		return repository.findById(id)
			.map(lancamento -> {
				lancamento.setDescricao(novoLancamento.getDescricao());
				lancamento.setValor(novoLancamento.getValor());
				lancamento.setTipo(novoLancamento.getTipo());
				lancamento.setData(novoLancamento.getData());
				
				return repository.save(lancamento);
			})
			.orElseThrow(() -> new EntityNotFoundException());
	}
	
	@DeleteMapping("/{id}")
	void excluir(@PathVariable Integer id) {
		repository.deleteById(id);
	}
	
}
