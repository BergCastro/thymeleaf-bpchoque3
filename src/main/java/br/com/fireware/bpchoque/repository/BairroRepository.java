package br.com.fireware.bpchoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fireware.bpchoque.entity.Bairro;
import br.com.fireware.bpchoque.entity.Cidade;

public interface BairroRepository extends JpaRepository<Bairro, Long> {

	Iterable<Bairro> findByCidade(Cidade cidade);

}
