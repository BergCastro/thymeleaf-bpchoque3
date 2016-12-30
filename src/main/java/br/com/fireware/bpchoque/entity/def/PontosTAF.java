package br.com.fireware.bpchoque.entity.def;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Data;



@Data
@Entity
@Table(name="PONTOS_TAF")
public class PontosTAF{
	@Id
	@GeneratedValue
	@Column(name="COD_PTS_TAF")
	private int id;
	
	@Column(name="SEXO_PTS_TAF")
	private String sexo;
	
	@Column(name="EXERCICIO_PTS_TAF")
	private String exercicio;
	@Column(name="IDADE_INICIAL_PTS_TAF")
	private int idade_inicial;
	@Column(name="IDADE_FINAL_PTS_TAF")
	private int idade_final;
	@Column(name="REF_INICIAL_PTS_TAF")
	private int ref_inicial;
	@Column(name="REF_FINAL_PTS_TAF")
	private int ref_final;
	@Column(name="VALOR_PTS_TAF")
	private int valor;
	
	
}
