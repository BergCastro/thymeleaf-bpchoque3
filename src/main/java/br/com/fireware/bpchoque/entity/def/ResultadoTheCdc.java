package br.com.fireware.bpchoque.entity.def;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;


import br.com.fireware.bpchoque.entity.Pessoa;
import lombok.Data;
@Data
@Table(name="RESULTADO_THE_CDC")
@Entity
public class ResultadoTheCdc {
	
	public enum SituacaoTheCdc{
		APTO, INAPTO
	}
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="COD_RTHE_CDC")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "COD_TESTE_FISICO")
	private TesteFisico testeFisico;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "COD_PESSOA")
	private Pessoa pessoa;
		
	private String participante;
	
	
	private String corrida_5km;
	
	
	
	private String granada;
	
	
	
	private String natacao_50m;
	
	
	
	private String flutuacao;
	
	
}
