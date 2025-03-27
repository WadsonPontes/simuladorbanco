package br.com.castgroup.simuladorbanco.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.castgroup.simuladorbanco.enums.SituacaoEnum;
import br.com.castgroup.simuladorbanco.model.Conta;
import br.com.castgroup.simuladorbanco.repository.ContaRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class ContaService {
	@Autowired
    private ContaRepository contaRepository;
	
	@Transactional
	public SituacaoEnum creditar(BigDecimal valor, HttpSession session) {
		Conta conta = (Conta) session.getAttribute("conta");
		
		if (valor.compareTo(BigDecimal.ZERO) <= 0) {
			return SituacaoEnum.ERRO_NEGATIVO;
		}
		
		conta.creditar(valor);
		contaRepository.save(conta);
		
		session.setAttribute("conta", conta);
		
		return SituacaoEnum.SUCESSO_CREDITO;
	}
	
	@Transactional
	public SituacaoEnum debitar(BigDecimal valor, HttpSession session) {
		Conta contaSession = (Conta) session.getAttribute("conta");
		Conta conta = contaRepository.findByNumeroConta(contaSession.getNumeroConta());
		
		if (valor.compareTo(BigDecimal.ZERO) <= 0) {
			return SituacaoEnum.ERRO_NEGATIVO;
		}
		else if (conta.getSaldo().compareTo(valor) < 0) {
			return SituacaoEnum.ERRO_SALDO;
		}
		
		conta.debitar(valor);
		contaRepository.save(conta);
		
		session.setAttribute("conta", conta);
		
		return SituacaoEnum.SUCESSO_DEBITO;
	}
}
