package com.liunx.createword.util.note;

import java.util.List;

/**
 * 类注释
 * 
 * @author dwl
 * @version 1.0
 * @date 2016年8月5日 Copyright 青海粮食云项目组
 */

public class CodeNoteClass {
	// 包名
	private String packageName;
	// 名称
	private String name;
	// 描述
	private String describe = "";
	// 作者
	private String author;
	// 版本
	private String version;
	// 日期
	private String date;
	// 版权
	private String copyright;
	// 注释错误
	private CodeNoteError codeNoteError;
	// 方法
	private List<CodeNoteMethod> codeNoteMethods;
	// 文件所在的目录
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public CodeNoteError getCodeNoteError() {
		return codeNoteError;
	}

	public void setCodeNoteError(CodeNoteError codeNoteError) {
		this.codeNoteError = codeNoteError;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public List<CodeNoteMethod> getCodeNoteMethods() {
		return codeNoteMethods;
	}

	public void setCodeNoteMethods(List<CodeNoteMethod> codeNoteMethods) {
		this.codeNoteMethods = codeNoteMethods;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

}
