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

public class ConfigDBUtil {
	private static Connection connection;

	public static Connection getConfigConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			DBInfo configDBInfo = new DBInfo();
			configDBInfo.setHost(DBConfig.DB_HOST);
			configDBInfo.setPort(DBConfig.DB_PORT);
			configDBInfo.setName(DBConfig.DB_NAME);
			configDBInfo.setUser(DBConfig.DB_USER);
			configDBInfo.setPassword(DBConfig.DB_PASSWORD);
			connection = DBUtil.getConnection(configDBInfo);
		}
		return connection;
	}

}
