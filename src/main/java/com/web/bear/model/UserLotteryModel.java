package com.web.bear.model;

import java.util.ArrayList;
import java.util.List;

public class UserLotteryModel {

    private List<UserExcelModel> admissions = new ArrayList<>();
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
