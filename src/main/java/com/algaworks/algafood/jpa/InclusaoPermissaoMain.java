package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

public class InclusaoPermissaoMain {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(
				AlgafoodApiApplication.class).web(WebApplicationType.NONE).run(args);

		PermissaoRepository repository = applicationContext.getBean(PermissaoRepository.class);
		
		Permissao permissao1 = new Permissao();
		permissao1.setNome("CONSULTAR_RESTAURANTES");
		permissao1.setDescricao("Permite consultar restaurantes");
		
		Permissao permissao2 = new Permissao();
		permissao2.setNome("EDITAR_RESTAURANTES");
		permissao2.setDescricao("Permite editar restaurantes");
		
		permissao1 = repository.salvar(permissao1);
		permissao2 = repository.salvar(permissao2);
		
		System.out.printf("%d - %s - %s\n", permissao1.getId(), permissao1.getNome(), permissao1.getDescricao());
		System.out.printf("%d - %s - %s\n", permissao2.getId(), permissao2.getNome(), permissao2.getDescricao());
		
	}

}
