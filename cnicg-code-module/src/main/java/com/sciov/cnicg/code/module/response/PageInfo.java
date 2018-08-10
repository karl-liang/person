package com.sciov.cnicg.code.module.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Tinker on 2017/8/17.
 */
@ApiModel(value="Page", description="分页信息")
public class PageInfo {

    @ApiModelProperty("当前页")
    private Integer pageNum;

    @ApiModelProperty("每页的数量")
    private Integer pageSize;

    @ApiModelProperty("总数")
    private Long total;

    public PageInfo() {

    }

    public PageInfo(int pageNum, int pageSize, long total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
