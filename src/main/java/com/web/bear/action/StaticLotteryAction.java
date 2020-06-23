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

    @RequestMapping("/api/upload/static/lottery")
    @ResponseBody
    public String uploadStaticLottery(@RequestParam("file") MultipartFile file) {

        String result = "";
        try {
            if (excelService.hasExcelFormat(file)) {
                List<UserExcelModel> listData = excelService.saveStaticExcel(file.getInputStream());
                result = JsonUtil.objectToJson(listData);
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
