//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.entity.def;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import javax.persistence.Table;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name="TESTE_FISICO")
public class TesteFisico{
	
	


	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private Long id;
	
	@NotNull(message="O campo não pode estar vazio!")
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "DATA_INICIAL", columnDefinition="DATE")
	private LocalDate dataInicial;
	
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "DATA_FINAL", columnDefinition="DATE")
	private LocalDate dataFinal;
	
	
	@NotEmpty(message="O campo não pode estar vazio!")
	
	private String objetivo;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusTeste status;
	
	@NotNull(message="Selecione uma opção!")
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoTesteFisico tipo;
	
	@NotNull(message="Selecione uma opção!")
	
	@Column(name = "media")
	private Double media;
	
	
	public enum Media {
		SETE(7.00), SETE_MEIO(7.50), OITO(8.00),
		OITO_MEIO(8.50), NOVE(9.00), NOVE_MEIO(9.50), DEZ(10.00);
		
		private final Double descricao;
		Media(Double descricao){
			this.descricao = descricao;
		}
		public Double getDescricao(){
			return descricao;
		}
	}
	
	public enum StatusTeste {
		PREVISTO("Previsto"), EM_ANDAMENTO("Em andamento"), CONCLUIDO("Concluído");
		
		private final String descricao;
		StatusTeste(String descricao){
			this.descricao = descricao;
		}
		public String getDescricao(){
			return descricao;
		}
	}
	
	
	
	public enum TipoTesteFisico {
		TAF("TAF"), TAF_THE_CDC("TAF + THE - CDC"), TAF_THE_COTAR("TAF + THE - COTAR"),
		TAF_THE_COTAM("TAF + THE - COTAM"), TAF_THE_GATE("TAF + THE - GATE"), TAF_THE_CANIL("TAF + THE - CANIL");
		
		private String descricao;
		TipoTesteFisico(String descricao){
			this.descricao = descricao;
		}
		public String getDescricao(){
			return descricao;
		}
	}
	
	
	

	

	

	
	
	
	
	
	

}
