package com.qhgrain.createword.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qhgrain.common.util.BeanUtil;
import com.qhgrain.createword.config.Config;
import com.qhgrain.createword.util.CharDealUtil;
import com.qhgrain.createword.util.ConfigDBUtil;
import com.qhgrain.createword.util.word.WordApiMethod;
import com.qhgrain.createword.util.word.WordApiMethodParam;
import com.qhgrain.createword.util.word.WordInfo;

/**
 * 类注释
 * 
 * @author dwl
 * @version 1.0
 * @date 2016年8月5日 Copyright 青海粮食云项目组
 */

public class WordInfoService {

	/**
	 * 获得转换为map的wordInfo
	 * 
	 * @return
	 */
	public Map getWordInfoMap(WordInfo wordInfo) {
		return wordInfoToMap(wordInfo);
	}

	/**
	 * 将wordInfo转为Map
	 * 
	 * @param wordInfo
	 * @return
	 */
	private Map<String, Object> wordInfoToMap(WordInfo wordInfo) {
		Map<String, Object> map = (Map<String, Object>) BeanUtil.objectToMap(wordInfo);
		map.put("createDateCn", wordInfo.getCreateDateCn());
		map.put("wordInfo", wordInfo);
		return map;
	}

	/**
	 * 获得文档信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public WordInfo getWordInfo() throws Exception {
		WordInfo wordInfo = new WordInfo();
		if (Config.WORD_TYPE.equals("app")) {
			wordInfo = getAppWordInfo(Config.PROJECT_CODE);
		}
		if (Config.WORD_TYPE.equals("api")) {
			wordInfo = getApiWordInfo(Config.PROJECT_CODE);
		}
		return wordInfo;
	}

	/**
	 * 获得appVersionApiWordInfos
	 * 
	 * @param projectCode
	 * @return
	 * @throws SQLException
	 */
	public static List<WordInfo> getAppVersionApiWordInfos(String projectCode) throws SQLException {
		List<WordInfo> appVersionApiWordInfos = new ArrayList<WordInfo>();
		Connection configConnection = ConfigDBUtil.getConfigConnection();
		Statement stmt = configConnection.createStatement();
		StringBuffer s = new StringBuffer();
		s.append("select  mod_service.project_code,mod_service.service_name,mod_service.project_name ");
		s.append(" from mod_app,mod_app_version_api,mod_service,mod_service_version");
		s.append(" where mod_app_version_api.app_id=mod_app.app_id");
		s.append(" and mod_app_version_api.service_id=mod_service.service_id");
		s.append(" and mod_app_version_api.service_version_id=mod_service_version.version_id");
		s.append(" and mod_app.project_code='" + projectCode + "'");
		ResultSet rs = stmt.executeQuery(s.toString());
		while (rs.next()) {
			appVersionApiWordInfos.add(getApiWordInfo(rs.getString("project_code")));
		}
		return appVersionApiWordInfos;
	}

	/**
	 * 通过项目编码获得serviceId
	 * 
	 * @param projectCode
	 * @return
	 * @throws SQLException
	 */
	public static String getServiceId(String projectCode) throws Exception {
		String serviceId = "";
		Connection configConnection = ConfigDBUtil.getConfigConnection();
		Statement stmt = configConnection.createStatement();
		StringBuffer s = new StringBuffer();
		s.append("select mod_service.service_id ");
		s.append(" from  mod_service");
		s.append(" where mod_service.project_code='" + projectCode + "'");
		ResultSet rs = stmt.executeQuery(s.toString());
		while (rs.next()) {
			serviceId = rs.getString("service_id");
		}
		if (serviceId.equals("")) {
			throw new Exception("提供的API项目编码错误，无法获取api manager 信息！");
		}
		return serviceId;
	}

	/**
	 * 获得API的wordInfo
	 * 
	 * @param projectCode
	 * @return
	 * @throws SQLException
	 */
	public static WordInfo getApiWordInfo(String projectCode) throws SQLException {
		WordInfo wordInfo = new WordInfo();
		Connection configConnection = ConfigDBUtil.getConfigConnection();
		Statement stmt = configConnection.createStatement();
		StringBuffer s = new StringBuffer();
		s.append("select mod_service.service_desc,sys_user.user_name,mod_service.service_name,mod_service.project_code,mod_service.project_name,mod_service_version.version_code ");
		s.append(" from mod_service,sys_user,mod_service_version ");
		s.append(" where mod_service.service_leader=sys_user.user_id ");
		s.append(" and mod_service.service_id=mod_service_version.service_id ");
		s.append(" and mod_service_version.v_enabled=1 ");
		s.append(" and mod_service.project_code='" + projectCode + "'");
		ResultSet rs = stmt.executeQuery(s.toString());
		while (rs.next()) {
			wordInfo.setFileName(CharDealUtil.xmlCharDeal(rs.getString("service_name")));
			wordInfo.setTitle(CharDealUtil.xmlCharDeal(rs.getString("service_name")));
			wordInfo.setServiceName(CharDealUtil.xmlCharDeal(rs.getString("service_name")));
			wordInfo.setDesc(CharDealUtil.xmlCharDeal(rs.getString("service_desc")));
			wordInfo.setCodeProjectName(CharDealUtil.xmlCharDeal(rs.getString("project_name")));
			wordInfo.setAuthor(CharDealUtil.xmlCharDeal(rs.getString("user_name")));
			wordInfo.setVersion(CharDealUtil.xmlCharDeal(rs.getString("version_code")));
			wordInfo.setProjectCode(CharDealUtil.xmlCharDeal(rs.getString("project_code")));
		}
		wordInfo.setCreateDate(new Date());
		if (Config.WORD_INFO_AUTHOR != null && !Config.WORD_INFO_AUTHOR.equals("")) {
			wordInfo.setAuthor(Config.WORD_INFO_AUTHOR);
		}
		if (Config.WORD_INFO_API_VERSION != null && !Config.WORD_INFO_API_VERSION.equals("")) {
			wordInfo.setVersion(Config.WORD_INFO_API_VERSION);
		}
		if (Config.WORD_INFO_PROJECT_NAME != null && !Config.WORD_INFO_PROJECT_NAME.equals("")) {
			wordInfo.setProjectName(Config.WORD_INFO_PROJECT_NAME);
		}
		if (Config.WORD_INFO_SERVICE_NAME != null && !Config.WORD_INFO_SERVICE_NAME.equals("")) {
			wordInfo.setServiceName(Config.WORD_INFO_SERVICE_NAME);
		}
		if (Config.WORD_INFO_TITLE != null && !Config.WORD_INFO_TITLE.equals("")) {
			wordInfo.setTitle(Config.WORD_INFO_TITLE);
		}
		if (Config.WORD_INFO_FILE_NAME != null && !Config.WORD_INFO_FILE_NAME.equals("")) {
			wordInfo.setFileName(Config.WORD_INFO_FILE_NAME);
		}
		// 服务来源
		if (wordInfo.getServiceName().split("-").length > 1) {
			wordInfo.setServiceSource(wordInfo.getServiceName().split("-")[0]);
		}
		return wordInfo;
	}

	/**
	 * 获得APP的wordInfo
	 * 
	 * @param projectCode
	 * @return
	 * @throws SQLException
	 */
	public static WordInfo getAppWordInfo(String projectCode) throws Exception {
		WordInfo wordInfo = new WordInfo();
		Connection configConnection = ConfigDBUtil.getConfigConnection();
		Statement stmt = configConnection.createStatement();
		StringBuffer s = new StringBuffer();
		s.append("select mod_app.app_desc,mod_app.app_name,sys_user.user_name,mod_app_source.project_name,mod_app.project_code ");
		s.append(" from mod_app,sys_user,mod_app_source ");
		s.append(" where mod_app.app_leader=sys_user.user_id");
		s.append(" and mod_app.app_id = mod_app_source.app_id");
		s.append(" and mod_app.project_code='" + projectCode + "'");
		ResultSet rs = stmt.executeQuery(s.toString());
		while (rs.next()) {
			wordInfo.setFileName(rs.getString("app_name"));
			wordInfo.setTitle(rs.getString("app_name"));
			wordInfo.setServiceName(rs.getString("app_name"));
			wordInfo.setCodeProjectName(rs.getString("project_name"));
			wordInfo.setDesc(CharDealUtil.xmlCharDeal(rs.getString("app_desc")));
			wordInfo.setAuthor(rs.getString("user_name"));
		}
		if (wordInfo.getTitle() == null || wordInfo.getTitle().equals("")) {
			throw new Exception("提供的APP项目编码错误，无法获取app manager 信息！");
		}

		wordInfo.setCreateDate(new Date());
		// 版本未获取
		wordInfo.setVersion(Config.WORD_INFO_APP_VERSION);
		if (Config.WORD_INFO_AUTHOR != null && !Config.WORD_INFO_AUTHOR.equals("")) {
			wordInfo.setAuthor(Config.WORD_INFO_AUTHOR);
		}
		if (Config.WORD_INFO_PROJECT_NAME != null && !Config.WORD_INFO_PROJECT_NAME.equals("")) {
			wordInfo.setProjectName(Config.WORD_INFO_PROJECT_NAME);
		}
		if (Config.WORD_INFO_SERVICE_NAME != null && !Config.WORD_INFO_SERVICE_NAME.equals("")) {
			wordInfo.setServiceName(Config.WORD_INFO_SERVICE_NAME);
		}
		if (Config.WORD_INFO_TITLE != null && !Config.WORD_INFO_TITLE.equals("")) {
			wordInfo.setTitle(Config.WORD_INFO_TITLE);
		}
		if (Config.WORD_INFO_FILE_NAME != null && !Config.WORD_INFO_FILE_NAME.equals("")) {
			wordInfo.setFileName(Config.WORD_INFO_FILE_NAME);
		}
		return wordInfo;
	}

	/**
	 * 将WordInfos转换为内部为map的list
	 * 
	 * @param appVersionApiWordInfos
	 * @return
	 */
	public List<Map> getWordInfoMaps(List<WordInfo> appVersionApiWordInfos) {
		List<Map> wordInfoMaps = new ArrayList<Map>();
		for (WordInfo wordInfo : appVersionApiWordInfos) {
			wordInfoMaps.add(wordInfoToMap(wordInfo));
		}
		return wordInfoMaps;
	}

	/**
	 * 获得主服务信息
	 * 
	 * @param appVersionApiWordInfos
	 * @param appProjectName
	 * @return
	 */
	public WordInfo getMainApiWordInfo(List<WordInfo> appVersionApiWordInfos, String appServiceName) {
		for (WordInfo wordInfo : appVersionApiWordInfos) {
			if (wordInfo.getServiceName().length() > 1) {
				String apiServiceName = wordInfo.getServiceName();
				if (apiServiceName.split("-").length > 1) {
					apiServiceName = apiServiceName.split("-")[apiServiceName.split("-").length - 1];
				}
				if (apiServiceName.length() > 2) {
					apiServiceName = apiServiceName.substring(0, 2);
				}
				if (appServiceName.indexOf(apiServiceName) > -1) {
					return wordInfo;
				}
			}
		}
		return new WordInfo();
	}

	/**
	 * 获得非主服务的信息列表
	 * 
	 * @param appVersionApiWordInfos
	 * @param mainApiWordInfo
	 * @return
	 */

	public List<WordInfo> getAppVersionApiQtWordInfos(List<WordInfo> appVersionApiWordInfos, WordInfo mainApiWordInfo) {
		List<WordInfo> appVersionApiQtWordInfos = new ArrayList<WordInfo>();
		for (WordInfo wordInfo : appVersionApiWordInfos) {
			if (mainApiWordInfo.getServiceName() == null || mainApiWordInfo.getServiceName().equals("")) {
				appVersionApiQtWordInfos.add(wordInfo);
			} else {
				if (!mainApiWordInfo.getServiceName().equals(wordInfo.getServiceName())) {
					appVersionApiQtWordInfos.add(wordInfo);
				}
			}
		}
		return appVersionApiQtWordInfos;
	}
}
