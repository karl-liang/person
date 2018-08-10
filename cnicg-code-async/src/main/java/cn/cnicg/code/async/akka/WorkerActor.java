package cn.cnicg.code.async.akka;


import akka.actor.AbstractActor;
import akka.japi.Creator;
import akka.japi.pf.ReceiveBuilder;
import cn.cnicg.code.async.AsyncTask;
import cn.cnicg.code.async.ITaskWorker;

public class WorkerActor extends AbstractActor {

	ITaskWorker taskWorker;
	
	public WorkerActor(ITaskWorker taskWorker) {
		this.taskWorker = taskWorker;
	}
	
	@Override
	public Receive createReceive() {
		return ReceiveBuilder.create().
                match(AsyncTask.class, x->processAsyncTask(x))
                .build();
	}

	private void processAsyncTask(AsyncTask task) {
		taskWorker.processAsyncTask(task);
	}
	
	public static class ActorCreator implements Creator<WorkerActor> {
        private static final long serialVersionUID = 1L;

        ITaskWorker taskWorker;
        
        public ActorCreator(ITaskWorker taskWorker) {
        	this.taskWorker = taskWorker;
        }

        @Override
        public WorkerActor create() throws Exception {
            return new WorkerActor(taskWorker);
        }
    }
}
