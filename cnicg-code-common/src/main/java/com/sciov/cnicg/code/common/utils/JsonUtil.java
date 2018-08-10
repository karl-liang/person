package com.sciov.cnicg.code.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	public static String objToStr(Object object) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(object);
	}

	
}
