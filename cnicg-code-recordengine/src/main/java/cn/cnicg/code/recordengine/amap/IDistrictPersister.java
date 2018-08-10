package cn.cnicg.code.recordengine.amap;

import java.util.Map;

import cn.cnicg.code.recordengine.amap.District;

public interface IDistrictPersister {

	public int persistGeoFencing(District district, int parentId);
	
	public boolean isDistrictIn(String adCode);
	
	public District getDistrictByAdCode(String adCode);
	
	public District getDistrictByAdCodeWithPolyline(String adCode);
	
	public Map<String, String> getIncludeDistrict(String adcode);
}
