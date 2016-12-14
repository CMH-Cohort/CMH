package org.wcci.cmh;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TermController {

	@Resource
	private TermRepository myTermRepository;

	@RequestMapping("/all")
	public String displayEntireListOfTerms(Model model) {
	
		Iterable<Term> terms = myTermRepository.findAll();
		model.addAttribute("terms", terms);
		return "term-list";
	}
	
	

	@RequestMapping("/term-single")
	public String displayASingleTerm(@RequestParam(value = "name", required = false) long id, Model model) {
		Term term = myTermRepository.findOne(id);
		model.addAttribute("selectedTerm", term);
		return "term-single";
	}

	@RequestMapping("/search")
	public String search(@RequestParam(value = "title") String title, Model model) {
		Iterable<Term> searchResults = myTermRepository.findByTitleIgnoreCaseLike("%" + title + "%");
		model.addAttribute("terms", searchResults);
		return "term-list";
	}
	
	@RequestMapping("/add")
	public String add(@RequestParam(value = "title") String title) {
		return "term-list";
	}
	

	

}