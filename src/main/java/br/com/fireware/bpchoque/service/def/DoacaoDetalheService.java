package br.com.fireware.bpchoque.service.def;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import br.com.fireware.bpchoque.entity.def.DoacaoDetalhe;
import br.com.fireware.bpchoque.repository.def.DoacaoDetalheRepository;


@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class DoacaoDetalheService {

	@Autowired
	private DoacaoDetalheRepository repository;

	@Transactional(readOnly = false)
	public void save(DoacaoDetalhe doacaoDetalhe) {

		repository.save(doacaoDetalhe);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(DoacaoDetalhe doacaoDetalhe) {

		repository.delete(doacaoDetalhe);

	}

	public DoacaoDetalhe finById(Long id) {

		return repository.findOne(id);

	}

	public List<DoacaoDetalhe> findAll() {
		return repository.findAll();
	}
	
	

}
