package br.com.fireware.bpchoque.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import br.com.fireware.bpchoque.entity.Cidade;

import br.com.fireware.bpchoque.repository.CidadeRepository;


@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CidadeService {

	@Autowired
	private CidadeRepository repository;
	
	
	@Transactional(readOnly = false)
	public void save(Cidade cidade){
		
		repository.save(cidade);
		
	}
	
	
	@Transactional(readOnly = false)
	public void delete(Long id){
		repository.delete(id);
		
	}
	
	
	@Transactional(readOnly = false)
	public void delete(Cidade cidade){
		
		repository.delete(cidade);
		
	}
	
	
	
	public Cidade finById(Long id){
		
		return repository.findOne(id);
		
	}
	
	
	
	public Iterable<Cidade> findAll(){
		return repository.findAll();
	}
	
	
	
	
	
}
	
	