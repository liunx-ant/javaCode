package com.liunx.createcode.util;

public class ObjectRel {

	private String relClassName;
	private String relProperty;
	private String relToClassName;
	private String relToProperty;
	private String relType;
	private CreateCodeObject relObject;
	private CreateCodeObject relToObject;
	
    public String getRelClassName() {
        return relClassName;
    }
    public void setRelClassName(String relClassName) {
        this.relClassName = relClassName;
    }
    public String getRelProperty() {
        return relProperty;
    }
    public void setRelProperty(String relProperty) {
        this.relProperty = relProperty;
    }
    public String getRelToClassName() {
        return relToClassName;
    }
    public void setRelToClassName(String relToClassName) {
        this.relToClassName = relToClassName;
    }
    public String getRelToProperty() {
        return relToProperty;
    }
    public void setRelToProperty(String relToProperty) {
        this.relToProperty = relToProperty;
    }
    public CreateCodeObject getRelObject() {
        return relObject;
    }
    public void setRelObject(CreateCodeObject relObject) {
        this.relObject = relObject;
    }
    public CreateCodeObject getRelToObject() {
        return relToObject;
    }
    public void setRelToObject(CreateCodeObject relToObject) {
        this.relToObject = relToObject;
    }
    public String getRelType() {
        return relType;
    }
    public void setRelType(String relType) {
        this.relType = relType;
    }

}
