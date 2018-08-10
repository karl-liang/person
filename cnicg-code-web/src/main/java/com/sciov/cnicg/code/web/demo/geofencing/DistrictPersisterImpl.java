package com.sciov.cnicg.code.web.demo.geofencing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sciov.cnicg.code.module.bean.DistrictExample;
import com.sciov.cnicg.code.persist.mapper.DistrictMapper;

import cn.cnicg.code.recordengine.amap.District;
import cn.cnicg.code.recordengine.amap.IDistrictPersister;

@Service
public class DistrictPersisterImpl implements IDistrictPersister {

	@Autowired
	DistrictMapper districtMapper;
	
	@Override
	public int persistGeoFencing(District district, int parentId) {
		com.sciov.cnicg.code.module.bean.District persistEntity = toPersistEntity(district, parentId);
		districtMapper.insert(persistEntity);
		return getDistrictByAdCode(district.getAdcode()).getId();
	}


	@Override
	public boolean isDistrictIn(String adCode) {
		return getDistrictByAdCode(adCode) != null;
	}

	@Override
	public District getDistrictByAdCode(String adCode) {
		DistrictExample districtExample = new DistrictExample();
		DistrictExample.Criteria criteria = districtExample.createCriteria();
		criteria.andAdcodeEqualTo(adCode);
		List<com.sciov.cnicg.code.module.bean.District> list = districtMapper.selectByExample(districtExample);
		if(list != null && list.size() > 0) {
			return toDistrict(list.get(0));
		}
		return null;
	}

	private District toDistrict(com.sciov.cnicg.code.module.bean.District district) {
		District result = new District();
		result.setAdcode(district.getAdcode());
		result.setCenter(district.getCenter());
		result.setCitycode(district.getCityCode());
		result.setDistricts(null);
		result.setId(district.getId());
		result.setLevel(district.getLevel());
		result.setName(district.getName());
		result.setPolyline(district.getPolyLine());
		return result;
	}
	
	private com.sciov.cnicg.code.module.bean.District toPersistEntity(District district, int parentId) {
		com.sciov.cnicg.code.module.bean.District result = new com.sciov.cnicg.code.module.bean.District();
		result.setAdcode(district.getAdcode());
		result.setCenter(district.getCenter());
		if(district.getCitycode() == null) {
			district.setCitycode("0");
		}
		result.setCityCode(district.getCitycode());
		result.setLevel(district.getLevel());
		result.setName(district.getName());
		result.setParentId(parentId);
		result.setPolyLine(district.getPolyline());
		return result;
	}


	@Override
	public District getDistrictByAdCodeWithPolyline(String adCode) {
		DistrictExample districtExample = new DistrictExample();
		DistrictExample.Criteria criteria = districtExample.createCriteria();
		criteria.andAdcodeEqualTo(adCode);
		List<com.sciov.cnicg.code.module.bean.District> list = districtMapper.selectByExampleWithBLOBs(districtExample);
		if(list != null && list.size() > 0) {
			return toDistrict(list.get(0));
		}
		return null;
	}


	@Override
	public Map<String, String> getIncludeDistrict(String adcode) {
		DistrictExample districtExample = new DistrictExample();
		DistrictExample.Criteria criteria = districtExample.createCriteria();
		criteria.andAdcodeEqualTo(adcode);
		List<com.sciov.cnicg.code.module.bean.District> list = districtMapper.selectByExample(districtExample);
		Map<String, String> result = new HashMap<String,String>();
		if(list != null && list.size() > 0) {
			com.sciov.cnicg.code.module.bean.District district = list.get(0);
			result.put(district.getAdcode(),district.getName());
			int parentId = district.getParentId();
			if(parentId != 0) {
				district = districtMapper.selectByPrimaryKey(parentId);
				result.putAll(getIncludeDistrict(district.getAdcode()));
			}
		}
		return result;
	}

}
