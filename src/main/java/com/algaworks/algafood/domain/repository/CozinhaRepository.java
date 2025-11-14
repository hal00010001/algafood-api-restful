package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;

//@Component
@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {	
	
	List<Cozinha> findTodasByNome(String nome);
	
	List<Cozinha> findTodasByNomeContaining(String nome);
	
	Optional<Cozinha> findByNome(String nome);
	
	boolean existsByNome(String nome);
			
	/*
	 * List<Cozinha> listar(); List<Cozinha> consultarPorNome(String nome); Cozinha
	 * buscar(Long id); Cozinha salvar(Cozinha cozinha); void remover(Long id);
	 */
	
}
