package br.com.sicoob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sicoob.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByLogin(String username);

	public Usuario findByNome(String username);
}
