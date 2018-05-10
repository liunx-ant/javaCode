package com.liunx.createword.util.word;

public class WrodTableObjectProperty {

	private String colName;
	private String name;
	private String type;
	private String colType;
	private String lenth;
	private Boolean isPk = false;
	private String dictType;
	private String title;

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		colType = colType.toUpperCase();
		if ("INTEGER".equals(colType)) {
			type = "Integer";
		} else if ("TIMESTAMP".equals(colType) || "DATETIME".equals(colType)) {
			type = "Date";
		} else if ("DECIMAL".equals(colType)) {
			type = "BigDecimal";
		} else {
			type = "String";
		}
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLenth() {
		return lenth;
	}

	public void setLenth(String lenth) {
		this.lenth = lenth;
	}

	public Boolean getIsPk() {
		return isPk;
	}

	public void setIsPk(Boolean isPk) {
		this.isPk = isPk;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColType() {
		if ("INT".equals(colType)) {
			colType = "INTEGER";
		}
		return colType;
	}

	public void setColType(String colType) {
		this.colType = colType;
	}

}
