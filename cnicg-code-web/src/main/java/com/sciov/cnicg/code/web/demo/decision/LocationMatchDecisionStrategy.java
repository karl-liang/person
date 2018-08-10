package com.sciov.cnicg.code.web.demo.decision;

import com.sciov.cnicg.code.web.demo.LogBaseState;

import cn.cnicg.code.recordengine.base.BaseDecisionResult;
import cn.cnicg.code.recordengine.enums.DecisionResultEnum;
import cn.cnicg.code.recordengine.intf.IDecisionResult;
import cn.cnicg.code.recordengine.intf.IDecisionStrategy;
import cn.cnicg.code.recordengine.intf.IEvent;

public class LocationMatchDecisionStrategy implements IDecisionStrategy{

	@Override
	public IDecisionResult makeDecision(IEvent event) {
		LogBaseState state = (LogBaseState)event.getSourceState();
		LogBaseState targetState = (LogBaseState)event.getTargetState();
		if(state.getLocaiton() == null || state.getLocaiton().equals(targetState.getLocaiton())) {
			return new BaseDecisionResult(DecisionResultEnum.SUCC);
		}else {
			return new BaseDecisionResult(DecisionResultEnum.WARN);
		}
	}

}
