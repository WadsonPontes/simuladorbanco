package br.com.castgroup.simuladorbanco.controller;

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
public class CadastroController {
	@Autowired
    private UsuarioService usuarioService;
	
	@GetMapping("/cadastro")
    public ModelAndView cadastro() {
		ModelAndView mav = new ModelAndView("cadastro");
		mav.addObject("usuario", new Usuario());
        return mav;
    }
	
	@PostMapping("/cadastrar")
    public ModelAndView cadastrar(Usuario usuario, RedirectAttributes redirectAttributes) {
		SituacaoEnum situacao = usuarioService.criar(usuario);
		
		if (situacao == SituacaoEnum.SUCESSO) {
			redirectAttributes.addFlashAttribute("sucesso", situacao.getDescricao());
	        return new ModelAndView("redirect:/login");
        }
		
		redirectAttributes.addFlashAttribute("erro", situacao.getDescricao());
	    return new ModelAndView("redirect:/cadastro");
    }
}
