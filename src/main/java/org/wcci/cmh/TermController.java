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

	@RequestMapping("/term-list")
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
	

	// @RequestMapping("/review")
	// public String displayReview(@RequestParam("id") long id, Model model) {
	// Review review = myReviewRepository.findOne(id);
	// model.addAttribute("selectedReview", review);
	// return "single-review";
	// }
	//
	// @RequestMapping("/all")
	// public String displayAll(Model model) {
	// Iterable<Review> reviews = myReviewRepository.findAll();
	// model.addAttribute("reviews", reviews);
	// Iterable<Category> categories = myCategoryRepository.findAll();
	// model.addAttribute("categories", categories);
	// return "all-reviews";
	// }
	//
	// @RequestMapping("/categories")
	// public String displayCategories(Model model) {
	// Iterable<Category> categories = myCategoryRepository.findAll();
	// model.addAttribute("categories", categories);
	// return "all-categories";
	// }

}