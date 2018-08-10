package com.sciov.cnicg.code.gui.entity;

public class WorkPositionJob extends CodeDevice {
	
	int targetAccount = 0;

	public WorkPositionJob(String name, String info, String ip, int targetAccount) {
		super(name, info, ip);
		this.targetAccount = targetAccount;
	}

	public int getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(int targetAccount) {
		this.targetAccount = targetAccount;
	}
	
	public boolean equals(Object object) {
		if(object instanceof CodeDevice) {
			CodeDevice target = (CodeDevice)object;
			return target.name.equals(name);
		}
		return false;
	}
	
	public int hashCode() {
		return name.hashCode();
	}

}
