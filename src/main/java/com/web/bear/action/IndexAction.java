package com.web.bear.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexAction {

    @RequestMapping("/")
    public String home(Model model) {
        return "index";
    }
}
