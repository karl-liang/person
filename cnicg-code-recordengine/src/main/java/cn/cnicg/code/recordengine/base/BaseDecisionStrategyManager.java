package cn.cnicg.code.recordengine.base;

import java.util.ArrayList;
import java.util.List;

import cn.cnicg.code.recordengine.intf.IDecisionResult;
import cn.cnicg.code.recordengine.intf.IDecisionStrategy;
import cn.cnicg.code.recordengine.intf.IDecisionStrategyManager;
import cn.cnicg.code.recordengine.intf.IEvent;

public class BaseDecisionStrategyManager implements IDecisionStrategyManager {

	List<IDecisionStrategy> strategyList = new ArrayList<IDecisionStrategy>();
	
	@Override
	public IDecisionResult makeDecision(IEvent event) {
		IDecisionResult result =null;
		for(IDecisionStrategy strategy:strategyList) {
			result = strategy.makeDecision(event);
			if(result.finishDecision()) {
				break;
			}
		}
		
		return result;
	}

	@Override
	public void addDecisionStrategy(IDecisionStrategy decisionStrategy) {
		strategyList.add(decisionStrategy);
	}

	@Override
	public void addDecisionStrategy(int index, IDecisionStrategy decisionStrategy) {
		strategyList.add(index, decisionStrategy);
	}

}
