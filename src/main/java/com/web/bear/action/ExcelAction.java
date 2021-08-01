package com.web.bear.action;

import com.web.bear.model.UserExcelModel;
import com.web.bear.service.ExcelService;
import com.web.bear.util.Const;
import com.web.bear.util.JsonUtil;
import io.swagger.annotations.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
@Api(tags = "Excel")
public class ExcelAction {

    @Autowired
    private ExcelService excelService;
    @Autowired
    private HttpServletResponse response;

    @ApiOperation(value = "抽獎名單上傳Excel檔案")
    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String upload(@ApiParam(name = "file", value = "抽獎Excel檔", required = true) @RequestPart("file") MultipartFile file) {

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

    @ApiOperation("固定名單Excel上傳")
    @PostMapping(value = "/static/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String uploadStaticLottery(@ApiParam(name = "file", value = "固定名單Excel檔", required = true) @RequestPart("file") MultipartFile file) {

        String result = "";
        try {
            if (excelService.hasExcelFormat(file)) {
                List<UserExcelModel> listData = excelService.saveStaticExcel(file.getInputStream());
                result = JsonUtil.objectToJson(listData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("抽獎名單Excel下載")
    @PostMapping(value = "/download", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String downloadResult(@ApiParam(name = "data", required = true) @RequestBody List<UserExcelModel> data) {

        if (data == null || data.size() <= 0) {
            return "";
        }

        try {
            Workbook wb = excelService.getExcelDoc(data);
            response.setContentType(Const.XLSX_TYPE);
            response.setHeader("Content-Disposition", "attachment;");
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
