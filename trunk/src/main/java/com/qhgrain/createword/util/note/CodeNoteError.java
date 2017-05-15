package com.qhgrain.createword.util.note;

import java.util.List;

public class CodeNoteError {
	private String describe;

	private String name;

	private List<CodeNoteMethodParam> codeNoteParamList;

	private List<CodeNoteMethodThrows> codeNoteMethodThrowsList;

	private CodeNoteMethodReturn codeNoteReturn;

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
