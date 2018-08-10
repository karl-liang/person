package cn.cnicg.code.recordengine.intf;

public interface IDecisionStrategyManager {

	public IDecisionResult makeDecision(IEvent event);
	
	public void addDecisionStrategy(IDecisionStrategy decisionStrategy);
	
	public void addDecisionStrategy(int index, IDecisionStrategy decisionStrategy);
	
}
