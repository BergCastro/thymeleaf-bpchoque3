//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.entity.def;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "doacoesDetalhes")
public class DoacaoDetalhe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @NotNull(message="O campo não pode estar vazio!")

	private String descricao;
	
	private Integer quantidade;

}
