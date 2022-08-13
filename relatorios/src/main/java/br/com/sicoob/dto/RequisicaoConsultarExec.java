package br.com.sicoob.dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
public class RequisicaoConsultarExec {
	
	private String nomeJob;
	private String dataExec;
	private String horaInicioExec;
	private String horaFimExec;
	}
