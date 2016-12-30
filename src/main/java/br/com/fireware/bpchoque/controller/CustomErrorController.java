package br.com.fireware.bpchoque.controller;




import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CustomErrorController{
	
	
	@RequestMapping("/acessoNegado")
	public ModelAndView acessoNegado() {
		ModelAndView mv = new ModelAndView("acessoNegado");
		
		
		return mv;
	}
   

}