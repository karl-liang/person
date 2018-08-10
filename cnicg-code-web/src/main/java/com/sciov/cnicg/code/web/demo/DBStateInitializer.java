package com.sciov.cnicg.code.web.demo;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sciov.cnicg.code.module.bean.CodeRecord;
import com.sciov.cnicg.code.module.bean.CodeRecordExample;
import com.sciov.cnicg.code.persist.mapper.CodeRecordMapper;

import cn.cnicg.code.recordengine.base.StateEntity;
import cn.cnicg.code.recordengine.intf.IEntity;
import cn.cnicg.code.recordengine.intf.IStateHandler;
import cn.cnicg.code.recordengine.intf.IStateInitializer;

@Component
public class DBStateInitializer implements IStateInitializer{

	@Autowired
    private CodeRecordMapper codeRecordMapper;
	
	@Autowired
	private IStateHandler stateHandler;
	
	@Override
	public void initializeEntityState(IEntity entity) {
		
		StateEntity<String> codeEntity = (StateEntity<String>)entity;
		String code = codeEntity.getEntity();
		
		CodeRecordExample example = new CodeRecordExample();
		CodeRecordExample.Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(code);
		example.setOrderByClause("gmt_create desc");
		List<CodeRecord> list = codeRecordMapper.selectByExample(example);
		LogBaseState state = null;
		if(CollectionUtils.isEmpty(list)) {
			state = new LogBaseState(null, new Date(),"begin",stateHandler);
		}else {
			CodeRecord record = list.get(0);
			state = new LogBaseState(record.getLocation(),record.getGmtCreate(),
					record.getState(),
					stateHandler);
		}
		codeEntity.setCurrentState(state);
		state.enterState(null,codeEntity);
	}

}
