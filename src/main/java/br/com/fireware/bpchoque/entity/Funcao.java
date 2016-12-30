//PATENTE(COD_PATENTE, NOME_PATENTE, ABREVIACAO_PATENTE)

package br.com.fireware.bpchoque.entity;



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
@Table(name="funcoes")
public class Funcao{
	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="COD_FUNCAO")
	private Long id;
	@NotEmpty(message="O campo não pode estar vazio")
	@Length(max=40, message="A função tem que ter no máximo {max} caracteres")
	@Column(name="NOME_FUNCAO", nullable= false, length=40, unique=true)
	private String nome;
	@NotEmpty(message="O campo não pode estar vazio")
	@Column(name="DESCRICAO_FUNCAO", length=60)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="COD_OPM_ORGAO")
	private OpmOrgao opmOrgao;
	
	
		
	
	
	
}
