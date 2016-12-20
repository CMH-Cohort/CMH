package org.wcci.cmh;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TermStatusController {

    @Resource
    private TermStatusRepository myTermStatusRepository;

    @RequestMapping("/markDone")
    public @ResponseBody void markDone(@RequestParam(value = "termStatusId") long id) {
        TermStatus status = myTermStatusRepository.findOne(id);
        status.markDone();
        myTermStatusRepository.save(status);
    }

   

}