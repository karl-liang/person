package com.sciov.cnicg.code.module.vo;

import io.swagger.annotations.ApiModelProperty;

public class AbEntityVo {
    @ApiModelProperty("能力实体")
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("创建时间")
    private String gmtCreate;

    @ApiModelProperty("实体状态")
    private Integer entityStatus;

    @ApiModelProperty("更新时间")
    private String gmtUpdate;

    @ApiModelProperty("生日")
    private String gmtBirthday;

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
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(Integer entityStatus) {
        this.entityStatus = entityStatus;
    }

    public String getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(String gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public String getGmtBirthday() {
        return gmtBirthday;
    }

    public void setGmtBirthday(String gmtBirthday) {
        this.gmtBirthday = gmtBirthday;
    }
}