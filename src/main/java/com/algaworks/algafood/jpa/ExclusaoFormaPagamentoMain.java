package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

public class ExclusaoFormaPagamentoMain {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(
				AlgafoodApiApplication.class).web(WebApplicationType.NONE).run(args);

		FormaPagamentoRepository repository = applicationContext.getBean(FormaPagamentoRepository.class);
		
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setId(1L);
		
		repository.remover(formaPagamento);
		
	}

}
