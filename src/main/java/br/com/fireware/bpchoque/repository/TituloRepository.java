package br.com.fireware.bpchoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fireware.bpchoque.entity.Titulo;

public interface TituloRepository extends JpaRepository<Titulo, Long> {

	public List<Titulo> findByDescricaoContaining(String descricao);
	
}
