package br.com.castgroup.simuladorbanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.castgroup.simuladorbanco.annotation.RequireAdmin;
import br.com.castgroup.simuladorbanco.model.Usuario;
import br.com.castgroup.simuladorbanco.model.Conta;
import br.com.castgroup.simuladorbanco.service.UsuarioService;
import br.com.castgroup.simuladorbanco.service.ContaService;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class AdminController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private ContaService contaService;
    
    @RequireAdmin
    @GetMapping("/admin")
    public ModelAndView listarUsuarios(HttpSession session) {
        ModelAndView mav = new ModelAndView("admin");
        
        List<Usuario> usuarios = usuarioService.listarTodos();
        
        mav.addObject("usuarios", usuarios);
        mav.addObject("usuarioLogado", session.getAttribute("usuario"));
        
        return mav;
    }
    
    @RequireAdmin
    @GetMapping("/usuario/{id}")
    public ModelAndView detalhesUsuario(@PathVariable Long id, HttpSession session) {
        ModelAndView mav = new ModelAndView("usuario");
        
        Usuario usuario = usuarioService.buscarPorId(id);
        
        if (usuario == null) {
            mav.setViewName("redirect:/admin");
            return mav;
        }
        
        // Calcular saldo total
        BigDecimal saldoTotal = usuario.getContas().stream()
            .map(Conta::getSaldo)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        mav.addObject("usuario", usuario);
        mav.addObject("saldoTotal", saldoTotal);
        mav.addObject("usuarioLogado", session.getAttribute("usuario"));
        
        return mav;
    }
    
    @RequireAdmin
    @PostMapping("/usuario/{id}/excluir")
    public ModelAndView excluirUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
        
        // Impedir que o admin exclua a si mesmo
        if (usuarioLogado.getId().equals(id)) {
            redirectAttributes.addFlashAttribute("erro", "Você não pode excluir sua própria conta");
            return new ModelAndView("redirect:/admin");
        }
        
        boolean excluido = usuarioService.excluir(id);
        
        if (excluido) {
            redirectAttributes.addFlashAttribute("sucesso", "Usuário excluído com sucesso");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir usuário");
        }
        
        return new ModelAndView("redirect:/admin");
    }
    
    @RequireAdmin
    @PostMapping("/usuario/{usuarioId}/conta/{contaId}/excluir")
    public ModelAndView excluirConta(@PathVariable Long usuarioId, @PathVariable Long contaId, RedirectAttributes redirectAttributes) {
        
        boolean excluido = contaService.excluir(contaId);
        
        if (excluido) {
            redirectAttributes.addFlashAttribute("sucesso", "Conta excluída com sucesso");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir conta");
        }
        
        return new ModelAndView("redirect:/usuario/" + usuarioId);
    }
}
