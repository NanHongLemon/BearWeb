package com.web.bear.service;

import com.web.bear.model.UserLotteryModel;
import org.apache.catalina.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public List<UserLotteryModel> processExcel(InputStream inputStream) throws IOException {

        Workbook wb = new XSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        List<UserLotteryModel> list = new ArrayList<>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            UserLotteryModel userLotteryModel = new UserLotteryModel();
            if (row != null) {
                userLotteryModel.setDate(row.getCell(0).getDateCellValue());
                userLotteryModel.setName(row.getCell(1).getStringCellValue());
                userLotteryModel.setAge(row.getCell(2).getNumericCellValue());
                userLotteryModel.setGender(row.getCell(3).getStringCellValue());
                userLotteryModel.setTel(row.getCell(4).getStringCellValue());
                list.add(userLotteryModel);
            } else {
                break;
            }
        }
        System.out.println(list);
        return list;
    }
}
