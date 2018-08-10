package com.sciov.lh.mybatis;

import java.util.Properties;

import org.mybatis.generator.api.IntrospectedTable;

public class PluginUtil {
	public static String getVofullQualif(Properties properties, IntrospectedTable introspectedTable) {
		String fullQualif = getVoTargetPackage(properties) + "."
				+ introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Vo";
		return fullQualif;
	}
	
	public static String getVoTargetPackage(Properties properties) {
		return properties.getProperty("voTargetPackage");
	}
	
	public static String getBeanTargetPackage(Properties properties) {
		return properties.getProperty("beanTargetPackage");
	}
	
	public static String getTargetPackage(Properties properties) {
		return properties.getProperty("targetPackage");
	}
	
	public static String getMapperTargetPacakge(Properties properties) {
		return properties.getProperty("mapperTargetPackage");
	}
	
	public static String getTargetProject(Properties properties) {
		return properties.getProperty("targetProject");
	}
	
	
	public static String getControllerTargetProject(Properties properties) {
		return properties.getProperty("controllerTargetProject");
	}
	public static String getControllerTargetPackage(Properties properties) {
		return properties.getProperty("controllerTargetPackage");
	}
}
