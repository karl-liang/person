package cn.cnicg.code.recordengine.base;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.cnicg.code.recordengine.intf.IDecisionResult;
import cn.cnicg.code.recordengine.intf.IDecisionRouterManager;
import cn.cnicg.code.recordengine.intf.IDecisionStrategyManager;
import cn.cnicg.code.recordengine.intf.IEntity;
import cn.cnicg.code.recordengine.intf.IEvent;
import cn.cnicg.code.recordengine.intf.IState;
import cn.cnicg.code.recordengine.intf.IStateInitializer;

@Service
public class RecordEngineService {
	
	@Autowired
	IStateInitializer stateInitializer;
	
	@Autowired
	IDecisionRouterManager decisionRouterManager;
	
	@Autowired
	IDecisionStrategyManager decisionStrategyManager;
	
	
	public void triggerRecord(StateEntity entity,RecordEvent event) {
		stateInitializer.initializeEntityState(entity);
		event.setSourceState(entity.getCurrentState());
		
		event.setTargetState(((BaseState)entity.getCurrentState()).getTargetState(event));
		
		IDecisionResult result = decisionStrategyManager.makeDecision(event);
		
		decisionRouterManager.routeDecisionResult(result, event);
		
	}


}
