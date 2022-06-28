package com.algaworks.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.algaworks.algamoney.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	@Query(value="select pt from Pessoa pt where codigo = :codigo ")	
	Pessoa getPessoaPorCodigo(Long codigo);
}
