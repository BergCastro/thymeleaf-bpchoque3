package br.com.fireware.bpchoque.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.fireware.bpchoque.entity.Cidade;
import br.com.fireware.bpchoque.service.CidadeService;

@Controller
@RequestMapping("/cidades")
public class CidadeController {
	
	private static final String CADASTRO_VIEW = "Cidades/CadastroCidade";
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping
	public ModelAndView cidades() {
		Iterable<Cidade> todosCidades = cidadeService.findAll();
		ModelAndView mv = new ModelAndView("/Cidades/Cidades");
		mv.addObject("cidades", todosCidades);
		
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Cidade());
		return mv;
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cidade cidade, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		cidade.setNome(cidade.getNome().toUpperCase());
		
		try {
			cidadeService.save(cidade);
			attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");
			return "redirect:/cidades/novo";
		} catch (IllegalArgumentException e) {
			
			return CADASTRO_VIEW;
		}
	}
	
	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Cidade cidade) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
		mv.addObject(cidade);
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String excluir(@PathVariable Long id) {
		cidadeService.delete(id);
		System.out.println("Entrou no delete");
		
		
		return "redirect:/cidades";
	}
	
	
	
	
	
}