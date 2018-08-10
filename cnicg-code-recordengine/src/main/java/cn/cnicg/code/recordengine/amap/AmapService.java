package cn.cnicg.code.recordengine.amap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Component
public class AmapService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${amap.key}")
	private String amapKey;

	@Autowired
	IDistrictPersister geoFencingPersister;

//	@PostConstruct
//	public void init() {
//		DistrictResponse response = getDistrictResponse("中国","base");
//		if (!response.getStatus().equals("1")) {
//			return;
//		}
//		
//		processDistrictResponse(response, 0);
//
//	}

	
	
	public DistrictResponse getDistrictResponse(String districtName,String baseOrAll) {

		String url = "https://restapi.amap.com/v3/config/district?keywords=" + districtName + "&subdistrict=1&key="
				+ amapKey + "&extensions="+baseOrAll;
		String json = restTemplate.getForEntity(url, String.class).getBody();
		Gson gson = new GsonBuilder().serializeNulls().addDeserializationExclusionStrategy(new SimpleExclusionStrategy()).create();
		return gson.fromJson(json, DistrictResponse.class);
	}

	
	
	
	public void processDistrictResponse(DistrictResponse response, int parentId) {

		List<District> districtList = response.getDistricts();
		if(districtList != null) {
			for (District district : districtList) {
				processDistrict(district, parentId);
			}
		}
	}

	private void processDistrict(District district, int parentId) {
		String level = district.getLevel();
		if (LevelEnum.PROVINCE.match(level)) {
			processSubCountryDistrict(district, parentId);
		} else if (LevelEnum.CITY.match(level)) {
			processSubCountryDistrict(district, parentId);
		} else if (LevelEnum.DISTRICT.match(level)) {
			processDistrictDistrict(district, parentId);
		} else if (LevelEnum.COUNTRY.match(level)) {
			processSubCountryDistrict(district, parentId);
		}
	}

	private void processSubCountryDistrict(District district, int parentId) {
		int curId = 0;
		District perssitDistrict = geoFencingPersister.getDistrictByAdCode(district.getAdcode());
		if (perssitDistrict == null) {
			curId = geoFencingPersister.persistGeoFencing(district, parentId);
		}else {
			curId = perssitDistrict.getId();
		}
		List<District> districtList = district.getDistricts();
		for (District dt : districtList) {
			String adCode = dt.getAdcode();
			if(LevelEnum.STREET.match(dt.getLevel()) || adCode.equals(district.getAdcode())) {
				continue;
			}
			if(LevelEnum.DISTRICT.match(dt.getLevel()) && geoFencingPersister.isDistrictIn(adCode)) {
			}else {
				DistrictResponse response = getDistrictResponse(adCode,"all");
				processDistrictResponse(response, curId);
			}
		}

	}

	private void processDistrictDistrict(District district, int parentId) {
		geoFencingPersister.persistGeoFencing(district, parentId);
	}

//	public String inDistrict(String location) {
//		//locations=116.472407,39.993322,1484816232
//		String url = "https://restapi.amap.com/v4/geofence/status?key="+amapKey+
//				"&diu=860562031844564&locations="+location+","+(System.currentTimeMillis()/1000);
//		
//		String json = restTemplate.getForEntity(url, String.class).getBody();
//		return json;
//		
//	}
	
	
	public AntiGeoResponse getAntiGeoResponse(String location) {

		String url = "https://restapi.amap.com/v3/geocode/regeo?output=JSON&location=" +  location 
				+ "&key=" + amapKey  
				+ "&radius=3000&extensions=base";
		String json = restTemplate.getForEntity(url, String.class).getBody();
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.fromJson(json, AntiGeoResponse.class);
	}
	
	public byte[] mark(String locations,int zoom) {
		   
//	     String url = "https://restapi.amap.com/v3/staticmap?markers=mid,0xFF0000,A:"
//	     		+ locations +"&key="+amapKey;
		 String url = "https://restapi.amap.com/v3/staticmap?zoom="+zoom+"&size=1000*1000&paths=10,0x0000ff,1,,:"
		 		+ locations + "&key=" + amapKey;
	    	 
	     HttpHeaders headers = new HttpHeaders();
	     headers.setContentType(MediaType.IMAGE_PNG);
	     ResponseEntity<byte[]> response = restTemplate.exchange(
	    	        url,
	    	        HttpMethod.GET,
	    	        new HttpEntity<byte[]>(headers),
	    	        byte[].class);

	     byte[] result = response.getBody();
	     return result;
	}
	
//	public String makeGeofencing(String name, String points) {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("name", name);
//		params.put("points", points);
//		String url = "https://restapi.amap.com/v4/geofence/meta?key="+amapKey;
//		HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        Gson gson = new GsonBuilder().create();
//        HttpEntity<String> formEntity = new HttpEntity<String>(gson.toJson(params), headers);
//		String json = restTemplate.postForEntity(url, formEntity,String.class).getBody();
//		return json;
//	}
}
