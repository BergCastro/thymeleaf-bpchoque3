package br.com.fireware.bpchoque.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



import br.com.fireware.bpchoque.entity.Grupo;

import br.com.fireware.bpchoque.service.GrupoService;
import lombok.Data;

@Controller
@Data
public class GrupoController {

	@Autowired
	GrupoService grupoService;

	private Grupo grupo = new Grupo();

	private Iterable<Grupo> grupos;
	
	
	

	
	

	public void salvar(Grupo grupo) {

		grupoService.save(grupo);
		this.grupo = new Grupo();

	}

	public void excluir(Grupo grupo) {

		System.out.println("Entrou no método excluir");
		grupoService.delete(grupo.getId());

	}

	public void editar(Grupo grupo) {

	}

	
	

}
