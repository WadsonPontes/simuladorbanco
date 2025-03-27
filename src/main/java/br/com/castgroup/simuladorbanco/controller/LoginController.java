package br.com.castgroup.simuladorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.castgroup.simuladorbanco.enums.SituacaoUsuarioEnum;
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
    public ModelAndView logar(Usuario usuario, RedirectAttributes redirectAttributes) {
		SituacaoUsuarioEnum situacao = usuarioService.logar(usuario);
		
		if (situacao == SituacaoUsuarioEnum.SUCESSO) {
			redirectAttributes.addFlashAttribute("sucesso", situacao.getDescricao());
	        return new ModelAndView("redirect:/dashboard");
        }
		
		redirectAttributes.addFlashAttribute("erro", situacao.getDescricao());
	    return new ModelAndView("redirect:/login");
    }
}
