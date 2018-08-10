package com.sciov.cnicg.code.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sciov.cnicg.code.module.bean.CodeRecord;
import com.sciov.cnicg.code.module.bean.CodeRecordExample;
import com.sciov.cnicg.code.module.response.ResponseData;
import com.sciov.cnicg.code.module.vo.CodeRecordVo;
import com.sciov.cnicg.code.persist.mapper.CodeRecordMapper;
import com.sciov.cnicg.code.service.ICodeRecordService;
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
public class CodeRecordServiceImpl implements ICodeRecordService {
    @Autowired
    private CodeRecordMapper codeRecordMapper;

    private static final Logger logger = LoggerFactory.getLogger(CodeRecordServiceImpl.class);;

    public CodeRecordVo getCodeRecord(int id) {
        return toVo(codeRecordMapper.selectByPrimaryKey(id));
    }

    public int addCodeRecord(CodeRecordVo vo) {
        CodeRecord codeRecord= new CodeRecord();
        codeRecord.setGmtCreate(new Date());
        BeanUtils.copyProperties(vo, codeRecord);
        return codeRecordMapper.insert(codeRecord);
        
    }

    public int deleteCodeRecord(int id) {
        return codeRecordMapper.deleteByPrimaryKey(id);
    }

    public int updateCodeRecord(CodeRecordVo vo) {
        CodeRecord codeRecord= codeRecordMapper.selectByPrimaryKey(vo.getId());
        if(codeRecord == null) {
			return 0;
		}
        BeanUtils.copyProperties(vo, codeRecord);
        return codeRecordMapper.updateByPrimaryKey(codeRecord);
    }

    public ResponseData<List<CodeRecordVo>> findCodeRecordList(CodeRecordVo vo, Date startDate, Date endDate, int pageNum, int pageSize) {
        CodeRecordExample example = new CodeRecordExample();
        CodeRecordExample.Criteria criteria = example.createCriteria();
        Integer id = vo.getId();
		if (0 !=id) {
			criteria.andIdEqualTo(id);
		}
        String code = vo.getCode();
		if (!StringUtils.isEmpty(code)) {
			criteria.andCodeLike("%code%");
		}
        String state = vo.getState();
		if (!StringUtils.isEmpty(state)) {
			criteria.andStateLike("%state%");
		}
        if(startDate != null) {
			criteria.andGmtCreateGreaterThanOrEqualTo(startDate);
		}
		if(endDate != null) {
			criteria.andGmtCreateLessThanOrEqualTo(endDate);
		}
        String location = vo.getLocation();
		if (!StringUtils.isEmpty(location)) {
			criteria.andLocationLike("%location%");
		}
        String relateEvent = vo.getRelateEvent();
		if (!StringUtils.isEmpty(relateEvent)) {
			criteria.andRelateEventLike("%relateEvent%");
		}
        Page page = PageHelper.startPage(pageNum, pageSize);
		List<CodeRecord> entityList = codeRecordMapper.selectByExample(example);
        List<CodeRecordVo> voList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(entityList)) {
			for (CodeRecord entity : entityList) {
				voList.add(toVo(entity));
			}
		}
        //根据需要设置分页信息
        //PageInfo pageInfo = new PageInfo(pageNum, page.getPageSize(), page.getTotal());
		return new ResponseData<>(voList);
    }

    public CodeRecordVo toVo(CodeRecord entity) {
        CodeRecordVo codeRecordVo= new CodeRecordVo();
        BeanUtils.copyProperties(entity, codeRecordVo);
        return codeRecordVo;
    }
}