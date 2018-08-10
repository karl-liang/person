package com.sciov.cnicg.code.gui.service;

import java.util.List;

import com.sciov.cnicg.code.gui.entity.CodeDevice;
import com.sciov.cnicg.code.gui.entity.WorkPositionJob;

public interface ICodeDeviceService {

	public List<WorkPositionJob> initCodeDevice();
	
	public boolean updateDeviceStatus(String content);
	
	public void setCurrentCodeDevice(String name);
	
	public WorkPositionJob getCurrentCodeDevice();

	public boolean addDevice(String deviceName, String ip, String info, int targetAccountValue) ;

	public void deleteDevice(String lastPathComponent);

	public void setDeviceFile(String deviceFile);

	public void cleanData();

	public String getDeviceName(String content);
	
	public boolean isAllCodeDevice(CodeDevice cd);
}
