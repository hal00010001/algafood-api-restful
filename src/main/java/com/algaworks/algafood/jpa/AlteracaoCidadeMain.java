package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

public class AlteracaoCidadeMain {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(
				AlgafoodApiApplication.class).web(WebApplicationType.NONE).run(args);

		CidadeRepository repository = applicationContext.getBean(CidadeRepository.class);
		
		Estado estado = new Estado();
		
		estado.setId(1L);
		
		Cidade cidade = new Cidade();
		cidade.setId(1L);
		cidade.setNome("Guaratuba");
		cidade.setEstado(estado);
		
		repository.save(cidade);
		
	}

}
