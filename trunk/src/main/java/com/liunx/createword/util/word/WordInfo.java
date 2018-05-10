package com.liunx.createword.util.word;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WordInfo {
	private String projectName = "";
	private String projectCode = "";
	private String desc = "";
	private String version = "";
	private String author = "";
	private String title = "";
	private Date createDate;
	private String createDateCn;
	private String fileName = "";
	private String serviceName = "";
	private String codeProjectName = "";
	private String serviceSource = "";

 

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getServiceSource() {
		return serviceSource;
	}

	public void setServiceSource(String serviceSource) {
		this.serviceSource = serviceSource;
	}

	public String getCodeProjectName() {
		return codeProjectName;
	}

	public void setCodeProjectName(String codeProjectName) {
		this.codeProjectName = codeProjectName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateDateCn() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		if (this.getCreateDate() != null) {
			createDateCn = formatter.format(this.getCreateDate());
		}
		return createDateCn;
	}

	public void setCreateDateCn(String createDateCn) {
		this.createDateCn = createDateCn;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
