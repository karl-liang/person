package com.sciov.cnicg.code.web.demo.geofencing;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sciov.cnicg.code.persist.mapper.GeoFencingMapper;

import cn.cnicg.code.recordengine.amap.AmapService;
import cn.cnicg.code.recordengine.amap.AntiGeoResponse;
import cn.cnicg.code.recordengine.amap.District;
import cn.cnicg.code.recordengine.amap.IDistrictPersister;

@Service
public class GeofencingService {

	@Autowired
	GeoFencingMapper geoFencingMapper;

	@Autowired
	AmapService amapService;

	@Autowired
	IDistrictPersister districtPersister;

	// public String makeGeoFencing(String adcode) {
	// District district =
	// districtPersister.getDistrictByAdCodeWithPolyline(adcode);
	//
	// String polyline = district.getPolyline();
	// if(polyline != null) {
	// String[] points = polyline.split(";");
	// if(points.length<5000) {
	// return
	// amapService.makeGeofencing("geofencing"+adcode+System.currentTimeMillis(),
	// polyline);
	// }
	// }
	//
	// return "fail";
	// }

	// public String inGeoFencing(String location) {
	// return amapService.inDistrict( location);
	// }

	public String inArea(String adcode, String location) {
		AntiGeoResponse position = amapService.getAntiGeoResponse(location);
		String result;
		Map<String, String> valideCodeMap = districtPersister.getIncludeDistrict(adcode);
		StringBuilder sb = new StringBuilder();
		Iterator<String> keyIterator = valideCodeMap.keySet().iterator();
		String targetLocation = "";
		while(keyIterator.hasNext()) {
			String code = keyIterator.next();
			if(code.equals(adcode) ) {
				targetLocation = valideCodeMap.get(code);
			}
			
			if(code.equals(position.getRegeocode().getAddressComponent().getAdcode())) {
				targetLocation = valideCodeMap.get(code);
				return String.format("坐标：%s 解析地址 %s 位于指定查找区域 %s",location, position.getRegeocode().getFormatted_address(),targetLocation);
			}
		}
		
		
		
		return String.format("坐标：%s 解析地址 %s 不在指定查找区域 %s",location, position.getRegeocode().getFormatted_address(),targetLocation);
	}
}
