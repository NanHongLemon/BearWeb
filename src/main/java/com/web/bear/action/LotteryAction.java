package com.web.bear.action;

import com.web.bear.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LotteryAction {

    @Autowired
    private LotteryService lotteryService;

    @RequestMapping("/lottery")
    public String lottery(Model model) {

        return "excelLottery";
    }

    @PostMapping("/api/lottery")
    @ResponseBody
    public String execLottery(@RequestBody String data, @RequestParam int lotteryNumber, @RequestParam int waitingNumber) {

        if (data == null || data.length() <= 0 || lotteryNumber == 0) {
            return "";
        }
        try {
           return lotteryService.processLotteryUser(data, lotteryNumber, waitingNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
