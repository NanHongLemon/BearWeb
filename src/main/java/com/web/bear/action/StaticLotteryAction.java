package com.web.bear.action;

import com.web.bear.model.UserExcelModel;
import com.web.bear.service.ExcelService;
import com.web.bear.service.LotteryService;
import com.web.bear.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Api(tags = "固定抽獎")
public class StaticLotteryAction {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ExcelService excelService;
    @Autowired
    private LotteryService lotteryService;

    @ApiOperation(value = "固定抽獎頁面")
    @GetMapping(value = "/static/lotteryPage", produces = MediaType.TEXT_HTML_VALUE)
    public String staticLotteryPage() {

        return "staticLottery";
    }

    @ApiOperation(value = "匯入固定抽獎資料")
    @PostMapping(value = "/api/save/static/lottery", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String saveStaticLottery(@RequestBody List<UserExcelModel> data) {

        if (data == null || data.size() <= 0) {
            return "data fail";
        }

        try {
            lotteryService.saveStaticLottery(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "fail";
    }

    @ApiOperation(value = "刪除固定抽獎資料")
    @DeleteMapping(value = "/api/static/lottery", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String deleteStaticLottery() {

        request.getSession().removeAttribute("staticUser");
        return "success";
    }
}
