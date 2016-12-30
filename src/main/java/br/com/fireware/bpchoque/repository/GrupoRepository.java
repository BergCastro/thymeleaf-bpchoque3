package br.com.fireware.bpchoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



import br.com.fireware.bpchoque.entity.Grupo;
import br.com.fireware.bpchoque.entity.OpmOrgao;


public interface GrupoRepository extends JpaRepository<Grupo, Long>{

	Grupo findByNome(String nome);
	
	/*@Query("select g from Grupo g where g.companhia=?1")
	List<Grupo> buscaPorCompanhia(Integer cod);*/
	
	List<Grupo> findByOpmOrgao(OpmOrgao opmOrgao);

}
