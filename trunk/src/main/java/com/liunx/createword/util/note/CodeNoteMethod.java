package com.liunx.createword.util.note;

import java.util.List;

public class CodeNoteMethod {

	private String describe;

	private String name;

	private String author;

	private String version;

	private String date;

	private List<CodeNoteMethodParam> codeNoteParamList;

	private List<CodeNoteMethodThrows> codeNoteMethodThrowsList;

	private CodeNoteMethodReturn codeNoteReturn;

	private CodeNoteError codeNoteThrows;

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

	public CodeNoteError getCodeNoteThrows() {
		return codeNoteThrows;
	}

	public void setCodeNoteThrows(CodeNoteError codeNoteThrows) {
		this.codeNoteThrows = codeNoteThrows;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CodeNoteMethodReturn getCodeNoteReturn() {
		return codeNoteReturn;
	}

	public void setCodeNoteReturn(CodeNoteMethodReturn codeNoteReturn) {
		this.codeNoteReturn = codeNoteReturn;
	}

	public List<CodeNoteMethodParam> getCodeNoteParamList() {
		return codeNoteParamList;
	}

	public void setCodeNoteParamList(List<CodeNoteMethodParam> codeNoteParamList) {
		this.codeNoteParamList = codeNoteParamList;
	}

	public List<CodeNoteMethodThrows> getCodeNoteMethodThrowsList() {
		return codeNoteMethodThrowsList;
	}

	public void setCodeNoteMethodThrowsList(List<CodeNoteMethodThrows> codeNoteMethodThrowsList) {
		this.codeNoteMethodThrowsList = codeNoteMethodThrowsList;
	}
}
