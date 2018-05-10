package com.liunx.createcode.util;

public class PropertyEdit {

	private Boolean isExist=false;

	// --showType:radio/checkbox/text/hidden/select/words
	private String showType="";

	private Boolean isMust=false;

	public Boolean getIsExist() {
		return isExist;
	}

	public void setIsExist(Boolean isExist) {
		this.isExist = isExist;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public Boolean getIsMust() {
		return isMust;
	}

	public void setIsMust(Boolean isMust) {
		this.isMust = isMust;
	}

}
