package com.qhgrain.createword.util.word;

import java.util.ArrayList;
import java.util.List;

public class WordApiProtocol {
	private String protocolName;
	private String protocolType;
	private String protocolDesc = "";
	private String protocolTilte;
	private String versonCode = "";
	private String servicePath;
	private String interfacePath;

	public String getProtocolTilte() {
		return protocolTilte;
	}

	public void setProtocolTilte(String protocolTilte) {
		this.protocolTilte = protocolTilte;
	}

	public String getProtocolDesc() {
		return protocolDesc;
	}

	public void setProtocolDesc(String protocolDesc) {
		this.protocolDesc = protocolDesc;
	}

	public String getVersonCode() {
		return versonCode;
	}

	public void setVersonCode(String versonCode) {
		this.versonCode = versonCode;
	}

	public String getServicePath() {
		return servicePath;
	}

	public void setServicePath(String servicePath) {
		this.servicePath = servicePath;
	}

	public String getInterfacePath() {
		return interfacePath;
	}

	public void setInterfacePath(String interfacePath) {
		this.interfacePath = interfacePath;
	}

	private List<WordApiMethod> wordApiMethods = new ArrayList<WordApiMethod>();

	public String getProtocolName() {
		return protocolName;
	}

	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

	public List<WordApiMethod> getWordApiMethods() {
		return wordApiMethods;
	}

	public void setWordApiMethods(List<WordApiMethod> wordApiMethods) {
		this.wordApiMethods = wordApiMethods;
	}

}
