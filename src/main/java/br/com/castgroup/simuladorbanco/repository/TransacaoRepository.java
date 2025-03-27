package br.com.castgroup.simuladorbanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.castgroup.simuladorbanco.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
