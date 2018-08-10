package cn.cnicg.code.recordengine.base;

import cn.cnicg.code.recordengine.enums.DecisionResultEnum;
import cn.cnicg.code.recordengine.intf.IDecisionResult;

public class BaseDecisionResult implements IDecisionResult {

	private String key;
	private boolean finish;
	public BaseDecisionResult(DecisionResultEnum result) {
		this.key = result.getKey();
		finish = result.isFinish();
	}
	
	
	@Override
	public String getResultKey() {
		return key;
	}


	@Override
	public boolean finishDecision() {
		// TODO Auto-generated method stub
		return finish;
	}

}
