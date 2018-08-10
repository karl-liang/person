package cn.cnicg.code.async.akka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import cn.cnicg.code.async.AsyncTask;
import cn.cnicg.code.async.ITaskDispatcher;
import cn.cnicg.code.async.ITaskWorker;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class SpecialTaskDispatcher implements ITaskDispatcher{

	private static final Logger logger = LoggerFactory.getLogger(SpecialTaskDispatcher.class);
	public static final String CORE_DISPATCHER_NAME = "core-dispatcher";
	public static final String APP_DISPATCHER_NAME = "app-dispatcher";

	private ActorSystem system;
	private ActorRef dispatcherActor;
	
	ITaskWorker taskWorker;
	private String taskType;

	public SpecialTaskDispatcher(String taskType, ActorSystem system, ITaskWorker taskWorker) {
		logger.info(String.format("initializing %s Task Dispatcher",taskType));
		this.system = system;
		this.taskType = taskType;
		this.taskWorker = taskWorker;
		
		dispatcherActor = system.actorOf(Props.create(new DispatcherActor.ActorCreator(taskWorker)).withDispatcher(APP_DISPATCHER_NAME), "dispatcherActor");
	}
	
	public ActorSystem getSystem() {
		return system;
	}

	public void setSystem(ActorSystem system) {
		this.system = system;
	}

	public ActorRef getDispatcherActor() {
		return dispatcherActor;
	}

	public void setDispatcherActor(ActorRef dispatcherActor) {
		this.dispatcherActor = dispatcherActor;
	}

	@Override
	public void dispatch(AsyncTask task) {
		Timeout timeout = new Timeout(Duration.create(5, "seconds"));
		Future f = Patterns.ask(dispatcherActor, task, timeout);
	}
	
}
