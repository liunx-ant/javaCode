package com.liunx.createword.util.word;

import java.util.ArrayList;
import java.util.List;

public class WordTableObject {

	private String domainPackageName;
	private String appPackageName;
	private String apiPackageName;
	private String apiRelativePath;
	private String appRelativePath;
	private String domainRelativePath;
	private String className;
	private String tableName;
	private String title;
	private Boolean isMain;
	private String objectName;
	private List<WrodTableObjectProperty> properties = new ArrayList<WrodTableObjectProperty>();

	public Boolean getIsMain() {
		return isMain;
	}

	public void setIsMain(Boolean isMain) {
		this.isMain = isMain;
	}

	public List<WrodTableObjectProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<WrodTableObjectProperty> properties) {
		this.properties = properties;
	}

	public String getDomainPackageName() {
		return domainPackageName;
	}

	public void setDomainPackageName(String domainPackageName) {
		this.domainPackageName = domainPackageName;
	}

	public String getAppPackageName() {
		return appPackageName;
	}

	public void setAppPackageName(String appPackageName) {
		this.appPackageName = appPackageName;
	}

	public String getApiPackageName() {
		return apiPackageName;
	}

	public void setApiPackageName(String apiPackageName) {
		this.apiPackageName = apiPackageName;
	}

	public String getApiRelativePath() {
		return apiPackageName.replaceAll("\\.", "/");
	}

	public void setApiRelativePath(String apiRelativePath) {
		this.apiRelativePath = apiRelativePath;
	}

	public String getAppRelativePath() {
		return appPackageName.replaceAll("\\.", "/");
	}

	public void setAppRelativePath(String appRelativePath) {
		this.appRelativePath = appRelativePath;
	}

	public String getDomainRelativePath() {
		return domainPackageName.replaceAll("\\.", "/");
	}

	public void setDomainRelativePath(String domainRelativePath) {
		this.domainRelativePath = domainRelativePath;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getObjectName() {
		objectName = "";
		if (className != null && className.length() > 0) {
			objectName = className.substring(0, 1).toLowerCase() + className.substring(1);
		}
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
}
