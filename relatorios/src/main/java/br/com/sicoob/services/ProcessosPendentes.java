package br.com.sicoob.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import br.com.sicoob.model.Execucao;
import br.com.sicoob.model.Job;
import br.com.sicoob.repository.ExecucaoRepository;
import br.com.sicoob.repository.JobRepository;
import lombok.extern.java.Log;

@Log
@Controller
public class ProcessosPendentes {

	@Autowired
	private JobRepository jobRep;

	@Autowired
	private ExecucaoRepository execRep;

	DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd-MM-YYYY");
	String dataCom[] = (dtf1.format(LocalDateTime.now())).split("-");
	int dia = Integer.parseInt(dataCom[0]);
	int mes = Integer.parseInt(dataCom[1]);
	int ano = Integer.parseInt(dataCom[2]);

	
	@Scheduled(cron = "0 0/30 * 1/1 * ?")
	public void atualizarProcessosPendentes() throws Exception {
		
		log.info("Executando atualizarProcessosPendentes()..." + this.queDiaUtile());

		List<Job> todosOsJobs = jobRep.findAll();
		for (Job job : todosOsJobs) {

			if (job.getTipo().equals("MENSAL")) {
				if ((job.getHorarioPrevisto().equals("1° DIA") && (this.queDiaUtile() >= 1 && this.queDiaUtile() <= 6))) {
					List<Execucao> exec = execRep.validaSeJobFoiExecutado(job.getId(), dataCom[1], dataCom[2]);
					boolean resul = false;
					for (Execucao e : exec) {
						resul = true;
					}
					log.info("Executando" + job.getId());
					if (!resul) {
						job.setPendenteExecucao(1);
						log.info("Alterando parametros ");
						jobRep.save(job);
					}
				}
				if ((job.getHorarioPrevisto().equals("2° DIA")) && (this.queDiaUtile() >= 2 && this.queDiaUtile() <= 7)) {
					List<Execucao> exec = execRep.validaSeJobFoiExecutado(job.getId(), dataCom[1], dataCom[2]);
					boolean resul = false;
					for (Execucao e : exec) {
						resul = true;
					}
					if (!resul) {
						job.setPendenteExecucao(1);
						jobRep.save(job);
					}
				}
				if ((job.getHorarioPrevisto().equals("3° DIA")) && (this.queDiaUtile() >= 3 && this.queDiaUtile() <= 8)) {
					List<Execucao> exec = execRep.validaSeJobFoiExecutado(job.getId(), dataCom[1], dataCom[2]);
					boolean resul = false;
					for (Execucao e : exec) {
						resul = true;
					}
					if (resul == false) {
						job.setPendenteExecucao(1);
						jobRep.save(job);
					}
				}

				if (job.getHorarioPrevisto().equals("5° DIA") && (this.queDiaUtile() >= 5 && this.queDiaUtile() <= 10)) {
					List<Execucao> exec = execRep.validaSeJobFoiExecutado(job.getId(), dataCom[1], dataCom[2]);
					boolean resul = false;
					for (Execucao e : exec) {
						resul = true;
					}
					if (resul == false) {
						job.setPendenteExecucao(1);
						jobRep.save(job);
					}
				}

				if (job.getHorarioPrevisto().equals("ULTIMO DIA") && (this.queDiaUtile() <= 5)) {

					String date[] = (LocalDate.of(ano, mes, dia).minusMonths(1)
							.format(DateTimeFormatter.ofPattern("MM-yyyy"))).split("-");

					List<Execucao> exec = execRep.validaSeJobFoiExecutado(job.getId(), date[0], date[1]);
					boolean resul = false;
					for (Execucao e : exec) {
						resul = true;
					}
					if (resul == false) {
						job.setPendenteExecucao(1);
						jobRep.save(job);
					}
				}
			}else {
				if (job.getTipo().equals("SEMESTRAL") && (this.queDiaUtile() <= 5) && (this.mes == 7 || this.mes == 1)) {
					List<Execucao> exec = execRep.validaSeJobFoiExecutado(job.getId(), dataCom[1], dataCom[2]);
					boolean resul = false;
					for (Execucao e : exec) {
						resul = true;
					}
					if (resul == false) {
						job.setPendenteExecucao(1);
						jobRep.save(job);
					}
				}
				if (job.getTipo().equals("ANUAL") && (this.queDiaUtile() <= 5) && (this.mes == 1)) {
					List<Execucao> exec = execRep.validaSeJobFoiExecutado(job.getId(), dataCom[1], dataCom[2]);
					boolean resul = false;
					for (Execucao e : exec) {
						resul = true;
					}
					if (resul == false) {
						job.setPendenteExecucao(1);
						jobRep.save(job);
					}
				}
			}

		}

	}
	public int queDiaUtile() throws Exception {
		YearMonth anoMes = YearMonth.of(ano, mes);
		int cont = 0;
		for (int aux = 1; aux <= dia; aux++) {
			LocalDate data = anoMes.atDay(aux);
			if (!data.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !data.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				cont++;
			}
		}
		return cont;
	}
}
