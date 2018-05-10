package com.liunx.createword.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liunx.common.util.BeanUtil;
import com.liunx.createword.util.CharDealUtil;
import com.liunx.createword.util.ConfigDBUtil;
import com.liunx.createword.util.word.WordApiMethod;
import com.liunx.createword.util.word.WordApiMethodParam;
import com.liunx.createword.util.word.WordApiProtocol;

/**
 * 类注释
 * 
 * @author dwl
 * @version 1.0
 * @date 2016年8月5日 Copyright 青海粮食云项目组
 */

public class WordApiProtocolService {

	/**
	 * 获得WordApiMethods
	 * 
	 * @param protocolName
	 * @param protocolApi
	 * @param testCase
	 */
	public static List<WordApiProtocol> getWordApiProtocols(String projectCode) throws SQLException {
		List<WordApiProtocol> wordApiProtocols = new ArrayList<WordApiProtocol>();
		Connection configConnection = ConfigDBUtil.getConfigConnection();
		Statement stmt = configConnection.createStatement();
		StringBuffer s = new StringBuffer();
		s.append("select protocol_name,protocol_type,protocol_api,test_case,version_type,version_code ");
		s.append(" from mod_service_version_protocol,mod_service_version,mod_service");
		s.append(" where mod_service_version_protocol.version_id=mod_service_version.version_id");
		s.append(" and mod_service.service_id=mod_service_version.service_id");
		s.append(" and mod_service_version.v_enabled=1");
		s.append(" and mod_service_version_protocol.protocol_type='rest'");
		s.append(" and mod_service.project_code='" + projectCode + "'");
		ResultSet rs = stmt.executeQuery(s.toString());
		while (rs.next()) {
			wordApiProtocols.add(getWordApiProtocol(rs.getString("protocol_name"), rs.getString("protocol_Type"), CharDealUtil.xmlCharDeal(rs.getString("protocol_api")), rs.getString("test_case"), rs.getString("version_type"), rs.getString("version_code")));
		}
		return wordApiProtocols;
	}

	/**
	 * 将wordApiMethods转换为Map
	 * 
	 * @param wordApiMethods
	 * @return
	 */

	public static List wordApiProtocolsToMap(List<WordApiProtocol> wordApiProtocols) {
		List wordApiProtocolsMap = new ArrayList();
		for (WordApiProtocol wordApiProtocol : wordApiProtocols) {
			Map wordApiProtocolMap = BeanUtil.objectToMap(wordApiProtocol);
			wordApiProtocolMap.put("wordApiMethods", wordApiMethodsToMap(wordApiProtocol.getWordApiMethods()));
			wordApiProtocolsMap.add(wordApiProtocolMap);
		}
		return wordApiProtocolsMap;
	}

	/**
	 * 将wordApiMethods转换为Map
	 * 
	 * @param wordApiMethods
	 * @return
	 */

	private static List wordApiMethodsToMap(List<WordApiMethod> wordApiMethods) {
		List wordApiMethodsMap = new ArrayList();
		for (WordApiMethod wordApiMethod : wordApiMethods) {
			wordApiMethodsMap.add(BeanUtil.objectToMap(wordApiMethod));
		}
		return wordApiMethodsMap;
	}

	/**
	 * 获得WordApiMethod
	 * 
	 * @param protocolName
	 * @param protocolApi
	 * @param testCase
	 */
	private static WordApiProtocol getWordApiProtocol(String protocolName, String protocolType, String protocolApi, String testCases, String versonType, String versonCode) {
		WordApiProtocol wordApiProtocol = new WordApiProtocol();
		List<WordApiMethod> wordApiMethods = new ArrayList<WordApiMethod>();
		JSONObject protocolApiJson = JSONArray.parseObject(protocolApi);
		wordApiProtocol.setProtocolName(protocolName);
		wordApiProtocol.setProtocolTilte(protocolNameDeal(protocolName));
		wordApiProtocol.setProtocolType(protocolType);
		wordApiProtocol.setVersonCode(versonCode);
		wordApiProtocol.setInterfacePath(protocolApiJson.getString("path"));
		JSONObject versonTypeJosn = JSONArray.parseObject(versonType);
		wordApiProtocol.setServicePath(versonTypeJosn.getString("servicePath"));
		JSONArray testCasesJson = JSONArray.parseArray(testCases);
		JSONArray methodJsons = protocolApiJson.getJSONArray("methods");
		for (int m = 0; m < methodJsons.size(); m++) {
			WordApiMethod wordApiMethod = new WordApiMethod();
			List<WordApiMethodParam> wordApiMethodParams = new ArrayList<WordApiMethodParam>();
			JSONObject methodJson = (JSONObject) methodJsons.get(m);
			wordApiMethod.setType(methodJson.getString("type"));
			wordApiMethod.setPath(methodJson.getString("path"));
			wordApiMethod.setName(methodJson.getString("name"));
			for (int t = 0; t < testCasesJson.size(); t++) {
				JSONObject testCaseJson = (JSONObject) testCasesJson.get(0);
				if (testCaseJson.getString("methodName") != null && testCaseJson.getString("methodName").equals(wordApiMethod.getName())) {
					if (testCaseJson.getString("output") != null) {
						wordApiMethod.setOutput(testCaseJson.getString("output"));
					} else {
						wordApiMethod.setOutput("");
					}
				}
			}
			wordApiMethod.setBusinessName(methodJson.getString("businessName"));
			wordApiMethod.setEncode(methodJson.getString("encode"));
			wordApiMethod.setDesc(methodJson.getString("desc"));
			wordApiMethod.setBusinessDesc(methodJson.getString("businessDesc"));
			wordApiMethod.setConsumes(methodJson.getString("consumes"));
			wordApiMethod.setReturnDesc(methodJson.getString("returnDesc"));
			wordApiMethod.setProduces(methodJson.getString("produces"));
			JSONArray params = methodJson.getJSONArray("params");
			for (int i = 0; i < params.size(); i++) {
				JSONObject wordApiMethodParamJson = (JSONObject) params.get(i);
				WordApiMethodParam wordApiMethodParam = new WordApiMethodParam();
				wordApiMethodParam.setName(wordApiMethodParamJson.getString("name"));
				wordApiMethodParam.setDesc(wordApiMethodParamJson.getString("desc"));
				wordApiMethodParam.setSource(wordApiMethodParamJson.getString("source"));
				wordApiMethodParam.setType(wordApiMethodParamJson.getString("type"));
				wordApiMethodParams.add(wordApiMethodParam);
			}
			wordApiMethod.setWordApiMethodParams(wordApiMethodParams);
			wordApiMethods.add(wordApiMethod);
		}
		wordApiProtocol.setWordApiMethods(wordApiMethods);
		return wordApiProtocol;
	}

	/**
	 * 将接口名称只保留最后一部分
	 * 
	 * @param protocolName
	 * @return
	 */
	private static String protocolNameDeal(String protocolName) {
		return protocolName.split("\\.")[protocolName.split("\\.").length - 1];
	}

	/**
	 * 将wordApiMethod转换为Map
	 * 
	 * @param wordApiMethod
	 * @return
	 */
	private static Map wordApiMethodToMap(WordApiMethod wordApiMethod) {
		Map wordApiMethodMap = BeanUtil.objectToMap(wordApiMethod);
		List wordApiMethodParamMapList = new ArrayList();
		for (WordApiMethodParam wordApiMethodParam : wordApiMethod.getWordApiMethodParams()) {
			wordApiMethodParamMapList.add(BeanUtil.objectToMap(wordApiMethodParam));
		}
		wordApiMethodMap.put("wordApiMethodParams", wordApiMethodParamMapList);
		return wordApiMethodMap;
	}
}
