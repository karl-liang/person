package com.sciov.cnicg.code.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sciov.cnicg.code.module.bean.AbEntityAbility;
import com.sciov.cnicg.code.module.bean.AbEntityAbilityExample;
import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.AbEntityAbilityVo;
import com.sciov.cnicg.code.persist.mapper.AbEntityAbilityMapper;
import com.sciov.cnicg.code.service.IAbEntityAbilityService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class AbEntityAbilityServiceImpl implements IAbEntityAbilityService {
    @Autowired
    private AbEntityAbilityMapper abEntityAbilityMapper;

    private static final Logger logger = LoggerFactory.getLogger(AbEntityAbilityServiceImpl.class);;

    public AbEntityAbilityVo getAbEntityAbility(int id) {
        return toVo(abEntityAbilityMapper.selectByPrimaryKey(id));
    }

    public int addAbEntityAbility(AbEntityAbilityVo vo) {
        AbEntityAbility abEntityAbility= new AbEntityAbility();
        abEntityAbility.setAbilityScore(0);
        abEntityAbility.setAbilityStatus(0);
        abEntityAbility.setGmtCreate(new Date());
        BeanUtils.copyProperties(vo, abEntityAbility);
        return abEntityAbilityMapper.insert(abEntityAbility);
        
    }

    public int deleteAbEntityAbility(int id) {
        return abEntityAbilityMapper.deleteByPrimaryKey(id);
    }

    public int updateAbEntityAbility(AbEntityAbilityVo vo) {
        AbEntityAbility abEntityAbility= abEntityAbilityMapper.selectByPrimaryKey(vo.getId());
        if(abEntityAbility == null) {
			return 0;
		}
        BeanUtils.copyProperties(vo, abEntityAbility);
        return abEntityAbilityMapper.updateByPrimaryKey(abEntityAbility);
    }

    public ResponseData<List<AbEntityAbilityVo>> findAbEntityAbilityList(AbEntityAbilityVo vo, Date startDate, Date endDate, int pageNum, int pageSize) {
        AbEntityAbilityExample example = new AbEntityAbilityExample();
        AbEntityAbilityExample.Criteria criteria = example.createCriteria();
        Integer id = vo.getId();
		if (0 !=id) {
			criteria.andIdEqualTo(id);
		}
        String abilityName = vo.getAbilityName();
		if (!StringUtils.isEmpty(abilityName)) {
			criteria.andAbilityNameLike("%abilityName%");
		}
        Integer abilityScore = vo.getAbilityScore();
		if (0 !=abilityScore) {
			criteria.andAbilityScoreEqualTo(abilityScore);
		}
        Integer abilityStatus = vo.getAbilityStatus();
		if (0 !=abilityStatus) {
			criteria.andAbilityStatusEqualTo(abilityStatus);
		}
        if(startDate != null) {
			criteria.andGmtCreateGreaterThanOrEqualTo(startDate);
		}
		if(endDate != null) {
			criteria.andGmtCreateLessThanOrEqualTo(endDate);
		}
        Page page = PageHelper.startPage(pageNum, pageSize);
		List<AbEntityAbility> entityList = abEntityAbilityMapper.selectByExample(example);
        List<AbEntityAbilityVo> voList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(entityList)) {
			for (AbEntityAbility entity : entityList) {
				voList.add(toVo(entity));
			}
		}
        //根据需要设置分页信息
        //PageInfo pageInfo = new PageInfo(pageNum, page.getPageSize(), page.getTotal());
		return new ResponseData<>(voList);
    }

    public AbEntityAbilityVo toVo(AbEntityAbility entity) {
        AbEntityAbilityVo abEntityAbilityVo= new AbEntityAbilityVo();
        BeanUtils.copyProperties(entity, abEntityAbilityVo);
        return abEntityAbilityVo;
    }
}