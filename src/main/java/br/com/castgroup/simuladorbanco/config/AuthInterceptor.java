package br.com.castgroup.simuladorbanco.config;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import br.com.castgroup.simuladorbanco.annotation.RequireAdmin;
import br.com.castgroup.simuladorbanco.annotation.RequireLogin;
import br.com.castgroup.simuladorbanco.enums.TipoUsuarioEnum;
import br.com.castgroup.simuladorbanco.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        
        String uri = request.getRequestURI();
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        
        // Verifica se o método requer ADMIN
        RequireAdmin requireAdmin = handlerMethod.getMethodAnnotation(RequireAdmin.class);
        
        // Verifica se o método requer LOGIN
        RequireLogin requireLogin = handlerMethod.getMethodAnnotation(RequireLogin.class);
        
        HttpSession session = request.getSession(false);
        Usuario usuario = null;
        
        if (session != null) {
            usuario = (Usuario) session.getAttribute("usuario");
        }
        
        // === REGRA 1: Redirecionar usuários logados que tentam acessar login/cadastro ===
        if (usuario != null && (uri.equals("/") || uri.equals("/login") || uri.equals("/cadastro"))) {
            response.sendRedirect("/conta");
            return false;
        }
        
        // === REGRA 2: Proteção de rotas ADMIN ===
        if (requireAdmin != null) {
            // Verifica se está logado
            if (usuario == null) {
                response.sendRedirect("/");
                return false;
            }
            
            // Verifica se é ADMIN
            if (usuario.getTipo() == null || !usuario.getTipo().getId().equals(TipoUsuarioEnum.ADMIN.getId())) {
                response.sendRedirect("/conta");
                return false;
            }
        }
        
        // === REGRA 3: Proteção de rotas que requerem apenas LOGIN ===
        if (requireLogin != null && requireAdmin == null) {
            if (usuario == null) {
                response.sendRedirect("/");
                return false;
            }
        }
        
        return true;
    }
}
