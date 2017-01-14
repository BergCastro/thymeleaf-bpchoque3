package br.com.fireware.bpchoque.controller.def;

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
import br.com.fireware.bpchoque.entity.Pessoa;
import br.com.fireware.bpchoque.entity.def.ResultadoTAF;
import br.com.fireware.bpchoque.entity.def.TesteFisico;
import br.com.fireware.bpchoque.entity.def.TesteFisico.Media;
import br.com.fireware.bpchoque.entity.def.TesteFisico.StatusTeste;
import br.com.fireware.bpchoque.entity.def.TesteFisico.TipoTesteFisico;
import br.com.fireware.bpchoque.service.PessoaService;
import br.com.fireware.bpchoque.service.def.ResultadoTafService;
import br.com.fireware.bpchoque.service.def.TesteFisicoService;

@Controller
@RequestMapping("/testesFisicos")
public class TesteFisicoController {

	private static final String CADASTRO_TESTE = "TestesFisicos/CadastroTesteFisico";
	private static final String TESTES_FISICOS = "TestesFisicos/TestesFisicos";
	private static final String ATUALIZA_RESULTADO = "TestesFisicos/AtualizaResultado";
	
	@Autowired
	private TesteFisicoService testeFisicoService;
	
	@Autowired
	private ResultadoTafService resultadoTafService;

	@Autowired
	private PessoaService pessoaService;

	private TesteFisico teste;

	@RequestMapping
	public ModelAndView testesFisicos() {
		Iterable<TesteFisico> todosTestesFisicos = testeFisicoService.findAll();
		ModelAndView mv = new ModelAndView(TESTES_FISICOS);
		mv.addObject("testesFisicos", todosTestesFisicos);

		return mv;
	}

	@RequestMapping("/novo")
	public ModelAndView novo() {

		ModelAndView mv = new ModelAndView();

		mv.setViewName(CADASTRO_TESTE);
		teste = new TesteFisico();

		mv.addObject("testeFisico", teste);

		return mv;

	}
	
	
	

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated TesteFisico testeFisico, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_TESTE;
		}
		if (testeFisico.getStatus() == null) {
			testeFisico.setStatus(StatusTeste.PREVISTO);
		}
		

		try {
			testeFisicoService.save(testeFisico);
			attributes.addFlashAttribute("mensagem", "Teste físico salvo com sucesso!");
			return "redirect:/testesFisicos";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_TESTE;
		}
	}

	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") TesteFisico testeFisico) {
		ModelAndView mv = new ModelAndView(CADASTRO_TESTE);
		
		
		
		mv.addObject(testeFisico);
		
	

		mv.addObject("resultadoVisivel", true);

		if (testeFisico.getTipo().equals(TipoTesteFisico.TAF)) {
			List<ResultadoTAF> resultadosTaf = resultadoTafService.findByTesteFisico(testeFisico);
			System.out.println("Passou pelo LIST");
			if(resultadosTaf.size() > 0){
				System.out.println("Total de resultados: "+resultadosTaf.size());
				
				mv.addObject("resultadosTaf", resultadosTaf);
				mv.addObject("taf", true);
			}
		}
		/*
		if (testeFisico.getTipo().equals(TipoTesteFisico.TAF_THE_CDC)) {
			mv.addObject("taf", true);
			mv.addObject("theCDC", true);
		}
		if (testeFisico.getTipo().equals(TipoTesteFisico.TAF_THE_COTAM)) {
			mv.addObject("taf", true);
			mv.addObject("theCOTAM", true);
		}
		if (testeFisico.getTipo().equals(TipoTesteFisico.TAF_THE_COTAR)) {
			mv.addObject("taf", true);
			mv.addObject("theCOTAR", true);
		}
		if (testeFisico.getTipo().equals(TipoTesteFisico.TAF_THE_CANIL)) {
			mv.addObject("taf", true);
			mv.addObject("theCANIL", true);
		}
		if (testeFisico.getTipo().equals(TipoTesteFisico.TAF_THE_GATE)) {
			mv.addObject("taf", true);
			mv.addObject("theGATE", true);
		}*/


		return mv;
	}

	@RequestMapping(value = "/delete/{id}")
	public String excluir(@PathVariable Long id) {
		testeFisicoService.delete(id);
		System.out.println("Entrou no delete");

		return "redirect:/testesFisicos";
	}

	@ModelAttribute("pessoas")
	public Iterable<Pessoa> pessoas() {
		return pessoaService.findAll();
	}

	@ModelAttribute("tipos")
	public List<TipoTesteFisico> tipos() {
		return Arrays.asList(TipoTesteFisico.values());
	}
	@ModelAttribute("status")
	public List<StatusTeste> status() {
		return Arrays.asList(StatusTeste.values());
	}
	
	@ModelAttribute("medias")
	public List<Media> media() {
		return Arrays.asList(Media.values());
	}

}