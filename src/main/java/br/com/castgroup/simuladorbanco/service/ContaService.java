package br.com.castgroup.simuladorbanco.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.castgroup.simuladorbanco.enums.SituacaoEnum;
import br.com.castgroup.simuladorbanco.model.Conta;
import br.com.castgroup.simuladorbanco.repository.ContaRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class ContaService {
	@Autowired
    private ContaRepository contaRepository;
	
	public SituacaoEnum creditar(BigDecimal valor, HttpSession session) {
		if (valor.compareTo(BigDecimal.ZERO) <= 0) {
			return SituacaoEnum.ERRO_NEGATIVO;
		}
		
		Conta conta = (Conta) session.getAttribute("conta");
		
		conta.creditar(valor);
		contaRepository.save(conta);
		
		session.setAttribute("conta", conta);
		
		return SituacaoEnum.SUCESSO_CREDITO;
	}
}
