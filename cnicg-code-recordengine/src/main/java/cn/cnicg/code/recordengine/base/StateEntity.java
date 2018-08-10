package cn.cnicg.code.recordengine.base;

import cn.cnicg.code.recordengine.intf.IEntity;
import cn.cnicg.code.recordengine.intf.IState;

public class StateEntity<T> implements IEntity{

	private T entity;
	
	private IState currentState;

	public StateEntity(T entity) {
		this.entity = entity;
	}
	
	@Override
	public IState getCurrentState() {
		return currentState;
	}


	@Override
	public void setCurrentState(IState state) {
		currentState = state;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	
}
