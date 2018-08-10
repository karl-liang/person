package com.sciov.cnicg.code.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sciov.cnicg.code.module.bean.AbEntityClassEvent;
import com.sciov.cnicg.code.module.bean.AbEntityClassEventExample;
import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.AbEntityClassEventVo;
import com.sciov.cnicg.code.persist.mapper.AbEntityClassEventMapper;
import com.sciov.cnicg.code.service.IAbEntityClassEventService;
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
public class AbEntityClassEventServiceImpl implements IAbEntityClassEventService {
    @Autowired
    private AbEntityClassEventMapper abEntityClassEventMapper;

    private static final Logger logger = LoggerFactory.getLogger(AbEntityClassEventServiceImpl.class);;

    public AbEntityClassEventVo getAbEntityClassEvent(int id) {
        return toVo(abEntityClassEventMapper.selectByPrimaryKey(id));
    }

    public int addAbEntityClassEvent(AbEntityClassEventVo vo) {
        AbEntityClassEvent abEntityClassEvent= new AbEntityClassEvent();
        abEntityClassEvent.setClassId(0);
        abEntityClassEvent.setEntityId(0);
        abEntityClassEvent.setGmtCreate(new Date());
        abEntityClassEvent.setScore(0);
        BeanUtils.copyProperties(vo, abEntityClassEvent);
        return abEntityClassEventMapper.insert(abEntityClassEvent);
        
    }

    public int deleteAbEntityClassEvent(int id) {
        return abEntityClassEventMapper.deleteByPrimaryKey(id);
    }

    public int updateAbEntityClassEvent(AbEntityClassEventVo vo) {
        AbEntityClassEvent abEntityClassEvent= abEntityClassEventMapper.selectByPrimaryKey(vo.getId());
        if(abEntityClassEvent == null) {
			return 0;
		}
        BeanUtils.copyProperties(vo, abEntityClassEvent);
        return abEntityClassEventMapper.updateByPrimaryKey(abEntityClassEvent);
    }

    public ResponseData<List<AbEntityClassEventVo>> findAbEntityClassEventList(AbEntityClassEventVo vo, Date startDate, Date endDate, int pageNum, int pageSize) {
        AbEntityClassEventExample example = new AbEntityClassEventExample();
        AbEntityClassEventExample.Criteria criteria = example.createCriteria();
        Integer id = vo.getId();
		if (0 !=id) {
			criteria.andIdEqualTo(id);
		}
        Integer classId = vo.getClassId();
		if (0 !=classId) {
			criteria.andClassIdEqualTo(classId);
		}
        Integer entityId = vo.getEntityId();
		if (0 !=entityId) {
			criteria.andEntityIdEqualTo(entityId);
		}
        if(startDate != null) {
			criteria.andGmtCreateGreaterThanOrEqualTo(startDate);
		}
		if(endDate != null) {
			criteria.andGmtCreateLessThanOrEqualTo(endDate);
		}
        Integer score = vo.getScore();
		if (0 !=score) {
			criteria.andScoreEqualTo(score);
		}
        Page page = PageHelper.startPage(pageNum, pageSize);
		List<AbEntityClassEvent> entityList = abEntityClassEventMapper.selectByExample(example);
        List<AbEntityClassEventVo> voList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(entityList)) {
			for (AbEntityClassEvent entity : entityList) {
				voList.add(toVo(entity));
			}
		}
        //根据需要设置分页信息
        //PageInfo pageInfo = new PageInfo(pageNum, page.getPageSize(), page.getTotal());
		return new ResponseData<>(voList);
    }

    public AbEntityClassEventVo toVo(AbEntityClassEvent entity) {
        AbEntityClassEventVo abEntityClassEventVo= new AbEntityClassEventVo();
        BeanUtils.copyProperties(entity, abEntityClassEventVo);
        return abEntityClassEventVo;
    }
}