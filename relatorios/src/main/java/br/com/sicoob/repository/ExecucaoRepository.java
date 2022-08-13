package br.com.sicoob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sicoob.model.Execucao;

public interface ExecucaoRepository extends JpaRepository<Execucao, Long> {

	@Query(value = "select * "
			+ "from Execucao "
			+ "where idJob like %:nome% "
			+ "and dataDiaria = :data "
			+ "and horaInicio >= :horaInicio "
			+ "and horaInicio <= :horaFim "
			+ "order by horaInicio ", nativeQuery = true)
	public List<Execucao> consultarExecucao( 
			@Param("nome") String nome, 
			@Param("data") String data,
			@Param("horaInicio") String horaInicio,
			@Param("horaFim") String horaFim);
	
	@Query(value = "select * "
			+ "from Execucao "
			+ "where idJob like %:idJob% "
			+ "and year(dataDiaria) = :ano "
			+ "and month(dataDiaria) = :mes ", nativeQuery = true)
	public List<Execucao> validaSeJobFoiExecutado(
			@Param("idJob") String idJob,
			@Param("mes") String mes,
			@Param("ano") String ano);
	
	
	@Query(value = "select * "
			+ "from execucao "
			+ "where idJob :idJob "
			+ "and dataDiaria = :dataDiaria "
			+ "and dataExecucao = :dataExecucao "
			+ "and horaInicio = :horaInicio "
			+ "and horaFim = :horaFim "
			+ "and statusFinal = :statusFinal ", nativeQuery = true)
	public Execucao consultarExecucao( 
			@Param("idJob") String idJob,
			@Param("dataDiaria") String dataDiaria,
			@Param("dataExecucao") String dataExecucao,
			@Param("horaInicio") String horaInicio,
			@Param("horaFim") String horaFim, 
			@Param("statusFinal") String statusFinal);
	
}
