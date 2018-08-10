package cn.cnicg.code.async.akka;

import java.time.Duration;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.SupervisorStrategy;
import akka.japi.Creator;
import akka.japi.pf.DeciderBuilder;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.ConsistentHashingPool;
import akka.routing.ConsistentHashingRouter.ConsistentHashMapper;
import cn.cnicg.code.async.AsyncTask;
import cn.cnicg.code.async.ITaskWorker;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;

public class DispatcherActor extends AbstractActor {
	
	ActorRef workerActor;
	
	public DispatcherActor(ITaskWorker taskWorker) {
		workerActor =  getContext().actorOf(
				new ConsistentHashingPool(10).withHashMapper(hashMapper).props(Props.create(new WorkerActor.ActorCreator(taskWorker))),
				"taskWorker");
	}

	private static SupervisorStrategy strategy = new OneForOneStrategy(10, Duration.ofMinutes(1), DeciderBuilder
			// .match(ArithmeticException.class, e -> SupervisorStrategy.resume())
			// .match(NullPointerException.class, e -> SupervisorStrategy.restart())
			// .match(IllegalArgumentException.class, e -> SupervisorStrategy.stop())
			.matchAny(o -> SupervisorStrategy.restart()).build());

	@Override
	public SupervisorStrategy supervisorStrategy() {
		return strategy;
	}

	final ConsistentHashMapper hashMapper = new ConsistentHashMapper() {
		@Override
		public Object hashKey(Object message) {
			if (message instanceof AsyncTask) {
				AsyncTask task = (AsyncTask)message;
				return task.getRealTask().hashCode();
			} else {
				return null;
			}
		}
	};

	@Override
	public Receive createReceive() {
		return ReceiveBuilder.create().
                match(AsyncTask.class, x->processAsyncTask(x))
                .build();
	}

	private void processAsyncTask(AsyncTask x) {
		workerActor.forward(x, getContext());
	}
	
	public static class ActorCreator implements Creator<DispatcherActor> {
        private static final long serialVersionUID = 1L;

        ITaskWorker taskWorker;
        
        public ActorCreator(ITaskWorker taskWorker) {
        	this.taskWorker = taskWorker;
        }

        @Override
        public DispatcherActor create() throws Exception {
            return new DispatcherActor(taskWorker);
        }
    }
}
