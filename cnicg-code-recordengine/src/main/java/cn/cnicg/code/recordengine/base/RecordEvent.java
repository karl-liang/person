package cn.cnicg.code.recordengine.base;

import cn.cnicg.code.recordengine.intf.IEntity;
import cn.cnicg.code.recordengine.intf.IEvent;
import cn.cnicg.code.recordengine.intf.IState;

public abstract class RecordEvent implements IEvent{
	protected IEntity entity;
	
	protected IState sourceState;
	protected IState targetState;

	protected String action;
	protected String remark;
	
	public RecordEvent(IEntity entity, IState sourceState, IState targetState, String action, String remark) {
		this.entity = entity;
		this.sourceState = sourceState;
		this.targetState = targetState;
		this.action = action;
		this.remark = remark;
	}

	@Override
	public IEntity getEntity() {
		return entity;
	}

	@Override
	public IState getSourceState() {
		// TODO Auto-generated method stub
		return sourceState;
	}

	@Override
	public IState getTargetState() {
		// TODO Auto-generated method stub
		return targetState;
	}

	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return action;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	public void setEntity(IEntity entity) {
		this.entity = entity;
	}

	public void setSourceState(IState sourceState) {
		this.sourceState = sourceState;
	}

	public void setTargetState(IState targetState) {
		this.targetState = targetState;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
