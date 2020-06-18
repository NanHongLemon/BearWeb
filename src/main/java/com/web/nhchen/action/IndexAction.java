package com.web.nhchen.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexAction {

    @GetMapping("index")
    public String addMemberPage(Model model) {

        model.addAttribute("message", "First apps.");
        return "index";
    }
}
