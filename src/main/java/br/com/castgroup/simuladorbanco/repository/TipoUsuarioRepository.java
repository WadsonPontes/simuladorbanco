package br.com.castgroup.simuladorbanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.castgroup.simuladorbanco.model.TipoUsuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {

}
