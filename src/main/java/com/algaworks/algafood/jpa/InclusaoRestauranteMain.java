package com.algaworks.algafood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class InclusaoRestauranteMain {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(
				AlgafoodApiApplication.class).web(WebApplicationType.NONE).run(args);

		RestauranteRepository repository = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("Comida Brasileira");
		restaurante1.setTaxaFrete(new BigDecimal("14.00"));
		
		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Comida Japonesa");
		restaurante2.setTaxaFrete(new BigDecimal("11.00"));
		
		restaurante1 = repository.save(restaurante1);
		restaurante2 = repository.save(restaurante2);
		
		System.out.printf("%d - %s\n", restaurante1.getId(), restaurante1.getNome());
		System.out.printf("%d - %s\n", restaurante2.getId(), restaurante2.getNome());
		
	}

}
