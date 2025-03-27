package br.com.castgroup.simuladorbanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.castgroup.simuladorbanco.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);
	boolean existsByEmail(String email);
}
