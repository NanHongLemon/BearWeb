package com.web.bear.action;

import com.web.bear.model.UserExcelModel;
import com.web.bear.service.ExcelService;
import com.web.bear.service.LotteryService;
import com.web.bear.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StaticLotteryAction {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ExcelService excelService;
    @Autowired
    private LotteryService lotteryService;

    @RequestMapping("/static/lotteryPage")
    public String staticLotteryPage() {

        return "staticLottery";
    }

    @PostMapping("/api/save/static/lottery")
    @ResponseBody
    public String saveStaticLottery(@RequestBody String data) {

        if (data == null || data.length() <= 0) {
            return "data fail";
        }

        try {
            lotteryService.saveStaticLottery(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "fail";
    }

    @DeleteMapping("/api/static/lottery")
    @ResponseBody
    public String deleteStaticLottery() {

        request.getSession().removeAttribute("staticUser");
        return "success";
    }

}
