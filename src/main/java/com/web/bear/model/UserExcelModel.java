package com.web.bear.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Excel名單資料")
public class UserExcelModel {

    @ApiModelProperty(value = "流水號", dataType = "Integer", example = "0", required = true)
    private Integer id;
    @ApiModelProperty(value = "姓名", dataType = "String", example = "陳聰明", required = true)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserExcelModel{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
