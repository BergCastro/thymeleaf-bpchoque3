package br.com.fireware.bpchoque.entity.def;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;


import br.com.fireware.bpchoque.entity.Pessoa;
import lombok.Data;

@Data
@Table(name="RESULTADO_THE_COTAR")
@Entity
public class ResultadoTheCotar {
	
	public enum SituacaoTheCotar{
		APTO, INAPTO
	}
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="COD_RTHE_COTAR")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "COD_TESTE_FISICO")
	private TesteFisico testeFisico;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "COD_PESSOA")
	private Pessoa pessoa;
		
	
	@Column(name="SALTO_PLATAFORMA_THE_COTAR")
	@Enumerated(EnumType.STRING)
	private SituacaoTheCotar salto_plataforma;
	
	@Column(name="FLUTUACAO_THE_COTAR")
	@Enumerated(EnumType.STRING)
	private SituacaoTheCotar flutuacao;
	
	@Column(name="NATACAO_200M_THE_CDC")
	private Integer natacao_200m;
	
	@Column(name="SHUTLERUN_THE_CDC")
	private Integer shutlerun;
	
	@Column(name="CORRIDA50_SOBR_THE_CDC")
	private Integer corrida_50m_sobr;
	

	
	
}
