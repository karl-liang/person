package cn.cnicg.code.recordengine.enums;

public enum DecisionResultEnum {

	SUCC("succ",false,""),
	WARN("warn",false,""),
	IGNORE("ignore",false,""),
	REJECT("reject",true,"");
	
	
	String key;
	String desc;
	boolean finish;
	
	DecisionResultEnum(String key, boolean finish, String desc) {
		this.key = key;
		this.desc = desc;
		this.finish = finish;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	
	
}
