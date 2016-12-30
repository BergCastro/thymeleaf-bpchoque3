package br.com.fireware.bpchoque.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "bairros")
public class Bairro extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_BAIRRO")
	private Long id;
	@NotEmpty(message = "O campo não pode estar vazio!")
	@Length(max = 40, message = "O campo tem que ter no máximo {max} caracteres")
	@Column(name = "NOME_BAIRRO", nullable = false, length = 40)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "COD_CIDADE")
	private Cidade cidade;
	
	
	@OneToMany(mappedBy="bairro", cascade={CascadeType.PERSIST, CascadeType.MERGE}) 
	private Collection<Pessoa> pessoa;
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setBairro(String nome) {
		this.nome = nome;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	

	public void setNome(String nome) {
		this.nome = nome;
	}

}
