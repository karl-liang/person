package com.sciov.cnicg.code.web.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sciov.cnicg.code.common.utils.JsonUtil;
import com.sciov.cnicg.code.module.bean.CodeRecord;
import com.sciov.cnicg.code.persist.mapper.CodeRecordMapper;

import cn.cnicg.code.recordengine.base.StateEntity;
import cn.cnicg.code.recordengine.intf.IEntity;
import cn.cnicg.code.recordengine.intf.IEvent;
import cn.cnicg.code.recordengine.intf.IState;
import cn.cnicg.code.recordengine.intf.IStateHandler;

@Component
public class DBPersistStateHandler implements IStateHandler{
	private static final Logger logger = LoggerFactory.getLogger(DBPersistStateHandler.class);
	
	@Autowired
    private CodeRecordMapper codeRecordMapper;
	
	@Override
	public void enterState(IState state, IEntity en,IEvent eventt) {
		StateEntity<String> entity = (StateEntity<String>) en;
		SimpleEvent event = (SimpleEvent) eventt;
		if(event != null) {
			LogBaseState realState =(LogBaseState)state;
			CodeRecord codeRecord = new CodeRecord();
			codeRecord.setCode(entity.getEntity());
			codeRecord.setGmtCreate(event.getCreateTime());
			codeRecord.setLocation(realState.getLocaiton());
			codeRecord.setState(realState.stateKey());
			codeRecord.setRelateEvent(JsonUtil.objToStr(event));
			codeRecordMapper.insert(codeRecord);
		}
	}

	@Override
	public void exitState(IState state, IEntity en,IEvent eventt) {
		logger.info(String.format("entity %s exist state %s",JsonUtil.objToStr(eventt), state.stateKey()));
	}

}
