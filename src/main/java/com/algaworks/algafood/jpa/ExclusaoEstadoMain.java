package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

public class ExclusaoEstadoMain {	
	
	public static void main(String[] args) {
				
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(
				AlgafoodApiApplication.class).web(WebApplicationType.NONE).run(args);

		CidadeRepository repositoryCidade = applicationContext.getBean(CidadeRepository.class);
		
		Cidade cidade1 = new Cidade();
		Cidade cidade2 = new Cidade();
		Cidade cidade3 = new Cidade();
		
		cidade1.setId(1L);
		cidade2.setId(2L);
		cidade3.setId(3L);
		
		repositoryCidade.remover(cidade1.getId());
		repositoryCidade.remover(cidade2.getId());
		repositoryCidade.remover(cidade3.getId());
		
		EstadoRepository repository = applicationContext.getBean(EstadoRepository.class);
						
		Estado estado = new Estado();
		estado.setId(1L);
		
		repository.remover(estado.getId());
		
	}

}
