package org.wcci.cmh;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.wcci.cmh.Term;

@Controller
public class TermController {

    @RequestMapping("/term-list")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        return "term-list";
    }
    
//	@RequestMapping("/review")
//	public String displayReview(@RequestParam("id") long id, Model model) {
//		Review review = myReviewRepository.findOne(id);
//		model.addAttribute("selectedReview", review);
//		return "single-review";
//	}
//	
//	@RequestMapping("/all")
//	public String displayAll(Model model) {
//		Iterable<Review> reviews = myReviewRepository.findAll();
//		model.addAttribute("reviews", reviews);
//		Iterable<Category> categories = myCategoryRepository.findAll();
//		model.addAttribute("categories", categories);
//		return "all-reviews";
//	}
//	
//	@RequestMapping("/categories")
//	public String displayCategories(Model model) {
//		Iterable<Category> categories = myCategoryRepository.findAll();
//		model.addAttribute("categories", categories);
//		return "all-categories";
//	}

}