package com.sciov.cnicg.code.web.demo.decision;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sciov.cnicg.code.common.utils.JsonUtil;

import cn.cnicg.code.recordengine.decision.WarnRouter;
import cn.cnicg.code.recordengine.intf.IDecisionResult;
import cn.cnicg.code.recordengine.intf.IEvent;
import cn.cnicg.code.recordengine.intf.IRouteHandler;

public class LogWarnRouter extends WarnRouter {

	private static final Logger logger = LoggerFactory.getLogger(LogWarnRouter.class);
	
	public LogWarnRouter(IRouteHandler routeHanlder) {
		super(routeHanlder);
	}

	@Override
	protected void doWarnRoute(IDecisionResult result, IEvent event) {
		logger.info(String.format("warn route result %s ,event %s", JsonUtil.objToStr(result), JsonUtil.objToStr(event)));
	}

}
