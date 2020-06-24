package com.web.bear.service;

import com.web.bear.model.UserExcelModel;
import com.web.bear.model.UserLotteryModel;
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

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Autowired
    private HttpServletRequest request;

    public boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
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
        HttpSession session = request.getSession();
        session.setAttribute("staticUser", list);
        return list;
    }

    private String markName(String name) {

        if (name == null || name.length() == 0) {
            return "";
        }
        if (name.length() == 2) {
            return name.replaceFirst(".$", "*");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            if (i == 0 || i == name.length() - 1) {
                sb.append(name.charAt(i));
            } else {
                sb.append("*");
            }
        }

        return sb.toString();
    }
}
