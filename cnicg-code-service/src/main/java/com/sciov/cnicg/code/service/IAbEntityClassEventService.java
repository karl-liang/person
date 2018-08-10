package com.sciov.cnicg.code.service;

import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.AbEntityClassEventVo;
import java.util.Date;
import java.util.List;

public interface IAbEntityClassEventService {
    AbEntityClassEventVo getAbEntityClassEvent(int id);

    int addAbEntityClassEvent(AbEntityClassEventVo vo);

    int deleteAbEntityClassEvent(int id);

    int updateAbEntityClassEvent(AbEntityClassEventVo vo);

    ResponseData<List<AbEntityClassEventVo>> findAbEntityClassEventList(AbEntityClassEventVo example, Date startDate, Date endDate, int pageNum, int pageSize);
}