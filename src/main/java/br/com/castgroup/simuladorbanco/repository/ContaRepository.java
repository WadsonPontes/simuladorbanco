package br.com.castgroup.simuladorbanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.castgroup.simuladorbanco.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
