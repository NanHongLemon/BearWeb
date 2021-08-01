package com.web.bear.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "抽獎後名單資料")
public class UserLotteryModel {

    @Schema(description = "正取")
    private List<UserExcelModel> admissions = new ArrayList<>();
    @Schema(description = "備取")
    private List<UserExcelModel> waitings = new ArrayList<>();

    public List<UserExcelModel> getAdmissions() {
        return admissions;
    }

    public void setAdmissions(List<UserExcelModel> admissions) {
        this.admissions = admissions;
    }

    public List<UserExcelModel> getWaitings() {
        return waitings;
    }

    public void setWaitings(List<UserExcelModel> waitings) {
        this.waitings = waitings;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserLotteryModel{");
        sb.append("admissions=").append(admissions);
        sb.append(", waitings=").append(waitings);
        sb.append('}');
        return sb.toString();
    }
}
