package com.sciov.cnicg.code.service;

import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.TrailVo;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ITrailService {
    TrailVo getTrail(int id);

    int addTrail(TrailVo vo);

    int deleteTrail(int id);

    int updateTrail(TrailVo vo);

    ResponseData<List<TrailVo>> findTrailList(TrailVo example, Date startDate, Date endDate, int pageNum, int pageSize);

	void getTrail(HttpServletRequest request, HttpServletResponse response, String person,int zoom, Date startDate)  throws IOException, Exception ;
}