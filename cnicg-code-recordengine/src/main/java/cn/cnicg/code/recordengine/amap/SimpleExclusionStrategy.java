package cn.cnicg.code.recordengine.amap;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class SimpleExclusionStrategy implements ExclusionStrategy {

	
	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		if(f.getName().equals("citycode") ) {
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

}
