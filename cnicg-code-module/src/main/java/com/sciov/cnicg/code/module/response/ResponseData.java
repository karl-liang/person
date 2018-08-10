package com.sciov.cnicg.code.module.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Tinker on 2017/8/17.
 */
@ApiModel(value="ResponseData", description="返回信息")
public class ResponseData<T> {

    public ResponseData() {

    }

    public ResponseData(String message) {
        this.message = message;
    }

    public ResponseData(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ResponseData(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ResponseData(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public ResponseData(T data) {
        this.statusCode = 200;
        this.message = "success";
        this.data = data;
    }

    public ResponseData(int statusCode, String message, T data, PageInfo pageInfo) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.pageInfo = pageInfo;
    }


    public ResponseData(T data, PageInfo pageInfo) {
        this.statusCode = 200;
        this.message = "success";
        this.data = data;
        this.pageInfo = pageInfo;
    }

    @ApiModelProperty(
    		value="状态码 " +
					"success: 200, " +
					"fail: 300, " +
					"device not exists: 10000"
	)
    private int statusCode = 200;

    @ApiModelProperty("提示信息")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;

    @ApiModelProperty("分页数据")
    private PageInfo pageInfo;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
