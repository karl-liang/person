package cn.cnicg.code.recordengine.intf;

public interface IDecisionStrategy {

	public IDecisionResult makeDecision(IEvent event);
}
