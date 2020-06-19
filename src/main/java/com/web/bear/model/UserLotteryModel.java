package com.web.bear.model;

import java.util.Date;

public class UserLotteryModel {

    private Date date;
    private String name;
    private double age;
    private String gender;
    private String tel;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserLotteryModel{");
        sb.append("date=").append(date);
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", tel='").append(tel).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
