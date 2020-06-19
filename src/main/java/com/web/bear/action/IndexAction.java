package com.web.bear.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexAction {

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello World.");
        return "index";
    }

    @GetMapping("/index")
    public String addMemberPage(Model model) {

        model.addAttribute("message", "First apps.");
        return "index";
    }
}
