package com.sciov.cnicg.code.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sciov.cnicg.code.module.bean.AbEntity;
import com.sciov.cnicg.code.module.bean.AbEntityExample;
import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.AbEntityVo;
import com.sciov.cnicg.code.persist.mapper.AbEntityMapper;
import com.sciov.cnicg.code.service.IAbEntityService;
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
public class AbEntityServiceImpl implements IAbEntityService {
    @Autowired
    private AbEntityMapper abEntityMapper;

    private static final Logger logger = LoggerFactory.getLogger(AbEntityServiceImpl.class);;

    public AbEntityVo getAbEntity(int id) {
        return toVo(abEntityMapper.selectByPrimaryKey(id));
    }

    public int addAbEntity(AbEntityVo vo) {
        AbEntity abEntity= new AbEntity();
        abEntity.setAge(0);
        abEntity.setGmtCreate(new Date());
        abEntity.setEntityStatus(0);
        BeanUtils.copyProperties(vo, abEntity);
        return abEntityMapper.insert(abEntity);
        
    }

    public int deleteAbEntity(int id) {
        return abEntityMapper.deleteByPrimaryKey(id);
    }

    public int updateAbEntity(AbEntityVo vo) {
        AbEntity abEntity= abEntityMapper.selectByPrimaryKey(vo.getId());
        if(abEntity == null) {
			return 0;
		}
        BeanUtils.copyProperties(vo, abEntity);
        return abEntityMapper.updateByPrimaryKey(abEntity);
    }

    public ResponseData<List<AbEntityVo>> findAbEntityList(AbEntityVo vo, Date startDate, Date endDate, int pageNum, int pageSize) {
        AbEntityExample example = new AbEntityExample();
        AbEntityExample.Criteria criteria = example.createCriteria();
        Integer id = vo.getId();
		if (0 !=id) {
			criteria.andIdEqualTo(id);
		}
        String name = vo.getName();
		if (!StringUtils.isEmpty(name)) {
			criteria.andNameLike("%name%");
		}
        Integer age = vo.getAge();
		if (0 !=age) {
			criteria.andAgeEqualTo(age);
		}
        if(startDate != null) {
			criteria.andGmtCreateGreaterThanOrEqualTo(startDate);
		}
		if(endDate != null) {
			criteria.andGmtCreateLessThanOrEqualTo(endDate);
		}
        Integer entityStatus = vo.getEntityStatus();
		if (0 !=entityStatus) {
			criteria.andEntityStatusEqualTo(entityStatus);
		}
        Page page = PageHelper.startPage(pageNum, pageSize);
		List<AbEntity> entityList = abEntityMapper.selectByExample(example);
        List<AbEntityVo> voList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(entityList)) {
			for (AbEntity entity : entityList) {
				voList.add(toVo(entity));
			}
		}
        //根据需要设置分页信息
        //PageInfo pageInfo = new PageInfo(pageNum, page.getPageSize(), page.getTotal());
		return new ResponseData<>(voList);
    }

    public AbEntityVo toVo(AbEntity entity) {
        AbEntityVo abEntityVo= new AbEntityVo();
        BeanUtils.copyProperties(entity, abEntityVo);
        return abEntityVo;
    }
}