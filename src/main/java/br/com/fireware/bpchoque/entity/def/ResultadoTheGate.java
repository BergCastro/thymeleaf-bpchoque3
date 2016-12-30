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

@Table(name="RESULTADO_THE_GATE")
@Entity
public class ResultadoTheGate {
	
	public enum Situacao{
		APTO, INAPTO
	}
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="COD_RTHE_GATE")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "COD_TESTE_FISICO")
	private TesteFisico testeFisico;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "COD_PESSOA")
	private Pessoa pessoa;
		
	
	
	
}
