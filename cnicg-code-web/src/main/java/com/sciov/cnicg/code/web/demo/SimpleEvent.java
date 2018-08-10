package com.sciov.cnicg.code.web.demo;

import java.util.Date;

import cn.cnicg.code.recordengine.base.RecordEvent;
import cn.cnicg.code.recordengine.intf.IEntity;
import cn.cnicg.code.recordengine.intf.IState;

public class SimpleEvent extends RecordEvent {

	String location;
	Date createTime;
	
	public SimpleEvent(String location, Date createTime,
			IEntity entity, IState sourceState, IState targetState, String action, String remark) {
		super(entity, sourceState, targetState, action, remark);
		this.location = location;
		this.createTime = createTime;
	}

	@Override
	public String getEvent() {
		return action;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
