package cn.cnicg.code.recordengine.decision;

import org.springframework.stereotype.Component;

import cn.cnicg.code.recordengine.base.BaseDecisionResult;
import cn.cnicg.code.recordengine.enums.DecisionResultEnum;
import cn.cnicg.code.recordengine.intf.IDecisionResult;
import cn.cnicg.code.recordengine.intf.IDecisionStrategy;
import cn.cnicg.code.recordengine.intf.IEvent;

public class DefultDecisionStrategy implements IDecisionStrategy {

	@Override
	public IDecisionResult makeDecision(IEvent event) {
		return new BaseDecisionResult(DecisionResultEnum.SUCC);
	}

}
