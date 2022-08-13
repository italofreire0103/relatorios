package br.com.sicoob.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="resumomensal")
public class ResumoMensal {

	@Id
	private int cod;
	
	private String mes; 
	
	@Column(name="maiortempo")
	private String maiorTempo;
	
	@Column(name="tempomedioexecucao")
	private String tempoMedioExecucao;
	

	@ManyToOne
	@JoinColumn(name="Idjob")
	private Job job;
	
	public List<ResumoMensal> refatoradorDeData(List<ResumoMensal> resum){
		for(ResumoMensal item : resum) {
			String aux[] = item.getMes().split("-");
			item.setMes(aux[1]+"/"+aux[0]);
		}
		
		return resum;
	}
}
