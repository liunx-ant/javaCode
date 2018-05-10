package com.liunx.createword.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liunx.createword.config.ApiDBConfig;
import com.liunx.createword.config.Config;
import com.liunx.createword.config.DBConfig;
import com.liunx.createword.util.word.WordInfo;

public class ConfigMySqlDBUtil {
	private static Connection connection;

	public static Connection getConfigConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			DBInfo configDBInfo = new DBInfo();
			configDBInfo.setHost(DBConfig.DB_HOST);
			configDBInfo.setPort(DBConfig.DB_PORT);
			configDBInfo.setName(DBConfig.MYSQL_INFO_DB_NAME);
			configDBInfo.setUser(DBConfig.DB_USER);
			configDBInfo.setPassword(DBConfig.DB_PASSWORD);
			connection = DBUtil.getConnection(configDBInfo);
		}
		return connection;
	}

	public static DBInfo getApiDBInfoByServiceId(String serviceId) throws SQLException {
		DBInfo DBInfo = new DBInfo();
		Connection configConnection = ConfigMySqlDBUtil.getConfigConnection();
		Statement stmt = configConnection.createStatement();
		StringBuffer s = new StringBuffer();
		s.append("select os_resource_mysql.allocate_content,os_project_env_res.res_addition");
		s.append(" from os_project,os_project_env,os_project_env_res,os_resource_mysql");
		s.append(" where os_project.project_id=os_project_env.project_id");
		s.append(" and os_project_env.env_id=os_project_env_res.env_id");
		s.append(" and os_project_env_res.paasos_res_id = os_resource_mysql.mysql_id");
		s.append(" and os_project_env_res.resource_id='mysql51'");
		s.append(" and os_project.business_id='" + serviceId + "'");
		ResultSet rs = stmt.executeQuery(s.toString());
		String sqlContent = "";
		String dbName = "";
		while (rs.next()) {
			sqlContent = rs.getString("allocate_content");
			dbName = rs.getString("res_addition");
		}
		JSONObject sqlContentJson = JSONArray.parseObject(sqlContent);
		JSONObject sqlConnectionJson = sqlContentJson.getJSONObject("connection");
		DBInfo.setHost(sqlConnectionJson.getString("externalIP"));
		DBInfo.setPort(sqlConnectionJson.getString("externalPort"));
		DBInfo.setName(dbName);
		DBInfo.setUser(sqlConnectionJson.getString("account"));
		DBInfo.setPassword(sqlConnectionJson.getString("password"));
		if (ApiDBConfig.DB_HOST != null && !ApiDBConfig.DB_HOST.equals("")) {
			DBInfo.setHost(ApiDBConfig.DB_HOST);
		}
		stmt.close();
		// 关闭连接
		configConnection.close();
		return DBInfo;
	}

}
