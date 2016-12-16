package org.wcci.cmh;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class TermStatusController {

    @Resource
    private TermStatusRepository myTermStatusRepository;

    @RequestMapping("/all")
    public String displayEntireListOfTermStatuses(Model model) {

        Iterable<TermStatus> statuses = myTermStatusRepository.findAll();
        model.addAttribute("terms", statuses);
        return "term-list";
    }


    @RequestMapping("/term-single")
    public String displayASingleTerm(@RequestParam(value = "name", required = false) long id, Model model) {
        TermStatus status = myTermStatusRepository.findOne(id);
        model.addAttribute("selectedTerm", status);
        return "term-single";
    }

    @RequestMapping("/search")
    public String search(@RequestParam(value = "done") String done, Model model) {
        Iterable<TermStatus> searchResults = myTermStatusRepository.findByDoneIgnoreCaseLike("%" + done + "%");
        model.addAttribute("terms", searchResults);
        return "term-list";
    }

/*    @RequestMapping("/add")
    public String add(@RequestParam(value = "done") String title, Model model) {
        TermStatus searchResults = myTermStatusRepository.findByDoneIgnoreCase(title);
        if (searchResults == null) {
            TermStatus status = new TermStatus(done);
            myTermStatusRepository.save(status);
        }
        return displayEntireListOfTermStatuses(model);
    }*/

    @RequestMapping("/remove")
    public String remove(@RequestParam(value = "title") String title, Model model) {
        TermStatus status = myTermStatusRepository.findByDoneIgnoreCase(title);
        myTermStatusRepository.delete(status);
        return displayEntireListOfTermStatuses(model);
    }


}