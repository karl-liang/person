package cn.cnicg.code.recordengine.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.cnicg.code.recordengine.intf.IDecisionResult;
import cn.cnicg.code.recordengine.intf.IEvent;
import cn.cnicg.code.recordengine.intf.IRouteHandler;

@Component
public class DefaultLogRouteHandler implements IRouteHandler {

	private static final Logger logger = LoggerFactory.getLogger(DefaultLogRouteHandler.class);
	
	@Override
	public void route(IDecisionResult result, IEvent event) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		logger.info(String.format("%s entity route to %s",  gson.toJson(event.getEntity()),result.getResultKey()));
	}

}
