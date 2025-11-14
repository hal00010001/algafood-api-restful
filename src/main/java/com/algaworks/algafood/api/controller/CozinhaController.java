package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository repository;
	
	@Autowired
	private CadastroCozinhaService service;

	@GetMapping
	public List<Cozinha> listar() {
		return repository.findAll();
//		return repository.listar();
	}
	
	public CozinhasXmlWrapper listarXml() {
		return new CozinhasXmlWrapper(repository.findAll());				
	}
	
	@PostConstruct
	public void init() {
	    System.out.println(">>> CozinhaController foi carregado!");
	}
	
//	Se a variável não for igual a do método, deve ser especificado como @PathVariable("cozinhaId")
//	O status pode ser alterado se necessário, mas normalmente o Spring já manda o status corretamente
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Optional<Cozinha> cozinha = repository.findById(id);
		
		if(cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());
		}
		return ResponseEntity.notFound().build();
//		return repository.buscar(id);
	}
	
//	O ResponseEntity é usado quando é necessário fazer uma condição dentro do método
	@GetMapping("/response/{id}")
	public ResponseEntity<Cozinha> buscarResponseEntity(@PathVariable Long id){
//		Cozinha cozinha = repository.buscar(id);
//		return ResponseEntity.status(HttpStatus.OK).body(cozinha);
//		return ResponseEntity.ok(cozinha);
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
		
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.headers(headers)
				.build();
		
	}
	
	/*
	 * @GetMapping("/response-condicional/{id}") public ResponseEntity<Cozinha>
	 * buscarResponseEntityCondicional(@PathVariable Long id){
	 * 
	 * Cozinha cozinha = repository.buscar(id);
	 * 
	 * if(cozinha != null) { return ResponseEntity.ok(cozinha); }
	 * 
	 * // return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); return
	 * ResponseEntity.notFound().build();
	 * 
	 * }
	 */
	
	@GetMapping("/like")
	public List<Cozinha> buscarComLike(String nome){
		return repository.findTodasByNomeContaining(nome);
	}
	
	@GetMapping("/exists")
	public boolean verificarNomeExiste(String nome) {
		return repository.existsByNome(nome);
	}
		
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {		
		return service.salvar(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable("cozinhaId") Long id, @RequestBody Cozinha cozinha) {
		
		Optional<Cozinha> cozinhaAtual = repository.findById(id);
		
		if(cozinhaAtual.isPresent()) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
			Cozinha cozinhaSalva = repository.save(cozinhaAtual.get());
			return ResponseEntity.ok(cozinhaSalva);
		}
		
		return ResponseEntity.notFound().build();
		
		/*
		 * Cozinha cozinhaAtual = repository.buscar(id); if(cozinhaAtual != null) { // O
		 * terceiro item "id" é colocado pra ignorar a propriedade id quando copiar os
		 * objetos BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		 * service.salvar(cozinhaAtual);
		 * 
		 * return ResponseEntity.ok(cozinhaAtual); } return
		 * ResponseEntity.notFound().build();
		 */
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
		try {			
			service.excluir(id);
			return ResponseEntity.noContent().build();
					
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
		}
		
	}

}

