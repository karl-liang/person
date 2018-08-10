package cn.cnicg.code.recordengine.intf;

public interface IState {

	public void enterState(IEvent event, IEntity entity);
	
	public void exitState(IEvent event, IEntity entity);
	
	public String stateKey();
	
//	public IEvent getSourceEvent();
}
