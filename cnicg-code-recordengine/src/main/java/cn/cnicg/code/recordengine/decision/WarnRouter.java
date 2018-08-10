package cn.cnicg.code.recordengine.decision;

import cn.cnicg.code.recordengine.intf.IDecisionResult;
import cn.cnicg.code.recordengine.intf.IEvent;
import cn.cnicg.code.recordengine.intf.IRouteHandler;

public abstract class WarnRouter extends SuccRouter{

	public WarnRouter(IRouteHandler routeHanlder) {
		super(routeHanlder);
	}
	
	@Override
	public void routeDecisionResult(IDecisionResult result, IEvent event) {
		doWarnRoute(result,event);
		super.routeDecisionResult(result, event);
	}

	protected abstract void doWarnRoute(IDecisionResult result, IEvent event) ;
}
