package com.web.bear.action;

import com.web.bear.model.UserExcelModel;
import com.web.bear.model.UserLotteryModel;
import com.web.bear.service.ExcelService;
import com.web.bear.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ExcelAction {

    @Autowired
    private ExcelService excelService;

    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file) {

        String result = "";
        try {
            if (excelService.hasExcelFormat(file)) {
                List<UserExcelModel> listData = excelService.processExcel(file.getInputStream());
                result = JsonUtil.objectToJson(listData);
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/static/upload")
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
