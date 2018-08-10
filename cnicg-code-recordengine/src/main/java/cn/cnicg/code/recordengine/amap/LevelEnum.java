package cn.cnicg.code.recordengine.amap;

public enum LevelEnum {

	COUNTRY("country"),
	PROVINCE("province"),
	CITY("city"),
	DISTRICT("district"),
	STREET("street"),
	
	;
	
	String level;
	
	private LevelEnum(String level) {
		this.level = level;
	}
	
	public boolean match(String level) {
		return this.level.equals(level);
	}
	
}
