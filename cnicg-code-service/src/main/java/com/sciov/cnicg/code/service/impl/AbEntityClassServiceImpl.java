package com.sciov.cnicg.code.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sciov.cnicg.code.module.bean.AbEntityClass;
import com.sciov.cnicg.code.module.bean.AbEntityClassExample;
import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.AbEntityClassVo;
import com.sciov.cnicg.code.persist.mapper.AbEntityClassMapper;
import com.sciov.cnicg.code.service.IAbEntityClassService;
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
public class AbEntityClassServiceImpl implements IAbEntityClassService {
    @Autowired
    private AbEntityClassMapper abEntityClassMapper;

    private static final Logger logger = LoggerFactory.getLogger(AbEntityClassServiceImpl.class);;

    public AbEntityClassVo getAbEntityClass(int id) {
        return toVo(abEntityClassMapper.selectByPrimaryKey(id));
    }

    public int addAbEntityClass(AbEntityClassVo vo) {
        AbEntityClass abEntityClass= new AbEntityClass();
        abEntityClass.setClassStatus(0);
        abEntityClass.setAbilityId(0);
        abEntityClass.setTargetScore(0);
        abEntityClass.setCurrentScore(0);
        abEntityClass.setGmtCreate(new Date());
        abEntityClass.setEntityId(0);
        BeanUtils.copyProperties(vo, abEntityClass);
        return abEntityClassMapper.insert(abEntityClass);
        
    }

    public int deleteAbEntityClass(int id) {
        return abEntityClassMapper.deleteByPrimaryKey(id);
    }

    public int updateAbEntityClass(AbEntityClassVo vo) {
        AbEntityClass abEntityClass= abEntityClassMapper.selectByPrimaryKey(vo.getId());
        if(abEntityClass == null) {
			return 0;
		}
        BeanUtils.copyProperties(vo, abEntityClass);
        return abEntityClassMapper.updateByPrimaryKey(abEntityClass);
    }

    public ResponseData<List<AbEntityClassVo>> findAbEntityClassList(AbEntityClassVo vo, Date startDate, Date endDate, int pageNum, int pageSize) {
        AbEntityClassExample example = new AbEntityClassExample();
        AbEntityClassExample.Criteria criteria = example.createCriteria();
        Integer id = vo.getId();
		if (0 !=id) {
			criteria.andIdEqualTo(id);
		}
        String className = vo.getClassName();
		if (!StringUtils.isEmpty(className)) {
			criteria.andClassNameLike("%className%");
		}
        Integer classStatus = vo.getClassStatus();
		if (0 !=classStatus) {
			criteria.andClassStatusEqualTo(classStatus);
		}
        Integer abilityId = vo.getAbilityId();
		if (0 !=abilityId) {
			criteria.andAbilityIdEqualTo(abilityId);
		}
        Integer targetScore = vo.getTargetScore();
		if (0 !=targetScore) {
			criteria.andTargetScoreEqualTo(targetScore);
		}
        Integer currentScore = vo.getCurrentScore();
		if (0 !=currentScore) {
			criteria.andCurrentScoreEqualTo(currentScore);
		}
        if(startDate != null) {
			criteria.andGmtCreateGreaterThanOrEqualTo(startDate);
		}
		if(endDate != null) {
			criteria.andGmtCreateLessThanOrEqualTo(endDate);
		}
        Integer entityId = vo.getEntityId();
		if (0 !=entityId) {
			criteria.andEntityIdEqualTo(entityId);
		}
        Page page = PageHelper.startPage(pageNum, pageSize);
		List<AbEntityClass> entityList = abEntityClassMapper.selectByExample(example);
        List<AbEntityClassVo> voList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(entityList)) {
			for (AbEntityClass entity : entityList) {
				voList.add(toVo(entity));
			}
		}
        //根据需要设置分页信息
        //PageInfo pageInfo = new PageInfo(pageNum, page.getPageSize(), page.getTotal());
		return new ResponseData<>(voList);
    }

    public AbEntityClassVo toVo(AbEntityClass entity) {
        AbEntityClassVo abEntityClassVo= new AbEntityClassVo();
        BeanUtils.copyProperties(entity, abEntityClassVo);
        return abEntityClassVo;
    }
}