package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository repository;

	@Autowired
	private CadastroRestauranteService service;

	@GetMapping
	public List<Restaurante> listar() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Optional<Restaurante> restaurante = repository.findById(id);
		if (restaurante.isPresent()) {
			return ResponseEntity.ok(restaurante.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(
			BigDecimal taxaInicial, BigDecimal taxaFinal){
		return repository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@GetMapping("/nome-cozinha")
	public List<Restaurante> restaurantesPorNomeECozinhaId(String nome, Long cozinhaId){
		return repository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
	}

	@GetMapping("/primeiro-nome")
	public Optional<Restaurante> buscarPrimeiroRestaurantePorNome(String nome){
		return repository.findFirstRestauranteByNomeContaining(nome);
	}
	
	@GetMapping("/top2")
	public List<Restaurante> buscarTop2RestaurantesPorNome(String nome){
		return repository.findTop2ByNomeContaining(nome);
	}
	
	@GetMapping("/count-cozinhas")
	public int contagemCozinhas(Long cozinhaId) {
		return repository.countByCozinhaId(cozinhaId);
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = service.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		try {
			Optional<Restaurante> restauranteAtual = repository.findById(id);
			if (restauranteAtual != null) {
				BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
				service.salvar(restauranteAtual.get());
				return ResponseEntity.ok(restauranteAtual);
			}
			return ResponseEntity.notFound().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		try {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {

		Optional<Restaurante> restauranteAtual = repository.findById(id);
		if (restauranteAtual == null) {
			return ResponseEntity.notFound().build();
		}

		merge(campos, restauranteAtual.get());
		return atualizar(id, restauranteAtual.get());
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

		System.out.println(restauranteOrigem);

		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {

			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);

			field.setAccessible(true);

			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

			ReflectionUtils.setField(field, restauranteDestino, novoValor);

		});
	}

}
