package com.qhgrain.createword.util.word;

import java.util.ArrayList;
import java.util.List;

public class WordApiMethod {
	private String type;
	private String path;
	private String name;
	private String businessName;
	private String encode;
	private String desc;
	private String businessDesc;
	private String consumes;// 方法响应返回类型
	private String returnDesc;// 方法响应字段说明
	private String produces="";// 方法响应返回类型
	private String output="";
	private List<WordApiMethodParam> wordApiMethodParams = new ArrayList<WordApiMethodParam>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBusinessDesc() {
		return businessDesc;
	}

	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
	}

	public String getConsumes() {
		return consumes;
	}

	public void setConsumes(String consumes) {
		this.consumes = consumes;
	}

	public String getReturnDesc() {
		return returnDesc;
	}

	public void setReturnDesc(String returnDesc) {
		this.returnDesc = returnDesc;
	}

	public String getProduces() {
		return produces;
	}

	public void setProduces(String produces) {
		this.produces = produces;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public List<WordApiMethodParam> getWordApiMethodParams() {
		return wordApiMethodParams;
	}

	public void setWordApiMethodParams(List<WordApiMethodParam> wordApiMethodParams) {
		this.wordApiMethodParams = wordApiMethodParams;
	}

}
