package cn.cnicg.code.recordengine.intf;

public interface IStateHandler {
	
	public void enterState(IState state, IEntity entity, IEvent event);
	
	public void exitState(IState state, IEntity entity, IEvent event);
	
}
