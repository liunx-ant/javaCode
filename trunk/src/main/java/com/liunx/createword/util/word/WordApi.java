package com.liunx.createword.util.word;

import java.util.ArrayList;
import java.util.List;

import com.liunx.createword.util.note.CodeNoteClass;

public class WordApi {
	private WordInfo wordInfo;

	private String serviceProjectName;

	private String dbName;

	private List<WordApiProtocol> wordApiProtocols = new ArrayList<WordApiProtocol>();

	private List<CodeNoteClass> serviceCodeNoteClasses = new ArrayList<CodeNoteClass>();

	private List<CodeNoteClass> daoCodeNoteClasses = new ArrayList<CodeNoteClass>();

	private List<CodeNoteClass> entityCodeNoteClasses = new ArrayList<CodeNoteClass>();

	private List<WordTableObject> wordTableObjects = new ArrayList<WordTableObject>();

	public List<WordApiProtocol> getWordApiProtocols() {
		return wordApiProtocols;
	}

	public void setWordApiProtocols(List<WordApiProtocol> wordApiProtocols) {
		this.wordApiProtocols = wordApiProtocols;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getServiceProjectName() {
		return serviceProjectName;
	}

	public void setServiceProjectName(String serviceProjectName) {
		this.serviceProjectName = serviceProjectName;
	}

	public List<CodeNoteClass> getEntityCodeNoteClasses() {
		return entityCodeNoteClasses;
	}

	public void setEntityCodeNoteClasses(List<CodeNoteClass> entityCodeNoteClasses) {
		this.entityCodeNoteClasses = entityCodeNoteClasses;
	}

	public List<CodeNoteClass> getServiceCodeNoteClasses() {
		return serviceCodeNoteClasses;
	}

	public void setServiceCodeNoteClasses(List<CodeNoteClass> serviceCodeNoteClasses) {
		this.serviceCodeNoteClasses = serviceCodeNoteClasses;
	}

	public List<CodeNoteClass> getDaoCodeNoteClasses() {
		return daoCodeNoteClasses;
	}

	public void setDaoCodeNoteClasses(List<CodeNoteClass> daoCodeNoteClasses) {
		this.daoCodeNoteClasses = daoCodeNoteClasses;
	}

	public WordInfo getWordInfo() {
		return wordInfo;
	}

	public void setWordInfo(WordInfo wordInfo) {
		this.wordInfo = wordInfo;
	}

	public List<WordTableObject> getWordTableObjects() {
		return wordTableObjects;
	}

	public void setWordTableObjects(List<WordTableObject> wordTableObjects) {
		this.wordTableObjects = wordTableObjects;
	}

}
