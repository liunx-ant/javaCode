package com.liunx.createword.util.word;

import java.util.ArrayList;
import java.util.List;

import com.liunx.createword.util.note.CodeNoteClass;

public class WordApp {

	private WordInfo wordInfo;

	private WordInfo mainApiWordInfo = new WordInfo();

	private List<CodeNoteClass> controllerCodeNoteClasses = new ArrayList<CodeNoteClass>();

	private List<CodeNoteClass> jsCodeNoteClasses = new ArrayList<CodeNoteClass>();

	private List<WordInfo> appVersionApiWordInfos = new ArrayList<WordInfo>();

	private List<WordInfo> appVersionApiQtWordInfos = new ArrayList<WordInfo>();

	public List<WordInfo> getAppVersionApiQtWordInfos() {
		return appVersionApiQtWordInfos;
	}

	public void setAppVersionApiQtWordInfos(List<WordInfo> appVersionApiQtWordInfos) {
		this.appVersionApiQtWordInfos = appVersionApiQtWordInfos;
	}

	public WordInfo getMainApiWordInfo() {
		return mainApiWordInfo;
	}

	public void setMainApiWordInfo(WordInfo mainApiWordInfo) {
		this.mainApiWordInfo = mainApiWordInfo;
	}

	public WordInfo getWordInfo() {
		return wordInfo;
	}

	public List<WordInfo> getAppVersionApiWordInfos() {
		return appVersionApiWordInfos;
	}

	public void setAppVersionApiWordInfos(List<WordInfo> appVersionApiWordInfos) {
		this.appVersionApiWordInfos = appVersionApiWordInfos;
	}

	public void setWordInfo(WordInfo wordInfo) {
		this.wordInfo = wordInfo;
	}

	public List<CodeNoteClass> getControllerCodeNoteClasses() {
		return controllerCodeNoteClasses;
	}

	public void setControllerCodeNoteClasses(List<CodeNoteClass> controllerCodeNoteClasses) {
		this.controllerCodeNoteClasses = controllerCodeNoteClasses;
	}

	public List<CodeNoteClass> getJsCodeNoteClasses() {
		return jsCodeNoteClasses;
	}

	public void setJsCodeNoteClasses(List<CodeNoteClass> jsCodeNoteClasses) {
		this.jsCodeNoteClasses = jsCodeNoteClasses;
	}

}
