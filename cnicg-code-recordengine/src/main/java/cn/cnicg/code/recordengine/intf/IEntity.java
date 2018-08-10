package cn.cnicg.code.recordengine.intf;

public interface IEntity {

	public IState  getCurrentState();
	
	public void setCurrentState(IState state);
}
