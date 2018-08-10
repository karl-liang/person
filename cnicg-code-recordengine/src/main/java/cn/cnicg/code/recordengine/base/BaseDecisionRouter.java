package cn.cnicg.code.recordengine.base;

import cn.cnicg.code.recordengine.intf.IDecisionResult;
import cn.cnicg.code.recordengine.intf.IDecisionRouter;
import cn.cnicg.code.recordengine.intf.IEvent;
import cn.cnicg.code.recordengine.intf.IRouteHandler;

public abstract class BaseDecisionRouter implements IDecisionRouter{

	protected IRouteHandler routeHandler;
	
	public BaseDecisionRouter(IRouteHandler routeHanlder) {
		this.routeHandler = routeHanlder;
	}
	
	@Override
	public  abstract void routeDecisionResult(IDecisionResult result, IEvent event) ;

	public IRouteHandler getRouteHandler() {
		return routeHandler;
	}

	public void setRouteHandler(IRouteHandler routeHandler) {
		this.routeHandler = routeHandler;
	}
	
	
}
