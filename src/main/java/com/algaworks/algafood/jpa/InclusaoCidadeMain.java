package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

public class InclusaoCidadeMain {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(
				AlgafoodApiApplication.class).web(WebApplicationType.NONE).run(args);

		CidadeRepository repository = applicationContext.getBean(CidadeRepository.class);
		
		Estado estado = new Estado();
		estado.setId(1L);
		
		Cidade cidade1 = new Cidade();
		cidade1.setNome("Paranavaí");
		cidade1.setEstado(estado);
		
		
		Cidade cidade2 = new Cidade();
		cidade2.setNome("Pato Branco");
		cidade2.setEstado(estado);
		
		
		cidade1 = repository.save(cidade1);
		cidade2 = repository.save(cidade2);
		
		System.out.printf("%d - %s - %s\n", cidade1.getId(), cidade1.getNome(), cidade1.getEstado().getNome());
		System.out.printf("%d - %s - %s\n", cidade2.getId(), cidade2.getNome(), cidade2.getEstado().getNome());
		
	}

}
