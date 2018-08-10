package com.sciov.cnicg.code.gui.entity;

import java.io.Serializable;

public class CodeDevice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6882500617732835996L;
	
	protected String name;
	protected String ip;
	protected String info;
	protected int allCount;
	protected int succCount;
	protected int errCount;
	
	public CodeDevice(String name, String info, String ip) {
		this.name = name;
		this.info = info;
		this.ip = ip;
		this.allCount = 0;
		this.succCount = 0;
		this.errCount = 0;
	}
	
	
	public void updateCount(String content, boolean isSucc) {
		allCount += 1;
		if(isSucc) {
			succCount += 1;
		}else {
			errCount += 1;
		}
	}
	
	
	
	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getInfo() {
		return info;
	}



	public void setInfo(String info) {
		this.info = info;
	}



	public int getAllCount() {
		return allCount;
	}



	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}



	public int getSuccCount() {
		return succCount;
	}



	public void setSuccCount(int succCount) {
		this.succCount = succCount;
	}



	public int getErrCount() {
		return errCount;
	}

	public void setErrCount(int errCount) {
		this.errCount = errCount;
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


	public void cleanData() {
		this.allCount = 0;
		this.succCount = 0;
		this.errCount = 0;
	}
	
	
}
