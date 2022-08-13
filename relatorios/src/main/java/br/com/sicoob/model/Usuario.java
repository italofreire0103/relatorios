package br.com.sicoob.model;

	import lombok.AllArgsConstructor;
	import lombok.Builder;
	import lombok.Data;
	import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
	import javax.validation.constraints.NotEmpty;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	@Entity
	@ToString
	public class Usuario {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    @NotEmpty()
	    private String nome;
	    private String login;
	    @NotEmpty()
	    private String senha;
	    private boolean admin;
	}
