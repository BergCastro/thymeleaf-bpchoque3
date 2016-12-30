package br.com.fireware.bpchoque.entity.def;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="PONTOS_THE_COTAR")
public class PontosTheCotar{
	@Id
	@GeneratedValue
	@Column(name="COD_PTS")
	private int id;
	
	
	
	@Column(name="EXERCICIO_PTS")
	private String exercicio;
	
	@Column(name="REF_INICIAL_PTS")
	private int ref_inicial;
	@Column(name="REF_FINAL_PTS")
	private int ref_final;
	@Column(name="VALOR_PTS")
	private int valor;
	
	
	
	
}
