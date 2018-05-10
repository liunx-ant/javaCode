package com.liunx.createcode.config;

import java.io.File;

import com.liunx.common.util.PathUtil;
/**
  * 配置信息
  * @author liub
  * @version 1.0
  * @date 2017-3-28
 */
public class Config {
	// 包的默认配置
	private String apiPackageNamePrefix = "com.qhgrain.api.whou";
	private String appPackageNamePrefix = "com.qhgrain.app.whou";
	private String domainPackageNamePrefix = "com.qhgrain.api.whou.domain";
	//路径配置
	private String apiProjectPath = "E:/yihe/whou/maven.1468831208129/trunk";
	private String appProjectPath = "E:/yihe/whou/maven.1469003107214/trunk";
	private String domainProjectPath = "E:/yihe/whou/maven.1462430664164/trunk";
	
	private String ftlFolderPath = PathUtil.getClasspath() + "ftl";
	//xml生成路径 
	private String xmlFolderRootPath ="code/xml/";
	//项目结构
	protected final String g=File.separator;
	private String jspFolderRootPath =g+"src"+g+"main"+g+"webapp"+g+"WEB-INF"+g+"view"+g;
	private String jsFolderRootPath =g+"src"+g+"main"+g+"webapp"+g;
	private String javaFolderRootPath =g+"src"+g+"main"+g+"java"+g;
	private String resourcesFolderRootPath = g+"src"+g+"main"+g+"resources"+g;
	
    public String getApiPackageNamePrefix() {
        return apiPackageNamePrefix;
    }
    public void setApiPackageNamePrefix(String apiPackageNamePrefix) {
        this.apiPackageNamePrefix = apiPackageNamePrefix.trim();
    }
    public String getAppPackageNamePrefix() {
        return appPackageNamePrefix;
    }
    public void setAppPackageNamePrefix(String appPackageNamePrefix) {
        this.appPackageNamePrefix = appPackageNamePrefix.trim();
    }
    public String getDomainPackageNamePrefix() {
        return domainPackageNamePrefix;
    }
    public void setDomainPackageNamePrefix(String domainPackageNamePrefix) {
        this.domainPackageNamePrefix = domainPackageNamePrefix.trim();
    }
    public String getApiProjectPath() {
        return apiProjectPath;
    }
    public void setApiProjectPath(String apiProjectPath) {
        this.apiProjectPath = apiProjectPath.trim();
    }
    public String getAppProjectPath() {
        return appProjectPath;
    }
    public void setAppProjectPath(String appProjectPath) {
        this.appProjectPath = appProjectPath.trim();
    }
    public String getDomainProjectPath() {
        return domainProjectPath;
    }
    public void setDomainProjectPath(String domainProjectPath) {
        this.domainProjectPath = domainProjectPath.trim();
    }
    public String getFtlFolderPath() {
        return ftlFolderPath;
    }
    public void setFtlFolderPath(String ftlFolderPath) {
        this.ftlFolderPath = ftlFolderPath.trim();
    }
    public String getXmlFolderRootPath() {
        return xmlFolderRootPath;
    }
    public void setXmlFolderRootPath(String xmlFolderRootPath) {
        this.xmlFolderRootPath = xmlFolderRootPath.trim();
    }
    public String getJspFolderRootPath() {
        return jspFolderRootPath;
    }
    public void setJspFolderRootPath(String jspFolderRootPath) {
        this.jspFolderRootPath = jspFolderRootPath.trim();
    }
    public String getJsFolderRootPath() {
        return jsFolderRootPath;
    }
    public void setJsFolderRootPath(String jsFolderRootPath) {
        this.jsFolderRootPath = jsFolderRootPath.trim();
    }
    public String getJavaFolderRootPath() {
        return javaFolderRootPath;
    }
    public void setJavaFolderRootPath(String javaFolderRootPath) {
        this.javaFolderRootPath = javaFolderRootPath.trim();
    }
    public String getResourcesFolderRootPath() {
        return resourcesFolderRootPath;
    }
    public void setResourcesFolderRootPath(String resourcesFolderRootPath) {
        this.resourcesFolderRootPath = resourcesFolderRootPath.trim();
    }
	
}
