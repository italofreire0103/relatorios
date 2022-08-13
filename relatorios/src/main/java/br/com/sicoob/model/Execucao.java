package br.com.sicoob.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table
@ToString
public class Execucao {

	@Id
	private int cod;
	
	@Column(name = "datadiaria")
	@NotEmpty
	private String dataDiaria;

	@NotEmpty
	@Column(name = "dataexecucao")
	private String dataExecucao;

	@NotEmpty
	@Column(name = "horainicio")
	private String horaInicio;

	@NotEmpty
	@Column(name = "horafim")
	private String horaFim;

	@NotEmpty
	@Column(name = "tempodeproc")
	private String tempoDeProc;

	@NotEmpty
	@Column(name = "statusfinal")
	private String statusFinal;

	@ManyToOne
	@JoinColumn(name = "idjob")
	@NotEmpty
	private Job job;

}
