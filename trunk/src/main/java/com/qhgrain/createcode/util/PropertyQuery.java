package com.qhgrain.createcode.util;

public class PropertyQuery {

	private Boolean isExist=false;
	// --showType:radio/checkbox/text/hidden/select/words
	private String showType="";
	// --queryType:equal[等于]/in[in]/interval[区间]/greaterThan[大于]/greaterThanOrEqual[大于等于]/lessThan[小于]/lessThanOrEqual[小于等于]
	private String queryType="";

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

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

}
