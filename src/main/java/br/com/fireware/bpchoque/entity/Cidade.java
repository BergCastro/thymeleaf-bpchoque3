package br.com.fireware.bpchoque.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


import lombok.Data;

@Data
@Entity
@Table(name = "cidades")
public class Cidade {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_CIDADE")
	private Long id;

	@NotEmpty(message = "O nome não pode estar vazio!")
	@Length(max = 40, message = "O campo tem que ter no máximo {max} caracteres")
	@Column(name = "NOME_CIDADE", nullable = false, length = 40)
	private String nome;

	@OneToMany(mappedBy = "cidade", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Collection<Bairro> bairro;

}
