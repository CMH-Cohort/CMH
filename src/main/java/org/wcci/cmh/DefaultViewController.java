package org.wcci.cmh;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultViewController {

	@RequestMapping("/")
	public String redirectToDefault() {
		return "redirect:/all";
	}
}
