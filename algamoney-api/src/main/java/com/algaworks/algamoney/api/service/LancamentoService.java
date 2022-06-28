package com.algaworks.algamoney.api.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.exceptionhandler.PessoaInativaException;
import com.algaworks.algamoney.api.exceptionhandler.PessoaInexistenteException;
import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.LancamentoRepository;
import com.algaworks.algamoney.api.repository.PessoaRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		
		Lancamento lancamentoSalva = getLancamentoById(codigo);

		BeanUtils.copyProperties(lancamento, lancamentoSalva, "codigo");
		return this.lancamentoRepository.save(lancamentoSalva);
		
		
	}
	
	public Lancamento getLancamentoById (Long codigo){
		
		return lancamentoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	public Lancamento savarLancamento(@Valid Lancamento lancamento) {
		
		Pessoa pessoa = pessoaRepository.getPessoaPorCodigo(lancamento.getPessoa().getCodigo());
		
		if (pessoa == null) {
			throw new PessoaInexistenteException();
		}
		else if (!lancamento.getPessoa().isAtivo()) {
			throw new PessoaInativaException();
		}
			
		return lancamentoRepository.save(lancamento);
	}

	/*public Lancamento findById(Long codigo) {
		
		Optional<Lancamento> lancamento = this.lancamentoRepository.findById(codigo);
		
		return lancamento.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}*/
}
