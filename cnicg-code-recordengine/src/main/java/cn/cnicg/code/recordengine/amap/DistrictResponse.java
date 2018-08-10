package cn.cnicg.code.recordengine.amap;

import java.util.List;

public class DistrictResponse extends BaseResponse{


	String count;
	List<District> districts;
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public List<District> getDistricts() {
		return districts;
	}
	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}
}
