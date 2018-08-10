package com.sciov.cnicg.code.service;

import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.AbEntityVo;
import java.util.Date;
import java.util.List;

public interface IAbEntityService {
    AbEntityVo getAbEntity(int id);

    int addAbEntity(AbEntityVo vo);

    int deleteAbEntity(int id);

    int updateAbEntity(AbEntityVo vo);

    ResponseData<List<AbEntityVo>> findAbEntityList(AbEntityVo example, Date startDate, Date endDate, int pageNum, int pageSize);
}