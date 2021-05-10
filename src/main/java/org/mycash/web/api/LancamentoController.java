package org.mycash.web.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.mycash.domain.Lancamento;
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

	// Somente exemplo
	private static List<Lancamento> lancamentos = new ArrayList<>();
	
	@GetMapping
	List<Lancamento> todos() {
		return lancamentos;
	}
	
	@PostMapping
	Lancamento criar(@RequestBody Lancamento lancamento) {
		lancamentos.add(lancamento);
		return lancamento;
	}
	
	@GetMapping("/{id}")
	Lancamento apenasUm(@PathVariable Integer id) {
		Optional<Lancamento> lancamentoOp = buscaPorId(id);
		
		return lancamentoOp.get();
	}
	
	@PutMapping("/{id}")
	Lancamento atualizar(
			@PathVariable Integer id, 
			@RequestBody Lancamento novoLancamento) {
	
		return buscaPorId(id)
			.map(lancamento -> {
				lancamento.setDescricao(novoLancamento.getDescricao());
				lancamento.setValor(novoLancamento.getValor());
				lancamento.setTipo(novoLancamento.getTipo());
				lancamento.setData(novoLancamento.getData());
				
				lancamentos.remove(lancamento);
				lancamentos.add(lancamento);
				
				return lancamento;
			})
			.orElseThrow(() -> new EntityNotFoundException());
	}
	
	@DeleteMapping("/{id}")
	void excluir(@PathVariable Integer id) {
		buscaPorId(id)
		.map(lancamento -> {
			lancamentos.remove(lancamento);
			
			return lancamento;
		})
		.orElseThrow(() -> new EntityNotFoundException());
	}

	private Optional<Lancamento> buscaPorId(Integer id) {
		Optional<Lancamento> lancamentoOp = lancamentos
				.stream()
				.filter(l -> l.getId() == id)
				.reduce((u, v) -> { throw new IllegalStateException(); });
		return lancamentoOp;
	}
	
	
	
}
