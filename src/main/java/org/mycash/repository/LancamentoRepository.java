package org.mycash.repository;

import org.mycash.domain.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository 
	extends JpaRepository<Lancamento, Integer> {

}
