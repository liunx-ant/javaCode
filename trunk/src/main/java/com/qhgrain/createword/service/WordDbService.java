package com.qhgrain.createword.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.qhgrain.common.util.BeanUtil;
import com.qhgrain.createcode.util.CreateCodeObjects;
import com.qhgrain.createcode.util.CreateCodeUtil;
import com.qhgrain.createword.config.Config;
import com.qhgrain.createword.util.ConfigMySqlDBUtil;
import com.qhgrain.createword.util.DBInfo;
import com.qhgrain.createword.util.DBUtil;
import com.qhgrain.createword.util.word.WordTableObject;
import com.qhgrain.createword.util.word.WrodTableObjectProperty;

/**
 * 类注释
 * 
 * @author dwl
 * @version 1.0
 * @date 2016年8月5日 Copyright 青海粮食云项目组
 */

public class WordDbService {

	private static DBInfo dbInfo = null;
	private static Connection con;
	private static Statement stmt;

	public DBInfo getDbInfo() {
		return dbInfo;
	}

	public WordDbService() {
		try {
			if (Config.WORD_TYPE.equals("api")) {
				if (dbInfo == null) {
					dbInfo = ConfigMySqlDBUtil.getApiDBInfoByServiceId(WordInfoService.getServiceId(Config.PROJECT_CODE));
				}
				this.con = DBUtil.getConnection(dbInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Map> getWordTableObjectMaps(List wordTableObjects) throws Exception {
		return wordTableObjectsToMap(wordTableObjects);
	}

	/**
	 * 获得所有的表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<WordTableObject> getWordTableObjects() throws Exception {
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='" + dbInfo.getName() + "' AND table_type='base table'");
		List<String> tables = new ArrayList<String>();
		while (rs.next()) {
			tables.add(rs.getString("table_name"));
		}
		if (dbInfo.getTables() == null || dbInfo.getTables().length == 0) {
			dbInfo.setTables(tables.toArray(dbInfo.getTables()));
		}
		if (dbInfo.getTables() == null || dbInfo.getTables().length == 0) {
			throw new Exception("请查看数据库中的表是否存在");
		}
		List<WordTableObject> wordTableObjects = new ArrayList<WordTableObject>();
		for (String tableName : dbInfo.getTables()) {
			if (tableName == null || tableName.equals("")) {
				throw new Exception("表名不能为空！");
			}
			WordTableObject wordTableObject = getWordTableObject(tableName);
			wordTableObjects.add(wordTableObject);
		}
		stmt.close();
		// 关闭连接
		con.close();
		return wordTableObjects;
	}

	/**
	 * 将wordTableObjects转换为map
	 * 
	 * @param wordTableObjects
	 * @return
	 */
	private List<Map> wordTableObjectsToMap(List<WordTableObject> wordTableObjects) {
		List<Map> wordTableObjectMaps = new ArrayList();
		for (WordTableObject wordTableObject : wordTableObjects) {
			wordTableObjectMaps.add(wordTableObjectToMap(wordTableObject));
		}
		return wordTableObjectMaps;
	}

	/**
	 * 将WordTableObject转换为map
	 * 
	 * @param wordTableObject
	 * @return
	 */

	private static Map<String, Object> wordTableObjectToMap(WordTableObject wordTableObject) {
		Map objectMap = BeanUtil.objectToMap(wordTableObject);
		// propertyMaps
		List<WrodTableObjectProperty> properties = wordTableObject.getProperties();
		List<Map> propertyMaps = new ArrayList();
		for (int i = 0; i < properties.size(); i++) {
			// propertyMap
			WrodTableObjectProperty property = properties.get(i);
			Map propertyMap = BeanUtil.objectToMap(property);
			// type是通过
			// colType转换来的，在BeanUtil.objectToMap的时候，由于执行顺序的的问题，可能转换错误，所以手动put
			propertyMap.put("type", property.getType());
			// propertyMaps
			propertyMaps.add(propertyMap);
		}
		objectMap.put("properties", properties);
		// objectName是通过className转换来的，在BeanUtil.objectToMap的时候，由于执行顺序的的问题，可能转换错误，所以手动put
		objectMap.put("objectName", wordTableObject.getObjectName());
		return objectMap;
	}

	private WordTableObject getWordTableObject(String tableName) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSetMetaData rsmd = null;
		pstmt = (PreparedStatement) con.prepareStatement("select * from  " + tableName);
		pstmt.execute();
		rsmd = (ResultSetMetaData) pstmt.getMetaData();
		CreateCodeObjects createCodeObjects = new CreateCodeObjects();
		WordTableObject wordTableObject = new WordTableObject();
		wordTableObject.setTitle(getTableComment(tableName));
		wordTableObject.setClassName(CreateCodeUtil.tableNameToObjectClassName(tableName));
		wordTableObject.setIsMain(true);
		wordTableObject.setTableName(tableName.toLowerCase());
		List<WrodTableObjectProperty> properties = new ArrayList<WrodTableObjectProperty>();
		for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
			WrodTableObjectProperty property = getProperty(rsmd, i);
			properties.add(property);
		}
		wordTableObject.setProperties(properties);
		return wordTableObject;
	}

	/**
	 * 通过 ResultSetMetaData 和其列的序列生成Property
	 * 
	 * @param rsmd
	 * @param i
	 * @return
	 * @throws SQLException
	 */
	private WrodTableObjectProperty getProperty(ResultSetMetaData rsmd, int i) throws SQLException {
		// rsmd的使用教程：http://blog.csdn.net/zwhfyy/article/details/4233311
		PreparedStatement pstmt = null;
		WrodTableObjectProperty property = new WrodTableObjectProperty();
		property.setColName(rsmd.getColumnName(i));// 列名
		property.setLenth(rsmd.getPrecision(i) + "");// 长度
		property.setColType(rsmd.getColumnTypeName(i));// 列的类型
		// ----------------------获得列的主键和备注信息
		String columnKey = "";
		String columnComment = "";
		pstmt = (PreparedStatement) con.prepareStatement("SELECT * FROM information_schema.columns	WHERE TABLE_SCHEMA='" + dbInfo.getName() + "' and TABLE_NAME='" + rsmd.getTableName(i) + "' and COLUMN_NAME='" + rsmd.getColumnName(i) + "'");
		pstmt.execute();
		ResultSet resultSet = pstmt.getResultSet();
		if (resultSet.next()) {
			columnKey = resultSet.getString("COLUMN_KEY");
			columnComment = resultSet.getString("COLUMN_COMMENT");
		}
		if (columnKey.equals("PRI")) {
			property.setIsPk(true);// 如果列名为id，则设置为主键
		}
		property.setTitle(columnComment);
		property.setName(CreateCodeUtil.colNameToPropertyName(rsmd.getColumnName(i)));
		property.setDictType("");
		return property;
	}

	private String getTableComment(String tableName) throws SQLException {
		PreparedStatement pstmt = null;
		String tableComment = "";
		pstmt = (PreparedStatement) con.prepareStatement("SELECT * FROM information_schema.tables	WHERE TABLE_SCHEMA='" + dbInfo.getName() + "' AND TABLE_NAME='" + tableName + "'");
		pstmt.execute();
		ResultSet resultSet = pstmt.getResultSet();
		if (resultSet.next()) {
			tableComment = resultSet.getString("TABLE_COMMENT");
		}
		return tableComment;
	}

}
