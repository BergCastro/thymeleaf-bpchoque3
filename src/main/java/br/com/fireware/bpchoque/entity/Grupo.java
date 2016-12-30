package br.com.fireware.bpchoque.entity;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


import lombok.Data;

@Entity
@Data
@Table(name="grupos")
public class Grupo{
	
	/**
	 * 
	 */

	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="COD_GRUPO")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="COD_OPM_ORGAO")
	private OpmOrgao opmOrgao;
	@NotEmpty(message="O campo não pode estar vazio!")
	@Length(max=30, message="O campo tem que ter no máximo {max} caracteres")
	@Column(name="NOME_GRUPO", nullable= false, length=30, unique=true)
	private String nome;
	@NotEmpty(message="O campo não pode estar vazio!")
	@Length(max=20, message="O campo tem que ter no máximo {max} caracteres")
	@Column(name="ABREVIACAO_GRUPO", nullable= false, length=20, unique=true)
	private String abreviacao;
	
	
	
	
	
	
}
