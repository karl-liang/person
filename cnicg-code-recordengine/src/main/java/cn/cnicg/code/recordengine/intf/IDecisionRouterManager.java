package cn.cnicg.code.recordengine.intf;

public interface IDecisionRouterManager {

	public void routeDecisionResult(IDecisionResult result, IEvent event);
	
	public void putRouter(String resultKey, IDecisionRouter router);
}
