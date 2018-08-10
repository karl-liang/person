package cn.cnicg.code.recordengine.intf;

public interface IDecisionRouter {

	public void routeDecisionResult(IDecisionResult result, IEvent event);
	
}
