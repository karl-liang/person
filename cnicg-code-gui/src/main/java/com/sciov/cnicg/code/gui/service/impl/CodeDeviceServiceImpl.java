package com.sciov.cnicg.code.gui.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;

import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Lists;
import com.sciov.cnicg.code.gui.entity.CodeDevice;
import com.sciov.cnicg.code.gui.entity.WorkPositionJob;
import com.sciov.cnicg.code.gui.service.ICodeDeviceService;
import com.sciov.cnicg.code.gui.service.IDeviceCheckService;
import com.sciov.cnicg.code.gui.util.HttpUtil;

public class CodeDeviceServiceImpl implements ICodeDeviceService,IDeviceCheckService {

	private WorkPositionJob allDevice = new WorkPositionJob("all!@#$%", "全部设备", "", 0);

	private WorkPositionJob currentDevice;

	private Map<String, WorkPositionJob> deviceMap = null;
	
	private String deviceFile = "";

	private static CodeDeviceServiceImpl impl = new CodeDeviceServiceImpl();

	public static ICodeDeviceService getCodeDeviceService() {
		return impl;
	}

	public List<WorkPositionJob> initCodeDevice() {
		if(deviceMap == null) {
			readDevice();
		}
		if (deviceMap.size() > 0) {
			return Lists.newArrayList(deviceMap.values().iterator());
		} else {
			return new ArrayList<WorkPositionJob>();
		}
	}

	/**
	 * boolean 收到内容是否是当前选中返回的设备信息
	 */
	public boolean updateDeviceStatus(String content) {
		if(currentDevice == null) {
			currentDevice = allDevice;
		}
		
		String[] strArray = content.split(",");
		if(strArray.length < 2) {
			allDevice.updateCount(content, false);
			return currentDevice.equals(allDevice);
		}else {
			String deviceName = strArray[0];
			String codeValue = strArray[1];

			CodeDevice cd = deviceMap.get(deviceName);
			if(cd == null) {
				return currentDevice.equals(allDevice);
			}
			if(!cd.equals(allDevice)) {
				cd.updateCount(codeValue, isContentSucc(codeValue));
			}
			allDevice.updateCount(codeValue, isContentSucc(codeValue) );
			return cd.equals(currentDevice) || currentDevice.equals(allDevice);
		}
	}

	public boolean isContentSucc(String content) {
		return true;
	}

	private boolean currentIsAll() {
		return currentDevice.equals(allDevice);
	}

	public void setCurrentCodeDevice(String name) {
		if(name == null) {
			currentDevice = allDevice;
		}else {
			currentDevice = deviceMap.get(name);
			if (currentDevice == null) {
				currentDevice = allDevice;
			}
		}
	}

	public WorkPositionJob getCurrentCodeDevice() {
		if (currentDevice == null) {
			currentDevice = allDevice;
		}
		return currentDevice;
	}

	private void persist() throws IOException {

//		URL url = CodeDeviceServiceImpl.class.getResource("/device.bin");
		try {
			
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(deviceFile));
			o.writeObject(deviceMap); // 写入数据
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readDevice() {
		try {
			File file = new File(deviceFile);
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			//URL url = CodeDeviceServiceImpl.class.getResource("/device.bin");
			//ObjectInputStream in = new ObjectInputStream(url.openStream());
			deviceMap = (Map<String, WorkPositionJob>) in.readObject(); // 读取数据
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (deviceMap == null) {
			deviceMap = new HashMap<String, WorkPositionJob>();
		}
	}

	@Override
	public boolean addDevice(String deviceName, String ip, String info, int targetAccount) {
		
		WorkPositionJob cd = new WorkPositionJob(deviceName,info, ip, targetAccount);
		if(deviceMap.values().contains(cd)) {
			return false;
		}
		deviceMap.put(deviceName, cd);
		try {
			this.persist();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void deleteDevice(String lastPathComponent) {
		deviceMap.remove(lastPathComponent);
		try {
			this.persist();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setDeviceFile(String deviceFile) {
		this.deviceFile = deviceFile;
	}

	@Override
	public void cleanData() {
		for(CodeDevice codeDevice:deviceMap.values()) {
			codeDevice.cleanData();
		}
		allDevice.cleanData();
	}

	@Override
	public String getDeviceName(String content) {
		String[] strArray = content.split(",");
		if(strArray.length < 2) {
			return "未知设备";
		}else {
			String deviceName = strArray[0];
			return deviceName;
		}
	}

	@Override
	public boolean isAllCodeDevice(CodeDevice cd) {
		return cd.equals(allDevice);
	}

	@Override
	public boolean isDeivceOnline(CodeDevice cd) {
		if(StringUtil.isNotEmpty(cd.getIp())) {
			try {
				HttpEntity entity = HttpUtil.doGet(cd.getIp(), null);
				if(entity != null) {
					return true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
