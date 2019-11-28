package com.jmy.controller.forword;

import com.jmy.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/case")
public class CaseController {

    @Autowired
    private CaseService caseService = null;
    @RequestMapping("/{path}.html")
    public String forword(@PathVariable("path") String path, Model model){
        Integer zan = caseService.getZan();
        model.addAttribute("zan",zan);
        return "/case/" + path;
    }

    @RequestMapping("/zan")
    public String zan(Model model){
        caseService.great();
        Integer zan = caseService.getZan();
        model.addAttribute("zan",zan);
        return "/case/case.html";
    }
}
