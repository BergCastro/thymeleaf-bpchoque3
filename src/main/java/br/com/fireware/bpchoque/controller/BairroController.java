package br.com.fireware.bpchoque.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.fireware.bpchoque.entity.Bairro;

import br.com.fireware.bpchoque.entity.Cidade;

import br.com.fireware.bpchoque.service.BairroService;
import br.com.fireware.bpchoque.service.CidadeService;

@Controller
@RequestMapping("/bairros")
public class BairroController {

	private static final String CADASTRO_VIEW = "Bairros/CadastroBairro";

	@Autowired
	private BairroService bairroService;

	@Autowired
	private CidadeService cidadeService;

	@RequestMapping
	public ModelAndView bairros() {
		Iterable<Bairro> todosBairros = bairroService.findAll();
		ModelAndView mv = new ModelAndView("/Bairros/Bairros");
		mv.addObject("bairros", todosBairros);

		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Bairro());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Bairro bairro, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		bairro.setNome(bairro.getNome().toUpperCase());
		try {
			bairroService.save(bairro);
			attributes.addFlashAttribute("mensagem", "Bairro salvo com sucesso!");
			return "redirect:/bairros/novo";
		} catch (IllegalArgumentException e) {

			return CADASTRO_VIEW;
		}
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Bairro bairro) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(bairro);
		return mv;
	}

	@RequestMapping(value = "/delete/{id}")
	public String excluir(@PathVariable Long id) {
		bairroService.delete(id);
		System.out.println("Entrou no delete");

		return "redirect:/bairros";
	}

	@ModelAttribute("cidades")
	public Iterable<Cidade> cidades() {
		return cidadeService.findAll();
	}

}