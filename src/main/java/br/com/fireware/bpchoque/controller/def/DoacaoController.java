package br.com.fireware.bpchoque.controller.def;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.com.fireware.bpchoque.entity.def.Doacao;
import br.com.fireware.bpchoque.entity.def.Doacao.DoacaoTipo;
import br.com.fireware.bpchoque.entity.def.DoacaoDetalhe;
import br.com.fireware.bpchoque.service.def.DoacaoDetalheService;
import br.com.fireware.bpchoque.service.def.DoacaoService;

@Controller
@RequestMapping("/doacoes")
public class DoacaoController {
	
	private static final String CADASTRO_OBJETO = "Doacoes/CadastroDoacaoObjeto";
	private static final String CADASTRO_VALOR = "Doacoes/CadastroDoacaoValor";
	
	@Autowired
	private DoacaoService doacaoService;
	
	@Autowired
	private DoacaoDetalheService doacaoDetalheService;
	
	
	@RequestMapping
	public ModelAndView doacoes() {
		Iterable<Doacao> todosDoacoes = doacaoService.findAll();
		ModelAndView mv = new ModelAndView("/Doacoes/Doacoes");
		mv.addObject("doacoes", todosDoacoes);
		
		return mv;
	}
	
	@RequestMapping("/lista")
	public List<Doacao> listaDoacoes() {
		List<Doacao> todosDoacoes = doacaoService.findAll();
		
		return todosDoacoes;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(@RequestParam(required=false) String tipo) {
		
		ModelAndView mv = new ModelAndView();
		
			
			if(tipo.equals("objeto")){
			mv.setViewName(CADASTRO_OBJETO);
			
			mv.addObject(new Doacao());
			return mv;
			}else{
				
				mv.setViewName(CADASTRO_VALOR);
				mv.addObject(new Doacao());
				return mv;
			}
		
		
	}
	
	
	
	@RequestMapping(value="/objeto" ,method = RequestMethod.POST)
	public String salvarObjeto( @Validated Doacao doacao, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_OBJETO;
		}
		
	/*	Locale BRAZIL = new Locale("pt", "BR");
		LocalDate dataNascimento = LocalDate.parse(data.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(BRAZIL));
		doacao.setDatanasc(dataNascimento);*/
		
			doacao.setDoador(doacao.getDoador().toUpperCase());
			doacao.setTipo(DoacaoTipo.OBJETO);
		
		try {
			doacaoService.save(doacao);
			attributes.addFlashAttribute("mensagem", "Doação salva com sucesso!");
			
			return "redirect:/doacoes";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_OBJETO;
		}
	}
	
	@RequestMapping(value="/objeto/detalhe", method = RequestMethod.POST)
	public String salvarObjetoDetalhe( @Validated DoacaoDetalhe doacaoDetalhe, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_OBJETO;
		}
		
	
		try {
			doacaoDetalheService.save(doacaoDetalhe);
			attributes.addFlashAttribute("mensagem", "Doação salva com sucesso!");
			return "redirect:/doacoes/objeto";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_OBJETO;
		}
	}
	
	@RequestMapping(value="/valor", method = RequestMethod.POST)
	public String salvarValor(@Validated Doacao doacao, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VALOR;
		}
		
	/*	Locale BRAZIL = new Locale("pt", "BR");
		LocalDate dataNascimento = LocalDate.parse(data.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(BRAZIL));
		doacao.setDatanasc(dataNascimento);*/
		
			
		
			doacao.setDoador(doacao.getDoador().toUpperCase());
			doacao.setTipo(DoacaoTipo.VALOR);
			
		try {
			doacaoService.save(doacao);
			attributes.addFlashAttribute("mensagem", "Doação salva com sucesso!");
			return "redirect:/doacoes";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VALOR;
		}
	}
	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Doacao doacao) {
		
		ModelAndView mv = new ModelAndView();
		
		if(doacao.getTipo().toString().equals("OBJETO")){
			mv.setViewName(CADASTRO_OBJETO);
		}else{
			mv.setViewName(CADASTRO_VALOR);
		}
		mv.addObject(doacao);
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String excluir(@PathVariable Long id) {
		doacaoService.delete(id);
		
		
		
		return "redirect:/doacoes";
	}
	
	
	
	
	
	
}