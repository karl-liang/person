package com.sciov.cnicg.code.module.bean;

import java.util.Date;

public class CodeRecord {
    private Integer id;

    private String code;

    private String state;

    private Date gmtCreate;

    private String location;

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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
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