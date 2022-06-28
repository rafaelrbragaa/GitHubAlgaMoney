package com.algaworks.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.Categoria;
import com.algaworks.algamoney.api.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria atualizar(Long codigo, Categoria categoria) {
		
		Categoria categoriaSalva = categoriaRepository.findById(codigo)
		.orElseThrow(() -> new EmptyResultDataAccessException(1));

		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");

		return this.categoriaRepository.save(categoriaSalva);
	}
}
