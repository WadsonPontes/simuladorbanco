package br.com.castgroup.simuladorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.castgroup.simuladorbanco.model.Usuario;
import br.com.castgroup.simuladorbanco.service.UsuarioService;

@Controller
public class LoginController {
	@Autowired
    private UsuarioService usuarioService;
	
	@GetMapping("/login")
    public ModelAndView cadastro() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("usuario", new Usuario());
        return mav;
    }
}
