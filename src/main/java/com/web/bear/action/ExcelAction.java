package com.web.bear.action;

import com.web.bear.model.UserLotteryModel;
import com.web.bear.service.ExcelService;
import com.web.bear.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
                List<UserLotteryModel> listData = excelService.processExcel(file.getInputStream());
                result = JsonUtil.objectToJson(listData);
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
