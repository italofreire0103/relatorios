package br.com.sicoob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.sicoob.dto.RequisicaoConsultarExec;
import br.com.sicoob.model.Execucao;
import br.com.sicoob.repository.ExecucaoRepository;

@Controller
public class ExecucaoController {

	@Autowired
	private ExecucaoRepository execRep;
	
	@RequestMapping(value = "/consultar-execucao", method = RequestMethod.GET)
	public String consultar ( RequisicaoConsultarExec req , Model model) {
		List<Execucao> execucoes;
		execucoes = execRep.consultarExecucao(req.getNomeJob(), req.getDataExec(), req.getHoraInicioExec(), req.getHoraFimExec()); 
		model.addAttribute("execucoes", execucoes);
		return "consultar-execucao";
	}
}
