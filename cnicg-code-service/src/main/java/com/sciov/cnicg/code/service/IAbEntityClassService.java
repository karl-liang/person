package com.sciov.cnicg.code.service;

import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.AbEntityClassVo;
import java.util.Date;
import java.util.List;

public interface IAbEntityClassService {
    AbEntityClassVo getAbEntityClass(int id);

    int addAbEntityClass(AbEntityClassVo vo);

    int deleteAbEntityClass(int id);

    int updateAbEntityClass(AbEntityClassVo vo);

    ResponseData<List<AbEntityClassVo>> findAbEntityClassList(AbEntityClassVo example, Date startDate, Date endDate, int pageNum, int pageSize);
}