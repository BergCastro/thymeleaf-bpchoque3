package br.com.fireware.bpchoque.entity;

import java.time.LocalDate;


import javax.persistence.*;



import lombok.Data;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private LocalDate dataCadastro;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Perfil perfil;

	
	

	
}

