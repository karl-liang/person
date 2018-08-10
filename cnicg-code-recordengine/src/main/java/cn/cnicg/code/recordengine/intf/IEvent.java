package cn.cnicg.code.recordengine.intf;

public interface IEvent {
	
	IEntity getEntity();
	
	IState getSourceState();
	
	IState getTargetState();
	
	String getAction();
	
	String getRemark();
	
	String getEvent();

}
