package cn.cnicg.code.async;

import java.io.Serializable;

public class AsyncTask<T> implements Serializable{

	private T realTask;
	
	private String taskType;
	
	
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public T getRealTask() {
		return realTask;
	}
	
	public void setRealTask(T realTask) {
		this.realTask = realTask;
	}
}
