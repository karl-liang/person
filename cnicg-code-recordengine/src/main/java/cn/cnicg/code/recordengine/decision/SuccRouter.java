package cn.cnicg.code.recordengine.decision;

import cn.cnicg.code.recordengine.base.BaseDecisionRouter;
import cn.cnicg.code.recordengine.intf.IDecisionResult;
import cn.cnicg.code.recordengine.intf.IDecisionRouter;
import cn.cnicg.code.recordengine.intf.IEntity;
import cn.cnicg.code.recordengine.intf.IEvent;
import cn.cnicg.code.recordengine.intf.IRouteHandler;

public class SuccRouter extends BaseDecisionRouter {

	
	public SuccRouter(IRouteHandler routeHanlder) {
		super(routeHanlder);
	}

	@Override
	public void routeDecisionResult(IDecisionResult result, IEvent event) {
		
		this.routeHandler.route(result, event);
		
		IEntity entity = event.getEntity();
		entity.getCurrentState().exitState(event, entity);
		entity.setCurrentState(event.getTargetState());
		event.getTargetState().enterState(event, entity);
		
	}

}
