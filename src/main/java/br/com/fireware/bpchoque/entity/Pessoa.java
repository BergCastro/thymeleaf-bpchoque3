/**
 PESSOA(COD_PESSOA, NOME_PESSOA, LOGRADOURO_PESSOA, NUMERO_END_PESSOA,
  COMPLEMENTO_END_PESSOA, COD_CIDADE, 
 COD_BAIRRO, COD_UF, CEP_PESSOA, DATA_NASC_PESSOA,
  EMAIL_PESSOA, CPF_PESSOA, COD_ESTADO_CIVIL, COD_SEXO)
 **/

package br.com.fireware.bpchoque.entity;


import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Table(name = "pessoas")
@Entity
											
public class Pessoa{

	

	public enum EstadoCivil {
		CASADO("Casado"),
		SOLTEIRO("Solteiro");
		
		private String descricao;
		
		EstadoCivil(String descricao) {
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return descricao;
		}
	}
	
	
	public enum TipoPessoa {

		MILITAR("Militar"),
		CIVIL("Civil");
		
		private String descricao;
		
		TipoPessoa(String descricao) {
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return descricao;
		}
		
	}


	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "COD_PESSOA")
	private Long id;
	@NotEmpty(message = "O nome não pode estar vazio!")
	@Length(max = 60, message = "O nome tem que ter no máximo {max} caracteres")
	@Column(name = "NOME_PESSOA", nullable = false, length = 60)
	private String nome;

	@NotNull(message = "Selecione uma opção!")
	@Enumerated(EnumType.STRING)
	@Column(name = "SEXO")
	private Sexo sexo;

	//@NotEmpty(message = "O campo não pode está vazio")
	//@Length(max = 30, message = "O logradouro tem que ter no máximo {max} caracteres")
	@Column(name = "LOGRADOURO_ENDERECO", nullable = false, length = 30, columnDefinition="VARCHAR(30) DEFAULT 'RUA FALTANDO'")
	private String logradouro;

	//@NotEmpty(message = "O campo não pode está vazio")
	//@Length(max = 6, message = "O número tem que ter no máximo {max} caracteres")
	@Column(name = "NUMERO_ENDERECO", length = 6, columnDefinition="VARCHAR(6) DEFAULT 'FALTA'")
	private String numero;

	
	//@Length(max = 30, message = "O complemento tem que ter no máximo {max} caracteres")
	@Column(name = "COMPLEMENTO_ENDERECO", length = 30)
	private String complemento;

	//@NotNull(message = "Selecione uma opção!")
	@ManyToOne(optional=true)
	@JoinColumn(name = "COD_BAIRRO", columnDefinition="BIGINT(20) DEFAULT '1'")
	private Bairro bairro;
	
	
	//@Length(max = 9, min = 9,  message = "O CEP deve ter {max} caracteres")
	@Column(name = "CEP_PESSOA", length = 9, columnDefinition="VARCHAR(14) DEFAULT '60000000'")
	private String cep;
	
	@Length(max = 14,  message = "Celular inválido!")
	@Column(name = "TELEFONE1_PESSOA", length = 14, columnDefinition="VARCHAR(14) DEFAULT 'CEL. FALTANDO'")
	private String telefone1;
	
	

	@Length(max = 14,  message = "Celular inválido!")
	@Column(name = "TELEFONE2_PESSOA", length = 14)
	private String telefone2;
	

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "DATA_NAS_PESSOA", columnDefinition="DATE")
	private LocalDate datanasc;

	
	@Email(message = "Insira um email válido!")
	@Length(max = 40, message = "O e-mail tem que ter no máximo {max} caracteres")
	@Column(name = "EMAIL_PESSOA", length = 40)
	private String email;

	
	
	//@CPF(message = "Insira um CPF válido!")
	@Column(name = "CPF_PESSOA", length = 14)
	private String cpf;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_CIVIL")
	private EstadoCivil estado_civil;

	@Lob
	@Column(name = "FOTO_PESSOA")
	private Byte[] foto;
	
	@Enumerated(EnumType.STRING)
	@Column(name= "PESSOA_TIPO")
	private TipoPessoa tipo;

	
	@NotEmpty(message = "A matrícula não pode estar vazio!")
	//@Digits(integer = 9, fraction = 0, message = "Digite apenas números neste campo!")
	//@Length(max = 10, min = 10, message = "A matrícula tem que ter {max} caracteres")
	@Column(name = "MATRICULA_MILITAR", nullable = false, length = 15, unique = true)
	private String matricula;

	//@NotNull(message = "Selecione uma opção!")
	@ManyToOne(optional = true)
	@JoinColumn(name = "COD_CARGO")
	private Cargo cargo;

	//@NotEmpty(message = "O campo não pode estar vazio!")
	//@Digits(integer = 6, fraction = 0, message = "Digite apenas números neste campo!")
	//@Length(max = 6, message = "O número tem que ter no máximo {max} caracteres")
	@Column(name = "NUMERO_PM_MILITAR", length = 6)
	private String numero_pm;

	//@NotEmpty(message = "O campo não pode estar vazio!")
	//@Length(max = 20, message = "O campo tem que ter no máximo {max} caracteres")
	@Column(name = "NOME_GUERRA_MILITAR", length = 20)
	private String nome_guerra;

	//@NotNull(message = "Selecione uma opção!")
	@ManyToOne(optional=true)
	@JoinColumn(name = "COD_GRUPO")
	private Grupo grupo;
	
	//@NotNull(message = "Selecione uma opção!")
	@ManyToOne()
	@JoinColumn(name = "COD_OPM")
	private OpmOrgao opmOrgao;
	
	
	
	
	
	
	
	public static int idade(final LocalDate aniversario) {
	    final LocalDate dataAtual = LocalDate.now();
	    final Period periodo = Period.between(aniversario, dataAtual);
	    return periodo.getYears();
	}
	
	public static int idadeAvaliacao(final LocalDate aniversario) {
	    final int anoAtual = LocalDate.now().getYear();
	    final int anoAniversario = aniversario.getYear();
	    final int resultado = anoAtual-anoAniversario; 
	    
	    return resultado;
	}
	
	
	

}
