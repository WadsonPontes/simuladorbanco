package br.com.castgroup.simuladorbanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.castgroup.simuladorbanco.model.TipoTransacao;

public interface TipoTransacaoRepository extends JpaRepository<TipoTransacao, Integer> {

}
