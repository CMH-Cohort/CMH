package org.wcci.cmh;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TermController {

    @RequestMapping("/term-list")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        return "term-list";
    }

}