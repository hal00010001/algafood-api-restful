package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

public class InclusaoEstadoMain {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(
				AlgafoodApiApplication.class).web(WebApplicationType.NONE).run(args);

		EstadoRepository repository = applicationContext.getBean(EstadoRepository.class);
		
		Estado estado1 = new Estado();
		estado1.setNome("Santa Catarina");
				
		Estado estado2 = new Estado();
		estado2.setNome("Rio Grande do Sul");
				
		estado1 = repository.save(estado1);
		estado2 = repository.save(estado2);
		
		System.out.printf("%d - %s\n", estado1.getId(), estado1.getNome());
		System.out.printf("%d - %s\n", estado2.getId(), estado2.getNome());
		
	}

}
