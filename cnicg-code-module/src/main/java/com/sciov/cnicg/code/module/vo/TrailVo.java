package com.sciov.cnicg.code.module.vo;

import io.swagger.annotations.ApiModelProperty;

public class TrailVo {
    @ApiModelProperty("位置跟踪")
    private Integer id;

    @ApiModelProperty("跟踪主体")
    private String person;

    @ApiModelProperty("上报的经纬度")
    private String point;

    @ApiModelProperty("解析的可读地址")
    private String location;

    @ApiModelProperty("上报时间")
    private String gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point == null ? null : point.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}