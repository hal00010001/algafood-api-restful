package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
	@Autowired
	private CozinhaRepository repository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return repository.save(cozinha);
	}

	public void excluir(Long id) {
		try {
			if(!repository.existsById(id)) {
				throw new EntidadeNaoEncontradaException(
						String.format("Não existe um cadastro de cozinha com o código %d", id));
			}
			repository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
			
		}
	}
	
}
