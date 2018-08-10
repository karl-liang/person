package com.sciov.cnicg.code.module.vo;

import io.swagger.annotations.ApiModelProperty;

public class AbEntityAbilityVo {
    @ApiModelProperty("实体能力")
    private Integer id;

    @ApiModelProperty("能力名称")
    private String abilityName;

    @ApiModelProperty("能力值")
    private Integer abilityScore;

    @ApiModelProperty("能力值状态")
    private Integer abilityStatus;

    @ApiModelProperty("创建时间")
    private String gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName == null ? null : abilityName.trim();
    }

    public Integer getAbilityScore() {
        return abilityScore;
    }

    public void setAbilityScore(Integer abilityScore) {
        this.abilityScore = abilityScore;
    }

    public Integer getAbilityStatus() {
        return abilityStatus;
    }

    public void setAbilityStatus(Integer abilityStatus) {
        this.abilityStatus = abilityStatus;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}