package com.liunx.createword.util;

import java.util.List;

public class CreateWordConfig {
	// 所要生成的文档是app还是api 阈值[app,api]
	public String wrodType = "";

	// app 或api manager中的项目编码
	public String projectCode = "";

	// 生成app或api文档时，所需SERVICE类的文件目录
	public List<String> serviceFilePaths;

	// 生成app或api文档时，所需DAO类的文件目录
	public List<String> daoFilePaths;

	// 生成app或api文档时，所需COTROLLOR类的文件目录
	public List<String> controllerFilePaths;

	// 生成api文档时，所需实体类的文件目录
	public List<String> entityFilePaths;

	// 生成app文档时，所需的js文件目录
	public List<String> jsFilePaths;

	public String getWrodType() {
		return wrodType;
	}

	public void setWrodType(String wrodType) {
		this.wrodType = wrodType;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public List<String> getServiceFilePaths() {
		return serviceFilePaths;
	}

	public void setServiceFilePaths(List<String> serviceFilePaths) {
		this.serviceFilePaths = serviceFilePaths;
	}

	public List<String> getDaoFilePaths() {
		return daoFilePaths;
	}

	public void setDaoFilePaths(List<String> daoFilePaths) {
		this.daoFilePaths = daoFilePaths;
	}

	public List<String> getControllerFilePaths() {
		return controllerFilePaths;
	}

	public void setControllerFilePaths(List<String> controllerFilePaths) {
		this.controllerFilePaths = controllerFilePaths;
	}

	public List<String> getEntityFilePaths() {
		return entityFilePaths;
	}

	public void setEntityFilePaths(List<String> entityFilePaths) {
		this.entityFilePaths = entityFilePaths;
	}

	public List<String> getJsFilePaths() {
		return jsFilePaths;
	}

	public void setJsFilePaths(List<String> jsFilePaths) {
		this.jsFilePaths = jsFilePaths;
	}

}
