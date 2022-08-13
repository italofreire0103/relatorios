package br.com.sicoob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sicoob.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

	@Query(value = "select j from Job j where pendenteExecucao =1")
	public List<Job> recuperaTodosPendentesExec();

	@Query(value = "select * from job_emissor where id =:id", nativeQuery = true)
	public Job procurarPorId(@Param("id") String id);

	
}
