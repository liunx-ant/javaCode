package com.liunx.createword.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liunx.createword.config.DBConfig;

public class DBUtil {

	public static Connection getConnection(DBInfo dbInfo) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String urlStr = "jdbc:mysql://" + dbInfo.getHost() + ":" + dbInfo.getPort() + "/" + dbInfo.getName();
			connection = DriverManager.getConnection(urlStr, dbInfo.getUser(), dbInfo.getPassword());
		} catch (ClassNotFoundException e) {
			System.err.print("classnotfoundexception :");
			System.err.print(e.getMessage());
		} catch (SQLException e) {
			System.err.print("连接数据库失败，请检查配置信息");
			e.printStackTrace();
		}
		return connection;
	}

}
