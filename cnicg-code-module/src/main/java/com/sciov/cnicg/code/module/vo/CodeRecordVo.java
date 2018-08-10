package com.sciov.cnicg.code.module.vo;

import io.swagger.annotations.ApiModelProperty;

public class CodeRecordVo {
    @ApiModelProperty("扫码记录")
    private Integer id;

    @ApiModelProperty("")
    private String code;

    @ApiModelProperty("当前状态")
    private String state;

    @ApiModelProperty("进入时间")
    private String gmtCreate;

    @ApiModelProperty("状态变更地点")
    private String location;

    @ApiModelProperty("相关事件")
    private String relateEvent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getRelateEvent() {
        return relateEvent;
    }

    public void setRelateEvent(String relateEvent) {
        this.relateEvent = relateEvent == null ? null : relateEvent.trim();
    }
}