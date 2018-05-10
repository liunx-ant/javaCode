package com.liunx.createcode.config;

import com.alibaba.fastjson.JSON;

/**
  * 数据库工具类
  * @author liub
  * @version 1.0
  * @date 2017-3-28
 */
public class DBConfig {
	private  String dbHost;
	private  String dbPort;
	private  String dbUser;
	private  String dbPassword;
	private  String dbName;
	
    public String getDbHost() {
        return dbHost;
    }
    public void setDbHost(String dbHost) {
        this.dbHost = dbHost.trim();
    }
    public String getDbPort() {
        return dbPort;
    }
    public void setDbPort(String dbPort) {
        this.dbPort = dbPort.trim();
    }
    public String getDbUser() {
        return dbUser;
    }
    public void setDbUser(String dbUser) {
        this.dbUser = dbUser.trim();
    }
    public String getDbPassword() {
        return dbPassword;
    }
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword.trim();
    }
    public String getDbName() {
        return dbName;
    }
    public void setDbName(String dbName) {
        this.dbName = dbName.trim();
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return JSON.toJSONString(this);
    }
}
