package com.sciov.cnicg.code.web.demo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sciov.cnicg.code.service.impl.CodeRecordServiceImpl;

import cn.cnicg.code.recordengine.base.BaseState;
import cn.cnicg.code.recordengine.intf.IEntity;
import cn.cnicg.code.recordengine.intf.IEvent;
import cn.cnicg.code.recordengine.intf.IStateHandler;

public class LogBaseState extends BaseState {
	String locaiton;
	Date gmtCreate;
	
	
	private static final Logger logger = LoggerFactory.getLogger(CodeRecordServiceImpl.class);
	public LogBaseState(String locaiton, Date changeDate,
			String stateKey,  IStateHandler stateHandler) {
		super(stateKey,  stateHandler);
		this.locaiton = locaiton;
		this.gmtCreate = changeDate;
	}

	@Override
	protected void enterStateImpl(IEntity entity) {
		logger.info(String.format("entity %s enter state %s", entity, this.stateKey));
	}

	@Override
	protected void exitStateImpl(IEntity entity) {
		logger.info(String.format("entity %s exit state %s", entity, this.stateKey));
	}

	public String getLocaiton() {
		return locaiton;
	}

	public void setLocaiton(String locaiton) {
		this.locaiton = locaiton;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	@Override
	public BaseState getTargetState(IEvent event) {
		SimpleEvent simpleEvent = (SimpleEvent)event;
		BaseState result = new LogBaseState(simpleEvent.getLocation(), simpleEvent.getCreateTime(),
				event.getAction(),  stateHandler);
		return result;
	}

	
}
