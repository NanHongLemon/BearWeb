package com.web.bear.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.web.bear.model.UserExcelModel;
import com.web.bear.model.UserLotteryModel;
import com.web.bear.util.Const;
import com.web.bear.util.JsonUtil;
import org.apache.catalina.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private HttpServletRequest request;

    public boolean hasExcelFormat(MultipartFile file) {

        if (!Const.XLSX_TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public List<UserExcelModel> processExcel(InputStream inputStream) throws IOException {

        DataFormatter formatter = new DataFormatter();
        Workbook wb = new XSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        List<UserExcelModel> list = new ArrayList<>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            UserExcelModel userExcelModel = new UserExcelModel();
            if (row != null) {
                String id = formatter.formatCellValue(row.getCell(0));
                String name = formatter.formatCellValue(row.getCell(1));
                if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(name)) {
                    userExcelModel.setId(Integer.parseInt(id));
                    userExcelModel.setName(markName(name.trim()));
                    list.add(userExcelModel);
                }
            } else {
                break;
            }
        }

        return list;
    }

    public List<UserExcelModel> saveStaticExcel(InputStream inputStream) throws IOException {

        List<UserExcelModel> list = processExcel(inputStream);
        return list;
    }

    private String markName(String name) {

        if (name == null || name.length() == 0) {
            return "";
        }
        if (name.length() == 2) {
            return name.replaceFirst(".$", "○");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            if (i == 0 || i == name.length() - 1) {
                sb.append(name.charAt(i));
            } else {
                sb.append("○");
            }
        }

        return sb.toString();
    }

    public Workbook getExcelDoc(List<UserExcelModel> user) {

        if (user.isEmpty()) {
            return new XSSFWorkbook();
        }

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("流水號");
        row.createCell(1).setCellValue("編號");
        row.createCell(2).setCellValue("姓名");

        int rowNum = 1;
        for (UserExcelModel item : user) {
            Row rowItem = sheet.createRow(rowNum);
            rowItem.createCell(0).setCellValue(rowNum);
            rowItem.createCell(1).setCellValue(item.getId());
            rowItem.createCell(2).setCellValue(item.getName());
            rowNum++;
        }

        return wb;
    }
}
