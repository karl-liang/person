package com.sciov.cnicg.code.module.bean;

import java.util.Date;

public class AbEntity {
    private Integer id;

    private String name;

    private Integer age;

    private Date gmtCreate;

    private Integer entityStatus;

    private Date gmtUpdate;

    private Date gmtBirthday;

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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(Integer entityStatus) {
        this.entityStatus = entityStatus;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Date getGmtBirthday() {
        return gmtBirthday;
    }

    public void setGmtBirthday(Date gmtBirthday) {
        this.gmtBirthday = gmtBirthday;
    }
}