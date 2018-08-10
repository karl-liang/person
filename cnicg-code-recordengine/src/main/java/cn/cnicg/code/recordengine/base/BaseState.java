package cn.cnicg.code.recordengine.base;

import cn.cnicg.code.recordengine.intf.IEntity;
import cn.cnicg.code.recordengine.intf.IEvent;
import cn.cnicg.code.recordengine.intf.IState;
import cn.cnicg.code.recordengine.intf.IStateHandler;

public abstract class BaseState implements IState {

	protected final String stateKey;
	
	protected IStateHandler stateHandler;
	
	
	public BaseState(String stateKey,  IStateHandler stateHandler ) {
		this.stateKey = stateKey;
		this.stateHandler = stateHandler;
	}
	
	@Override
	public void enterState(IEvent event, IEntity entity) {
		enterStateImpl(entity);
		stateHandler.enterState(this,entity, event);
	}

	protected abstract void enterStateImpl(IEntity entity) ;
	
	protected abstract void exitStateImpl(IEntity entity) ;

	@Override
	public void exitState(IEvent event, IEntity entity) {
		exitStateImpl(entity);
		stateHandler.exitState(this,entity, event);
	}

	@Override
	public String stateKey() {
		// TODO Auto-generated method stub
		return stateKey;
	}

	public abstract BaseState getTargetState(IEvent event);

	
	
}
