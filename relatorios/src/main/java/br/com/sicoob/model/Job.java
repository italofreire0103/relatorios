package br.com.sicoob.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.ManyToAny;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "job_emissor")
public class Job {
	
	@Id
	private String id;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String areaResponsavel;
	@NotEmpty
	private String tipo;
	@NotEmpty
	private String horarioPrevisto;
	private String dependencia;
	private int pendenteExecucao; 
	
	@OneToMany(mappedBy = "job")
	List<Execucao> execucoes;
	
	@OneToMany(mappedBy = "job")
	List<ResumoMensal> resumosMensais;
}
