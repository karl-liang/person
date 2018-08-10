package com.sciov.cnicg.code.module.bean;

public class GeoFencing {
    private Integer id;

    private String adcode;

    private String fenceGid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode == null ? null : adcode.trim();
    }

    public String getFenceGid() {
        return fenceGid;
    }

    public void setFenceGid(String fenceGid) {
        this.fenceGid = fenceGid == null ? null : fenceGid.trim();
    }
}