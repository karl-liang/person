package cn.cnicg.code.async.akka;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorSystem;
import cn.cnicg.code.async.AsyncTask;
import cn.cnicg.code.async.ITaskDispatcher;
import cn.cnicg.code.async.ITaskWorker;

public class AysncTaskSystem implements ITaskDispatcher{

	private static final Logger logger = LoggerFactory.getLogger(AysncTaskSystem.class);
	private static final String ACTOR_SYSTEM_NAME = "Akka";
	public static final String CORE_DISPATCHER_NAME = "core-dispatcher";
	public static final String APP_DISPATCHER_NAME = "app-dispatcher";
	private static final String AKKA_CONF_FILE_NAME = "actor-system.conf";

	private ActorSystem system;
	Map<String, SpecialTaskDispatcher> dispatcherMap = new HashMap<String, SpecialTaskDispatcher>();

	public AysncTaskSystem(Map<String, ITaskWorker> workMap) {
		logger.info("Initializing Actor system. ");

		Config config = ConfigFactory.parseResources(AKKA_CONF_FILE_NAME).withFallback(ConfigFactory.load());
		system = ActorSystem.create(ACTOR_SYSTEM_NAME, config);
		
		Iterator<String> keyIterator = workMap.keySet().iterator();
		while(keyIterator.hasNext()) {
			String key = keyIterator.next();
			dispatcherMap.put(key,new SpecialTaskDispatcher(key,system, workMap.get(key)));
		}
	}

	
	public ActorSystem getSystem() {
		return system;
	}

	public void setSystem(ActorSystem system) {
		this.system = system;
	}


	@Override
	public void dispatch(AsyncTask task) {
		ITaskDispatcher dispatcher = dispatcherMap.get(task.getTaskType());
		if(dispatcher != null) {
			dispatcher.dispatch(task);
		}
	}
}
