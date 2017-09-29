//TESTE_FISICO(COD_TF, DATA_TF, COD_TTF)
package br.com.fireware.bpchoque.entity.def;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;



import org.springframework.format.annotation.DateTimeFormat;

import br.com.fireware.bpchoque.entity.Pessoa;
import lombok.Data;

@Data
@Entity
@Table(name="avaliador")
public class Avaliador{
	
	
	

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	
	//@NotNull(message="O campo não pode estar vazio!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "DATA_AVALIACAO", columnDefinition="DATE")
	private LocalDate dataAvaliacao;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn
	private Pessoa pessoa;
	
	private Boolean praticaTipoAtividade;
	
	private String praticaTipoAtividadeQual;
	
	
	@Enumerated(EnumType.STRING)
	private Frequencia frequencia;
	
	@Enumerated(EnumType.STRING)
	private Duracao duracao;
	
	
	@Enumerated(EnumType.STRING)
	private Objetivos objetivo;
	
	private String objetivoOutro;
	
		
	private Boolean restricao;
	
	private String restricaoQual;
	
	private Boolean fuma;
	
	private Boolean bebidaAlcoolica;
	
	private Boolean medicamento;
	
	private String medicamentoQual;
	
	private String medicamentoMotivo;
	
	@ElementCollection(targetClass=Problemas.class)
	@Enumerated(EnumType.STRING) // Possibly optional (I'm not sure) but defaults to ORDINAL.
	@CollectionTable(name="avaliacao_problemas", joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
	@Column(name="avaliacao_problemas") // Column name in person_interest
	private List<Problemas> problema;
	
	private String problemaOutro;
	
	private String problemaArticulacaoQual;
	


	
	
	
	public enum Frequencia {
		UMA_VEZ_POR_SEMANA("Uma vez por semana"), DUAS_VEZES_POR_SEMANA("Duas vezes por semana"), 
		TRES_VEZES_POR_SEMANA("Três vezes por semana"), QUATRO_VEZES_POR_SEMANA("Quatro vezes por semana"),
		SOMENTE_AOS_FINAIS_DE_SEMANA("Somente aos finais de semana"), TODOS_OS_DIAS("Todos os dias");
		
		private String descricao;
		Frequencia(String descricao){
			this.descricao = descricao;
		}
		public String getDescricao(){
			return descricao;
		}
	}
	
	public enum Duracao {
		MENOS_DE_30MIN("Menos de 30 minutos"), TRINTA_MIN("Trinta minutos"), ENTRE_40_A_50MIN("Entre 40 a 50 minutos"),
		UMA_HORA("Uma hora"), ACIMA_DE_1H("Acima de uma hora");
		
		private String descricao;
		Duracao(String descricao){
			this.descricao = descricao;
		}
		public String getDescricao(){
			return descricao;
		}
	}
	
	public enum Objetivos {
		EMAGRECIMENTO("Emagrecimento"), QUALIDADE_DE_VIDA("Quaidade de vida"), GANHO_DE_MASSA_MUSCULAR("Ganho de massa muscular"), 
		CONDICIONAMENTO("Condicionamento"), REABILITACAO("Reabilitação"),
		TREINAMENTO_ESPORTIVO("Treinamento Esportivos"), LAZER("Lazer"), OUTROS("Outros");
		
		private String descricao;
		Objetivos(String descricao){
			this.descricao = descricao;
		}
		public String getDescricao(){
			return descricao;
		}
	}
	
	
	public enum Problemas {
		ARTICULACAO("Articulação"), HIPERTENSAO("Hipertensão"), DIABETES("Diabetes"), RENAL("Renal"), 
		OBESIDADE("Obesidade"),	HERNIA_DE_DISCO("Hernia de disco"), CARDIOPATIA("Cardiopata"), OUTROS("Outros");
		
		private String descricao;
		Problemas(String descricao){
			this.descricao = descricao;
		}
		public String getDescricao(){
			return descricao;
		}
	}
	
	
	
	

}
