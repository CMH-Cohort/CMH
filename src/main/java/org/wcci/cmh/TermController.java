package org.wcci.cmh;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.cmh.security.UserUtility;

@Controller
public class TermController {

	@Resource
	private TermRepository termRepository;

	@Resource
	private UserRepository userRepository;

	@Resource
	private TermStatusRepository termStatusRepository;

	@Resource
	private UserUtility userUtility;

	@RequestMapping("/all")
	public String displayEntireListOfTerms(Model model) {
		model.addAttribute("termStatuses", userUtility.currentUser()
				.getTermStatuses());
		return "term-list";
	}

	@RequestMapping("/search")
	public String search(@RequestParam(value = "title") String title,
			Model model) {
		Collection<Term> searchResults = termRepository
				.findByTitleIgnoreCaseLike("%" + title + "%");
		Collection<TermStatus> searchTermStatusResults = new ArrayList<TermStatus>();
		for (TermStatus termStatus : userUtility.currentUser()
				.getTermStatuses()) {
			if(searchResults.contains(termStatus.getTerm())) {
				searchTermStatusResults.add(termStatus);
			}
		}
		model.addAttribute("termStatuses", searchTermStatusResults);
		return "term-list";

	}

	@RequestMapping("/add")
	public String add(@RequestParam(value = "title") String title, Model model) {
		Term searchResults = termRepository
				.findByTitleIgnoreCase(title);
		if (searchResults == null) {
			Term term = new Term(title);
			termRepository.save(term);
			Iterable<User> allUsers = userRepository.findAll();
			if (allUsers != null && allUsers.iterator() != null
					&& allUsers.iterator().hasNext()) {
				for (User user : allUsers) {
					TermStatus termStatus = new TermStatus();
					termStatus.setTerm(term);
					termStatus.setUser(user);
					termStatus.setDone(false);
					user.getTermStatuses().add(termStatus);
					userRepository.save(user);
				}
			}
		}
		return "redirect:/all";
	}

	@RequestMapping("/remove")
	public String remove(@RequestParam(value = "title") String title,
			Model model) {
		Term term = termRepository.findByTitleIgnoreCase(title);
		termRepository.delete(term);
		return displayEntireListOfTerms(model);
	}

}