package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
//	@JsonIgnore serve para tirar esta propriedade, mas o JsonProperty não pode estar presente, no Post quando estiver ativo, tem que colocar o nome da propriedade alterada "título": ""
	@JsonProperty("título")
	@Column(nullable = false)
	private String nome;
			
}
