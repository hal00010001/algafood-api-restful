package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

public class AlteracaoPermissaoMain {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(
				AlgafoodApiApplication.class).web(WebApplicationType.NONE).run(args);

		PermissaoRepository repository = applicationContext.getBean(PermissaoRepository.class);
		
		Permissao permissao = new Permissao();
		permissao.setId(1L);
		permissao.setNome("BUSCAR_COZINHAS");
		permissao.setDescricao("Permite buscar cozinhas");
		
		repository.save(permissao);
		
	}

}
