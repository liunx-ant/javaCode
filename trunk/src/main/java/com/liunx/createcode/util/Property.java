package com.liunx.createcode.util;

public class Property {

	private String colName;
	private String name;
	private String type;
	private String colType;
	private String lenth;
	private Boolean isPk=false;
	private String dictType;
	private String title;
	private PropertyEdit propertyEdit=new PropertyEdit();
	private PropertyNew propertyNew=new PropertyNew ();
	private PropertyList propertyList= new PropertyList ();
	private PropertyQuery propertyQuery=new PropertyQuery ();
	private PropertyView propertyView=new PropertyView ();
	 
	public PropertyEdit getPropertyEdit() {
		return propertyEdit;
	}

	public void setPropertyEdit(PropertyEdit propertyEdit) {
		this.propertyEdit = propertyEdit;
	}

	public PropertyNew getPropertyNew() {
		return propertyNew;
	}

	public void setPropertyNew(PropertyNew propertyNew) {
		this.propertyNew = propertyNew;
	}

	public PropertyList getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(PropertyList propertyList) {
		this.propertyList = propertyList;
	}

	public PropertyQuery getPropertyQuery() {
		return propertyQuery;
	}

	public void setPropertyQuery(PropertyQuery propertyQuery) {
		this.propertyQuery = propertyQuery;
	}

	public PropertyView getPropertyView() {
		return propertyView;
	}

	public void setPropertyView(PropertyView propertyView) {
		this.propertyView = propertyView;
	}

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
	    colType=colType.toUpperCase();
	    if ("INTEGER".equals(colType) || "TINYINT".equals(colType) ) {
	        type="Integer";
        }else if("TIMESTAMP".equals(colType)||"DATETIME".equals(colType)) {
            type="Date";
        }else if("DECIMAL".equals(colType)) {
            type="BigDecimal";
        }else if("BIGINT".equals(colType)) {
        	type="Long";
        }else{
            type="String";
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
            colType="INTEGER";
        }
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

}
