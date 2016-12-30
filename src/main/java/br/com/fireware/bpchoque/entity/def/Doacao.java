//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.entity.def;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "doacoes")
public class Doacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @NotNull(message="O campo não pode estar vazio!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(columnDefinition = "DATE")
	private LocalDate dataDoacao;

	private String doador;

	@OneToMany(orphanRemoval=true)
	private List<DoacaoDetalhe> doacaoDetalhe;

	private double valor;
	@Enumerated(EnumType.STRING)
	private DoacaoTipo tipo;
	
	public enum DoacaoTipo {
		OBJETO("Objeto"),
		VALOR("Valor");
		
		private String descricao;
		
		DoacaoTipo(String descricao) {
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return descricao;
		}
	}

}
