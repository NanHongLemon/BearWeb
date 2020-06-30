package com.web.bear.action;

import com.web.bear.model.UserExcelModel;
import com.web.bear.service.ExcelService;
import com.web.bear.util.Const;
import com.web.bear.util.JsonUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ExcelAction {

    @Autowired
    private HttpServletResponse response;
    @Autowired
    private ExcelService excelService;

    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file) {

        String result = "";
        try {
            if (excelService.hasExcelFormat(file)) {
                List<UserExcelModel> listData = excelService.processExcel(file.getInputStream());
                result = JsonUtil.objectToJson(listData);
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

    @RequestMapping("/download")
    public String downloadResult(@RequestBody String data, @RequestParam("filename") String filename) {

        if (data == null || data.length() <= 0) {
            return "";
        }

        try {
            Workbook wb = excelService.getExcelDoc(data);
            response.setContentType(Const.XLSX_TYPE);
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
            OutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "download excel";
    }
}
