package org.wcci.cmh;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TermController {

    @Resource
    private TermRepository termRepository;
    
    @Resource 
    private UserRepository userRepository;
    
    @Resource
    private TermStatusRepository termStatusRepository;
    
//    @Resource
//    private Principal principal;

    @RequestMapping("/all")
    public String displayEntireListOfTerms(Model model) {
//    	User user = myUserRepository.findByUsernameIgnoreCase((principal.getName()));

        Iterable<Term> terms = termRepository.findAll();
//    	ArrayList<Term> terms = new ArrayList<>();
//    	Iterable<TermStatus> termStatuses = termStatusRepository.findByUser(user);
//    	for(TermStatus status : termStatuses) {
//    		terms.add(status.getTerm());
//    	}
        model.addAttribute("terms", terms);
        return "term-list";
    }


    @RequestMapping("/term-single")
    public String displayASingleTerm(@RequestParam(value = "name", required = false) long id, Model model) {
        Term term = termRepository.findOne(id);
        model.addAttribute("selectedTerm", term);
        return "term-single";
    }

    @RequestMapping("/search")
    public String search(@RequestParam(value = "title") String title, Model model) {
        Iterable<Term> searchResults = termRepository.findByTitleIgnoreCaseLike("%" + title + "%");
        model.addAttribute("terms", searchResults);
        return "term-list";
    }

    @RequestMapping("/add")
    public String add(@RequestParam(value = "title") String title, Model model) {
        Term searchResults = termRepository.findByTitleIgnoreCase(title);
        if (searchResults == null) {
            Term term = new Term(title);
            termRepository.save(term);
            Iterable<User> allUsers = userRepository.findAll();
            if (allUsers != null && allUsers.iterator() != null && allUsers.iterator().hasNext()){
            	for(User user : allUsers) {
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
        //return displayEntireListOfTerms(model);
    }

    @RequestMapping("/remove")
    public String remove(@RequestParam(value = "title") String title, Model model) {
        Term term = termRepository.findByTitleIgnoreCase(title);
        termRepository.delete(term);
        return displayEntireListOfTerms(model);
    }


}