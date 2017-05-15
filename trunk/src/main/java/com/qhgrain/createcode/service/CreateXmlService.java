package com.qhgrain.createcode.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.qhgrain.common.util.DelAllFile;
import com.qhgrain.common.util.Freemarker;
import com.qhgrain.createcode.config.Config;
import com.qhgrain.createcode.config.DBConfig;
import com.qhgrain.createcode.util.CreateCodeObject;
import com.qhgrain.createcode.util.CreateCodeObjects;
import com.qhgrain.createcode.util.CreateCodeUtil;
import com.qhgrain.createcode.util.Property;
import com.qhgrain.createcode.util.PropertyEdit;
import com.qhgrain.createcode.util.PropertyList;
import com.qhgrain.createcode.util.PropertyNew;
import com.qhgrain.createcode.util.PropertyQuery;
import com.qhgrain.createcode.util.PropertyView;
/**
 * 
  * Description
  * @author liub
  * @version 1.0
  * @date 2017-3-28
  * Copyright
 */
@Service
public class CreateXmlService {

	private Connection con;
	private Statement stmt;

	public CreateXmlService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.print("classnotfoundexception :");
			System.err.print(e.getMessage());
		}
	}
	/**
	 * 
	 * Description
	 * @param 
	 * @return 
	 * @author liub
	 * @date 2017-3-28
	 */
	public void proXml(CreateCodeObjects userConf,DBConfig dbConfig,Config config) throws Exception {
	    // 生成代码前,先清空之前生成的代码
        DelAllFile.delFolder(config.getXmlFolderRootPath());
		List<String> tables=getTableNames(dbConfig,config);
		if (tables == null || tables.size() == 0) {
		    throw new Exception("请查看数据库中的表是否存在");
		}
		for (String tableName : tables) {
			if (tableName == null || tableName.equals("")) {
				throw new Exception("表名不能为空！");
			}
			// 将xml配置文件内容转成createCodeObjects对象
			CreateCodeObjects createCodeObjects = getCreateCodeObjects(tableName,dbConfig,config);
			createCodeObjects.setAuthor(userConf.getAuthor());
            createCodeObjects.setCopyright(userConf.getCopyright());
            createCodeObjects.setVersion(userConf.getVersion());
			// 通过ftl生成文件
			createFileByFtl(createCodeObjects,dbConfig,config);
		}
		stmt.close();
		// 关闭连接
		con.close();
	}

	public List<String> getTableNames(DBConfig dbConfig,Config config) throws Exception{
	    String urlStr = "jdbc:mysql://" + dbConfig.getDbHost() + ":" + dbConfig.getDbPort() + "/" + dbConfig.getDbName();
        con = DriverManager.getConnection(urlStr, dbConfig.getDbUser(), dbConfig.getDbPassword());
        stmt = con.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='"+dbConfig.getDbName()+"' AND table_type='base table'");
        List<String> tables=new ArrayList<String>();
        while (rs.next()){
            tables.add(rs.getString("table_name"));
        }
        return tables;
	}
	
	public void createFileByFtl(CreateCodeObjects createCodeObjects,DBConfig dbConfig,Config config) throws Exception {
	    // 设置FreeMarker 使用的root值
        Map<String, Object> rootMap =CreateCodeUtil.createCodeObjectsToMap(createCodeObjects);
		for (CreateCodeObject createCodeObject : createCodeObjects.getObjects()) {//只会有一个
			if (createCodeObject.getIsMain()) {
				Freemarker.printFile("objectsXml.ftl", rootMap, createCodeObject.getClassName() + ".xml", config.getXmlFolderRootPath(), config.getFtlFolderPath() + "/xml");
			}
		}
	}

	private CreateCodeObjects getCreateCodeObjects(String tableName,DBConfig dbConfig,Config config) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSetMetaData rsmd = null;
		pstmt = (PreparedStatement) con.prepareStatement("select * from  " + tableName);
		pstmt.execute();
		rsmd = (ResultSetMetaData) pstmt.getMetaData();
		CreateCodeObjects createCodeObjects = new CreateCodeObjects();
		List<CreateCodeObject> objects = new ArrayList<CreateCodeObject>();
		CreateCodeObject createCodeObject = new CreateCodeObject();
		createCodeObject.setApiPackageName(config.getApiPackageNamePrefix() + "." + CreateCodeUtil.getModuleNameByTableName(tableName));
		createCodeObject.setAppPackageName(config.getAppPackageNamePrefix() + "." + CreateCodeUtil.getModuleNameByTableName(tableName));
		createCodeObject.setDomainPackageName(config.getDomainPackageNamePrefix() + "." + CreateCodeUtil.getModuleNameByTableName(tableName));
		createCodeObject.setTitle(getTableComment(tableName,dbConfig));
		createCodeObject.setClassName(CreateCodeUtil.tableNameToObjectClassName(tableName));
		createCodeObject.setIsMain(true);
		createCodeObject.setTableName(tableName.toLowerCase());
		objects.add(createCodeObject);
		createCodeObjects.setObjects(objects);
		List<Property> properties = new ArrayList<Property>();
		for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
			Property property = getProperty(rsmd, i,dbConfig);
			properties.add(property);
		}
		createCodeObject.setProperties(properties);
		return createCodeObjects;
	}

	private String getTableComment(String tableName,DBConfig dbConfig) throws SQLException {
		PreparedStatement pstmt = null;
		String tableComment = "";
		pstmt = (PreparedStatement) con.prepareStatement("SELECT * FROM information_schema.tables	WHERE TABLE_SCHEMA='" + dbConfig.getDbName() + "' AND TABLE_NAME='" + tableName + "'");
		pstmt.execute();
		ResultSet resultSet = pstmt.getResultSet();
		if (resultSet.next()) {
			tableComment = resultSet.getString("TABLE_COMMENT");
		}
		return tableComment;
	}

	/**
	 * 通过 ResultSetMetaData 和其列的序列生成Property
	 * 
	 * @param rsmd
	 * @param i
	 * @return
	 * @throws SQLException
	 */
	private Property getProperty(ResultSetMetaData rsmd, int i,DBConfig dbConfig) throws SQLException {
		// rsmd的使用教程：http://blog.csdn.net/zwhfyy/article/details/4233311
		PreparedStatement pstmt = null;
		Property property = new Property();
		property.setColName(rsmd.getColumnName(i));// 列名
		property.setLenth(rsmd.getPrecision(i) + "");// 长度
		property.setColType(rsmd.getColumnTypeName(i));// 列的类型
		// ----------------------获得列的主键和备注信息
		String columnKey = "";
		String columnComment = "";
		pstmt = (PreparedStatement) con.prepareStatement("SELECT * FROM information_schema.columns	WHERE TABLE_SCHEMA='" + dbConfig.getDbName() + "' and TABLE_NAME='" + rsmd.getTableName(i) + "' and COLUMN_NAME='" + rsmd.getColumnName(i) + "'");
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
		if(property.getName()!=null && !(property.getName().equals("companyId") || property.getName().equals("createUserid")
		        || property.getName().equals("createDeptid") || property.getName().equals("createDate")
		        || property.getName().equals("modifyUserid") || property.getName().equals("modifyDeptid")
		        || property.getName().equals("modifyDate"))){
		    property.setPropertyEdit(this.getPropertyEdit(property));
		    property.setPropertyNew(this.getPropertyNew(property));
		    property.setPropertyQuery(this.getPropertyQuery(property));
		    property.setPropertyList(this.getPropertyList(property));
		    property.setPropertyView(this.getPropertyView(property));
		}
		property.setDictType("");
		return property;
	}

	private PropertyView getPropertyView(Property property) {
		PropertyView propertyView = new PropertyView();
		propertyView.setIsExist(true);
		propertyView.setShowType("text");
		if (property.getIsPk()) {
			propertyView.setShowType("hidden");
		}
		return propertyView;
	}

	private PropertyQuery getPropertyQuery(Property property) {
		PropertyQuery propertyQuery = new PropertyQuery();
		propertyQuery.setIsExist(false);
		propertyQuery.setShowType("text");
		if (property.getColType().toLowerCase().equals("varchar")) {
			propertyQuery.setQueryType("like");
		}
		if (property.getColType().toLowerCase().equals("integer")) {
			propertyQuery.setQueryType("interval");
		}
		if (property.getColType().toLowerCase().equals("datetime")) {
			propertyQuery.setQueryType("interval");
		}
		if (property.getColType().toLowerCase().equals("numeric")) {
			propertyQuery.setQueryType("interval");
		}
		return propertyQuery;
	}

	private PropertyList getPropertyList(Property property) {
		PropertyList propertyList = new PropertyList();
		propertyList.setIsExist(false);
		propertyList.setShowType("text");
		propertyList.setOrderBy("");
		return propertyList;
	}

	private PropertyEdit getPropertyEdit(Property property) {
		PropertyEdit propertyEdit = new PropertyEdit();
		propertyEdit.setIsExist(true);
		propertyEdit.setIsMust(true);
		propertyEdit.setShowType("text");
		return propertyEdit;
	}

	private PropertyNew getPropertyNew(Property property) {
		PropertyNew propertyNew = new PropertyNew();
		propertyNew.setIsExist(true);
		propertyNew.setIsMust(true);
		propertyNew.setShowType("text");
		return propertyNew;
	}

}
