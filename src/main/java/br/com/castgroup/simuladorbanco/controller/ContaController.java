package br.com.castgroup.simuladorbanco.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.castgroup.simuladorbanco.enums.SituacaoEnum;
import br.com.castgroup.simuladorbanco.model.Conta;
import br.com.castgroup.simuladorbanco.model.Usuario;
import br.com.castgroup.simuladorbanco.service.ContaService;
import br.com.castgroup.simuladorbanco.service.UsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
public class ContaController {
	@Autowired
    private ContaService contaService;
	
	@GetMapping("/conta")
    public ModelAndView conta() {
        return new ModelAndView("redirect:/conta/credito");
    }
	
	@GetMapping("/conta/credito")
    public ModelAndView credito(HttpSession session) {
		ModelAndView mav = new ModelAndView("conta");
		mav.addObject("destino", new Conta());
		mav.addObject("pagina", "credito");
		mav.addObject("usuario", session.getAttribute("usuario"));
		mav.addObject("conta", session.getAttribute("conta"));
        return mav;
    }
	
	@GetMapping("/conta/debito")
    public ModelAndView debito(HttpSession session) {
		ModelAndView mav = new ModelAndView("conta");
		mav.addObject("destino", new Conta());
		mav.addObject("pagina", "debito");
		mav.addObject("usuario", session.getAttribute("usuario"));
		mav.addObject("conta", session.getAttribute("conta"));
        return mav;
    }
	
	@GetMapping("/conta/transferencia")
    public ModelAndView transferencia(HttpSession session) {
		ModelAndView mav = new ModelAndView("conta");
		mav.addObject("destino", new Conta());
		mav.addObject("pagina", "transferencia");
		mav.addObject("usuario", session.getAttribute("usuario"));
		mav.addObject("conta", session.getAttribute("conta"));
        return mav;
    }
	
	@PostMapping("/conta/creditar")
    public ModelAndView creditar(Conta destino, RedirectAttributes redirectAttributes, HttpSession session) {
		SituacaoEnum situacao = contaService.creditar(destino.getSaldo(), session);
		
		if (situacao == SituacaoEnum.SUCESSO_CREDITO) {
			redirectAttributes.addFlashAttribute("sucesso", situacao.getDescricao());
	        return new ModelAndView("redirect:/conta/credito");
        }
		
		redirectAttributes.addFlashAttribute("erro", situacao.getDescricao());
	    return new ModelAndView("redirect:/conta/credito");
    }
}
