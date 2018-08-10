package com.sciov.cnicg.code.module.vo;

import io.swagger.annotations.ApiModelProperty;

public class AbEntityClassEventVo {
    @ApiModelProperty("课程事件")
    private Integer id;

    @ApiModelProperty("课程ID")
    private Integer classId;

    @ApiModelProperty("实例ID")
    private Integer entityId;

    @ApiModelProperty("发生时间")
    private String gmtCreate;

    @ApiModelProperty("结束时间")
    private String gmtEnd;

    @ApiModelProperty("分数")
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(String gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}