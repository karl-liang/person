package com.sciov.cnicg.code.service;

import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.AbEntityAbilityVo;
import java.util.Date;
import java.util.List;

public interface IAbEntityAbilityService {
    AbEntityAbilityVo getAbEntityAbility(int id);

    int addAbEntityAbility(AbEntityAbilityVo vo);

    int deleteAbEntityAbility(int id);

    int updateAbEntityAbility(AbEntityAbilityVo vo);

    ResponseData<List<AbEntityAbilityVo>> findAbEntityAbilityList(AbEntityAbilityVo example, Date startDate, Date endDate, int pageNum, int pageSize);
}