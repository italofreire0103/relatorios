package br.com.sicoob.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.sicoob.dto.RequisicaoConsultarRelatorio;
import br.com.sicoob.model.Job;
import br.com.sicoob.repository.JobRepository;
import br.com.sicoob.services.JobServices;

@Controller
public class JobController {

	@Autowired
	private JobRepository jobRep;
	@Autowired
	private JobServices jobSer;


	@GetMapping("/consultar-jobs")
	public String consultar(Model model) {
		List<Job> jobs = jobSer.consultarJobs();
		model.addAttribute("jobs", jobs);
		return "todos-jobs";
		}

	@GetMapping("/consultar-relatorio-job")
	public String consultarRelatorioJob(RequisicaoConsultarRelatorio con, Model model) {
		model.addAttribute("job", jobSer.consultarJob(con.getIdJob()));
		return "consultar-relatorio";
	}

	@GetMapping("/processosPendentes")
	public String recuperaTodosPendentesExec(Model model) throws Exception {
		
		model.addAttribute("jobs", jobSer.consultarProcessosPendentes());
		return "monitorar";
	}

	@GetMapping("/cadastrarJob")
	public String cadastrarJob(Model model) {
		return "teste";
	}
	
	@GetMapping("/excluirJob")
	public String excluirJob(Job job, Model model) {
		jobSer.deletarJob(job);
		return "deletar";
	}
	
	@GetMapping("/alterarJob")
	public String alterarJob(Model model) {
		Job jobNull = new Job();
		model.addAttribute("job", jobNull);
		return "alterar";
	}
	
	@GetMapping("/atualizar-job")
	public String atualizarJob(@Valid Job job, Model model) {
		jobRep.save(job);
		Job jobNull = new Job();
		model.addAttribute("job", jobNull);
		return "alterar";
	}
}
