package com.web.bear.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LotteryAction {

    @RequestMapping("/lottery")
    public String lottery(Model model) {

        return "excelLottery";
    }
}
