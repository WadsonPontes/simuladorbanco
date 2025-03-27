package br.com.castgroup.simuladorbanco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.castgroup.simuladorbanco.model.Usuario;

@Controller
public class ClienteController {
	@GetMapping("/cliente")
    public ModelAndView cliente() {
		ModelAndView mav = new ModelAndView("cliente");
		mav.addObject("usuario", new Usuario());
        return mav;
    }
}
