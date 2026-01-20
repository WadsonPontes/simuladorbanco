package br.com.castgroup.simuladorbanco.config;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.castgroup.simuladorbanco.annotation.PublicRoute;
import br.com.castgroup.simuladorbanco.annotation.RequireAdmin;
import br.com.castgroup.simuladorbanco.annotation.RequireCliente;
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
        
        boolean isAdminRoute = handlerMethod.getMethodAnnotation(RequireAdmin.class) != null;
        boolean isClienteRoute = handlerMethod.getMethodAnnotation(RequireCliente.class) != null;
        boolean isLoginRoute = handlerMethod.getMethodAnnotation(RequireLogin.class) != null;
        boolean isPublicRoute = handlerMethod.getMethodAnnotation(PublicRoute.class) != null;
        
        HttpSession session = request.getSession(false);
        Usuario usuario = null;
        
        if (session == null) {
        	if (!isPublicRoute) {
        		response.sendRedirect("/");
                return false;
        	}
        	
        	return true;
        }
        
    	usuario = (Usuario) session.getAttribute("usuario");
    	
    	if (usuario == null) {
        	if (!isPublicRoute) {
        		response.sendRedirect("/");
                return false;
        	}
        	
        	return true;
        }
    	
		if (isPublicRoute) {
    		if (usuario.isAdmin()) {
    			response.sendRedirect("/admin");
                return false;
    		}
    		
    		response.sendRedirect("/conta");
            return false;
    	}
    	
    	if (isClienteRoute && usuario.isAdmin()) {
			response.sendRedirect("/admin");
            return false;
		}
    	
    	if (isAdminRoute && usuario.isCliente()) {
			response.sendRedirect("/conta");
            return false;
    	}
        
        return true;
    }
}
