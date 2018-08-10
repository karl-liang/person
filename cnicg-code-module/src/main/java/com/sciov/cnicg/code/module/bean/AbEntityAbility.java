package com.sciov.cnicg.code.module.bean;

import java.util.Date;

public class AbEntityAbility {
    private Integer id;

    private String abilityName;

    private Integer abilityScore;

    private Integer abilityStatus;

    private Date gmtCreate;

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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}