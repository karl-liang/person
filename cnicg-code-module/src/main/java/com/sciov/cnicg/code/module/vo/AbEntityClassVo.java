package com.sciov.cnicg.code.module.vo;

import io.swagger.annotations.ApiModelProperty;

public class AbEntityClassVo {
    @ApiModelProperty("能力课程")
    private Integer id;

    @ApiModelProperty("课程名称")
    private String className;

    @ApiModelProperty("课程状态")
    private Integer classStatus;

    @ApiModelProperty("目标能力值")
    private Integer abilityId;

    @ApiModelProperty("课程目标")
    private Integer targetScore;

    @ApiModelProperty("当前分数")
    private Integer currentScore;

    @ApiModelProperty("创建时间")
    private String gmtCreate;

    @ApiModelProperty("计划完成时间")
    private String gmtPlanFinish;

    @ApiModelProperty("实际结束时间")
    private String gmtEnd;

    @ApiModelProperty("实体ID")
    private Integer entityId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(Integer classStatus) {
        this.classStatus = classStatus;
    }

    public Integer getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(Integer abilityId) {
        this.abilityId = abilityId;
    }

    public Integer getTargetScore() {
        return targetScore;
    }

    public void setTargetScore(Integer targetScore) {
        this.targetScore = targetScore;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtPlanFinish() {
        return gmtPlanFinish;
    }

    public void setGmtPlanFinish(String gmtPlanFinish) {
        this.gmtPlanFinish = gmtPlanFinish;
    }

    public String getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(String gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
}