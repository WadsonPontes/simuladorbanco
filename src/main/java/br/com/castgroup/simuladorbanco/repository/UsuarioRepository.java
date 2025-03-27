package br.com.castgroup.simuladorbanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.castgroup.simuladorbanco.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	boolean existsByEmail(String email);
}
