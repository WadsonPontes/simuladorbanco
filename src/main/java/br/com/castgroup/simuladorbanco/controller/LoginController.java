package br.com.castgroup.simuladorbanco.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.castgroup.simuladorbanco.enums.SituacaoEnum;
import br.com.castgroup.simuladorbanco.model.Usuario;
import br.com.castgroup.simuladorbanco.service.UsuarioService;

@Controller
public class LoginController {
	@Autowired
    private UsuarioService usuarioService;
	
	@GetMapping("/login")
    public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("usuario", new Usuario());
        return mav;
    }
	
	@PostMapping("/logar")
    public ModelAndView logar(Usuario usuario, RedirectAttributes redirectAttributes, HttpSession session) {
		SituacaoEnum situacao = usuarioService.logar(usuario, session);
		
		if (situacao == SituacaoEnum.SUCESSO) {
			redirectAttributes.addFlashAttribute("sucesso", situacao.getDescricao());
	        return new ModelAndView("redirect:/conta");
        }
		
		redirectAttributes.addFlashAttribute("erro", situacao.getDescricao());
	    return new ModelAndView("redirect:/login");
    }
	
	@GetMapping("/sair")
    public ModelAndView sair(HttpSession session) {
        session.removeAttribute("usuario");
        session.removeAttribute("conta");
        return new ModelAndView("redirect:/home");
    }
}
