package br.com.fireware.bpchoque.controller.def;



import java.time.LocalDate;
import java.util.Arrays;

import java.util.List;


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


import br.com.fireware.bpchoque.entity.Pessoa;
import br.com.fireware.bpchoque.entity.def.AvaliacaoIndividual;


import br.com.fireware.bpchoque.entity.def.AvaliacaoIndividual.Duracao;
import br.com.fireware.bpchoque.entity.def.AvaliacaoIndividual.Frequencia;
import br.com.fireware.bpchoque.entity.def.AvaliacaoIndividual.Objetivos;
import br.com.fireware.bpchoque.entity.def.AvaliacaoIndividual.Problemas;
import br.com.fireware.bpchoque.service.PessoaService;
import br.com.fireware.bpchoque.service.def.AvaliacaoIndividualService;

@Controller
@RequestMapping("/avaliacoesIndividuais")
public class AvaliacaoIndividualController {
	
	private static final String CADASTRO_AVALIACAO = "AvaliacoesIndividuais/CadastroAvaliacaoIndividual";
	
	
	@Autowired
	private AvaliacaoIndividualService avaliacaoIndividualService;
	
	@Autowired
	private PessoaService pessoaService;
	
	
	@RequestMapping
	public ModelAndView avaliacaoIndividuais() {
		Iterable<AvaliacaoIndividual> todosAvaliacaoIndividuais = avaliacaoIndividualService.findAll();
		ModelAndView mv = new ModelAndView("/AvaliacoesIndividuais/AvaliacoesIndividuais");
		mv.addObject("avaliacoesIndividuais", todosAvaliacaoIndividuais);
		
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		
		ModelAndView mv = new ModelAndView();
		
				
			mv.setViewName(CADASTRO_AVALIACAO);
			AvaliacaoIndividual avaliacao = new AvaliacaoIndividual();
			avaliacao.setDataAvaliacao(LocalDate.now());
			mv.addObject(avaliacao);
			return mv;

		
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated AvaliacaoIndividual avaliacaoIndividual, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_AVALIACAO;
		}
		
		
		try {
			avaliacaoIndividualService.save(avaliacaoIndividual);
			attributes.addFlashAttribute("mensagem", "Avaliacao Individual salva com sucesso!");
			return "redirect:/avaliacoesIndividuais";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_AVALIACAO;
		}
	}
	
	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") AvaliacaoIndividual avaliacaoIndividual) {
		ModelAndView mv = new ModelAndView(CADASTRO_AVALIACAO); 
		mv.addObject(avaliacaoIndividual);
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String excluir(@PathVariable Long id) {
		avaliacaoIndividualService.delete(id);
		System.out.println("Entrou no delete");
		
		
		return "redirect:/avaliacaoIndividuais";
	}
	
	@ModelAttribute("frequencias")
	public List<Frequencia> frequencias() {
		return Arrays.asList(Frequencia.values());
	}
	
	@ModelAttribute("duracoes")
	public List<Duracao> duracoes() {
		return Arrays.asList(Duracao.values());
	}
	
	@ModelAttribute("objetivos")
	public List<Objetivos> objetivos() {
		return Arrays.asList(Objetivos.values());
	}
	
	@ModelAttribute("problemas")
	public List<Problemas> problemas() {
		return Arrays.asList(Problemas.values());
	}
	
	@ModelAttribute("pessoas")
	public Iterable<Pessoa> pessoas() {
		return pessoaService.findAll();
	}
	
	
	
}