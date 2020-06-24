package com.web.bear.action;

import com.web.bear.model.UserExcelModel;
import com.web.bear.service.ExcelService;
import com.web.bear.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class StaticLotteryAction {

    @Autowired
    private ExcelService excelService;

    @RequestMapping("/static/lotteryPage")
    public String staticLotteryPage() {

        return "staticLottery";
    }
}
