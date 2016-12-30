package br.com.fireware.bpchoque.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.fireware.bpchoque.entity.Bairro;
import br.com.fireware.bpchoque.entity.Cidade;

import br.com.fireware.bpchoque.repository.BairroRepository;



@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class BairroService {

	@Autowired
	private BairroRepository repository;
	
	
	@Transactional(readOnly = false)
	public void save(Bairro bairro){
		
		repository.save(bairro);
		
	}
	
	
	@Transactional(readOnly = false)
	public void delete(Long id){
		repository.delete(id);
		
	}
	
	
	@Transactional(readOnly = false)
	public void delete(Bairro bairro){
		
		repository.delete(bairro);
		
	}
	
	
	
	public Bairro finById(Long id){
		
		return repository.findOne(id);
		
	}
	
	
	
	public Iterable<Bairro> findAll(){
		return repository.findAll();
	}
	
	
	
	public Iterable<Bairro> findByCidade(Cidade cidade){
		return repository.findByCidade(cidade);
	}
	
	
}
	
	