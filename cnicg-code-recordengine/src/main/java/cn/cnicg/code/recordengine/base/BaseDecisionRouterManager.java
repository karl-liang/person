package cn.cnicg.code.recordengine.base;

import java.util.HashMap;
import java.util.Map;

import cn.cnicg.code.recordengine.intf.IDecisionResult;
import cn.cnicg.code.recordengine.intf.IDecisionRouter;
import cn.cnicg.code.recordengine.intf.IDecisionRouterManager;
import cn.cnicg.code.recordengine.intf.IEvent;

public class BaseDecisionRouterManager implements IDecisionRouterManager {

	Map<String, IDecisionRouter> routerMapper = new HashMap<String, IDecisionRouter>();
	
	@Override
	public void routeDecisionResult(IDecisionResult result, IEvent event) {
		IDecisionRouter router = routerMapper.get(result.getResultKey());
		if(router != null) {
			router.routeDecisionResult(result, event);
		}
	}

	@Override
	public void putRouter(String resultKey, IDecisionRouter router) {
		routerMapper.put(resultKey, router);
	}

}
