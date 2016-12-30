package br.com.fireware.bpchoque.controller;



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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fireware.bpchoque.entity.Bairro;
import br.com.fireware.bpchoque.entity.Cargo;
import br.com.fireware.bpchoque.entity.Cidade;
import br.com.fireware.bpchoque.entity.OpmOrgao;
import br.com.fireware.bpchoque.entity.Pessoa;
import br.com.fireware.bpchoque.entity.Pessoa.EstadoCivil;
import br.com.fireware.bpchoque.entity.Pessoa.TipoPessoa;
import br.com.fireware.bpchoque.entity.Sexo;

import br.com.fireware.bpchoque.service.BairroService;
import br.com.fireware.bpchoque.service.CargoService;
import br.com.fireware.bpchoque.service.CidadeService;
import br.com.fireware.bpchoque.service.OpmOrgaoService;
import br.com.fireware.bpchoque.service.PessoaService;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {
	
	private static final String CADASTRO_MILITAR = "Pessoas/CadastroMilitar";
	private static final String CADASTRO_CIVIL = "Pessoas/CadastroCivil";
	
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private CargoService cargoService;
	@Autowired
	private OpmOrgaoService opmOrgaoService;
	@Autowired
	private CidadeService cidadeService;
	@Autowired
	private BairroService bairroService;
	
	
	@RequestMapping
	public ModelAndView pessoas() {
		Iterable<Pessoa> todosPessoas = pessoaService.findAll();
		ModelAndView mv = new ModelAndView("/Pessoas/Pessoas");
		mv.addObject("pessoas", todosPessoas);
		
		return mv;
	}
	
	@RequestMapping("/lista")
	public List<Pessoa> listaPessoas() {
		List<Pessoa> todosPessoas = pessoaService.findAll();
		
		return todosPessoas;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(@RequestParam(required=false) String tipo) {
		
		ModelAndView mv = new ModelAndView();
		
			
			if(tipo.equals("militar")){
			mv.setViewName(CADASTRO_MILITAR);
			
			mv.addObject(new Pessoa());
			return mv;
			}else{
				System.out.println("Entrou no civil");
				mv.setViewName(CADASTRO_CIVIL);
				mv.addObject(new Pessoa());
				return mv;
			}
		
		
	}
	
	
	
	@RequestMapping(value="/militar" ,method = RequestMethod.POST)
	public String salvarMilitar( @Validated Pessoa pessoa, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_MILITAR;
		}
		
	/*	Locale BRAZIL = new Locale("pt", "BR");
		LocalDate dataNascimento = LocalDate.parse(data.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(BRAZIL));
		pessoa.setDatanasc(dataNascimento);*/
		
			pessoa.setNome(pessoa.getNome().toUpperCase());
			pessoa.setLogradouro(pessoa.getLogradouro().toUpperCase());
			pessoa.setComplemento(pessoa.getComplemento().toUpperCase());
			pessoa.setNome_guerra(pessoa.getNome_guerra().toUpperCase());
			pessoa.setEmail(pessoa.getEmail().toLowerCase());
			pessoa.setTipo(TipoPessoa.MILITAR);
			
			System.out.println("Entrou no militar");
		
		try {
			pessoaService.save(pessoa);
			attributes.addFlashAttribute("mensagem", "Pessoa salva com sucesso!");
			return "redirect:/pessoas";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_MILITAR;
		}
	}
	
	@RequestMapping(value="/civil", method = RequestMethod.POST)
	public String salvarCivil(@Validated Pessoa pessoa, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_MILITAR;
		}
		
	/*	Locale BRAZIL = new Locale("pt", "BR");
		LocalDate dataNascimento = LocalDate.parse(data.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(BRAZIL));
		pessoa.setDatanasc(dataNascimento);*/
		
			
			System.out.println("Entrou no civil");
			pessoa.setNome(pessoa.getNome().toUpperCase());
			pessoa.setTipo(TipoPessoa.CIVIL);
			
		try {
			pessoaService.save(pessoa);
			attributes.addFlashAttribute("mensagem", "Pessoa salva com sucesso!");
			return "redirect:/pessoas";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_CIVIL;
		}
	}
	
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Pessoa pessoa) {
		
		ModelAndView mv = new ModelAndView();
		System.out.println(pessoa.getTipo());
		if(pessoa.getTipo().toString().equals("MILITAR")){
			mv.setViewName(CADASTRO_MILITAR);
		}else{
			mv.setViewName(CADASTRO_CIVIL);
		}
		mv.addObject(pessoa);
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String excluir(@PathVariable Long id) {
		pessoaService.delete(id);
		System.out.println("Entrou no delete");
		
		
		return "redirect:/pessoas";
	}
	
	@ModelAttribute("sexos")
	public List<Sexo> sexos() {
		return Arrays.asList(Sexo.values());
	}
	
	@ModelAttribute("estadosCivis")
	public List<EstadoCivil> estadosCivis() {
		return Arrays.asList(EstadoCivil.values());
	}
	
	
	@ModelAttribute("cargos")
	public Iterable<Cargo> cargos() {
		return cargoService.findAll();
	}
	
	@ModelAttribute("cidades")
	public Iterable<Cidade> cidades() {
		return cidadeService.findAll();
	}
	@ModelAttribute("bairros")
	public Iterable<Bairro> bairros() {
		return bairroService.findAll();
	}
	
	@ModelAttribute("opms")
	public Iterable<OpmOrgao> opms() {
		return opmOrgaoService.findByTipoMilitar();
	}
	@ModelAttribute("orgaos")
	public Iterable<OpmOrgao> orgaos() {
		return opmOrgaoService.findByTipoCivil();
	}
	
	
}