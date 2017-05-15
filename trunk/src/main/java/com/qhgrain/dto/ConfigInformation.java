package com.qhgrain.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qhgrain.createcode.config.Config;
import com.qhgrain.createcode.config.DBConfig;
import com.qhgrain.createcode.util.CreateCodeObjects;
/**
 * 
  * Description
  * @author liub
  * @version 1.0
  * @date 2017-3-28
  * Copyright
 */
public class ConfigInformation {
    //    id
    private String id;
    //    项目配置
    private Config config;
    //    数据库配置
    private DBConfig dbConfig;
    //    项目公司版权信息
    private CreateCodeObjects createCodeObjects;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Config getConfig() {
        return config;
    }
    public void setConfig(Config config) {
        this.config = config;
    }
    public DBConfig getDbConfig() {
        return dbConfig;
    }
    public void setDbConfig(DBConfig dbConfig) {
        this.dbConfig = dbConfig;
    }
    public CreateCodeObjects getCreateCodeObjects() {
        return createCodeObjects;
    }
    public void setCreateCodeObjects(CreateCodeObjects createCodeObjects) {
        this.createCodeObjects = createCodeObjects;
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return JSON.toJSONString(this,SerializerFeature.DisableCircularReferenceDetect);
    }
}
