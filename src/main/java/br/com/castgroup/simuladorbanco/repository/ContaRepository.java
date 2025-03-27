package br.com.castgroup.simuladorbanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.castgroup.simuladorbanco.model.Conta;
import jakarta.persistence.LockModeType;

public interface ContaRepository extends JpaRepository<Conta, Long> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
    Conta findByNumeroConta(String numeroConta);
}
