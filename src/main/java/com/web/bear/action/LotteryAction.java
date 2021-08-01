package com.web.bear.action;

import com.web.bear.model.UserExcelModel;
import com.web.bear.model.UserLotteryModel;
import com.web.bear.service.LotteryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "抽獎")
public class LotteryAction {

    @Autowired
    private LotteryService lotteryService;

    @ApiOperation(value = "抽獎頁面")
    @GetMapping(value = "/lottery", produces = MediaType.TEXT_HTML_VALUE)
    public String lottery() {

        return "excelLottery";
    }

    @ApiOperation(value = "執行抽獎")
    @PostMapping(value = "/api/lottery", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserLotteryModel execLottery(@RequestBody List<UserExcelModel> data,
                                        @RequestParam int lotteryNumber,
                                        @RequestParam(value = "waitingNumber", required = false) int waitingNumber) {

        if (data == null || data.size() <= 0 || lotteryNumber == 0) {
            return null;
        }
        try {
           return lotteryService.processLotteryUser(data, lotteryNumber, waitingNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
