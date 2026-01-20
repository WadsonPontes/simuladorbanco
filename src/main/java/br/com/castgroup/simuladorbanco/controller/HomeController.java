package br.com.castgroup.simuladorbanco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.castgroup.simuladorbanco.annotation.PublicRoute;

@Controller
public class HomeController {
	@PublicRoute
    @GetMapping("")
    public ModelAndView index() {
        return new ModelAndView("home");
    }
}
