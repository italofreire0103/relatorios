package br.com.sicoob.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicoob.model.Job;
import br.com.sicoob.model.ResumoMensal;
import br.com.sicoob.repository.JobRepository;

@Service
public class JobServices {
	
	@Autowired
	private JobRepository jobRep;

	public Job consultarJob(String id) {
		Job job = jobRep.procurarPorId(id);
		ResumoMensal res = new ResumoMensal();
		job.setResumosMensais(res.refatoradorDeData(job.getResumosMensais()));
		return job;
	}

	public List<Job>consultarJobs() {
		List<Job> jobs = jobRep.findAll();	
		return jobs;
	}

	public List<Job> consultarProcessosPendentes() {
		List<Job> jobs = jobRep.recuperaTodosPendentesExec();
		return jobs;
	}

	public void deletarJob(Job job) {
		jobRep.delete(job);
	}
	
	
}
