package br.com.castgroup.simuladorbanco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.castgroup.simuladorbanco.model.Conta;
import br.com.castgroup.simuladorbanco.model.Usuario;

@Controller
public class ContaController {
	@GetMapping("/conta")
    public ModelAndView conta() {
        return new ModelAndView("redirect:/conta/credito");
    }
	
	@GetMapping("/conta/credito")
    public ModelAndView credito() {
		ModelAndView mav = new ModelAndView("conta");
		mav.addObject("valor", "");
		mav.addObject("pagina", "credito");
		mav.addObject("usuario", new Usuario());
        return mav;
    }
	
	@GetMapping("/conta/debito")
    public ModelAndView debito() {
		ModelAndView mav = new ModelAndView("conta");
		mav.addObject("valor", "");
		mav.addObject("pagina", "debito");
		mav.addObject("usuario", new Usuario());
        return mav;
    }
	
	@GetMapping("/conta/transferencia")
    public ModelAndView transferencia() {
		ModelAndView mav = new ModelAndView("conta");
		mav.addObject("destino", new Conta());
		mav.addObject("valor", "");
		mav.addObject("pagina", "transferencia");
		mav.addObject("usuario", new Usuario());
        return mav;
    }
}
