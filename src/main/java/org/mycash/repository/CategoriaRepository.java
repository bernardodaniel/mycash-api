package org.mycash.repository;

import org.mycash.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository 
	extends JpaRepository<Categoria, Integer>{

}
