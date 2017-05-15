package com.qhgrain.dto;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * 
  * Description
  * @author liub
  * @version 1.0
  * @date 2017-3-28
  * Copyright
 */
public class User {
    //    用户id
    private String id;
    //    用户名称
    private String name;
    //    下载次数
    private Integer downLoadTimes=0;
    //    配置信息
    private ConfigInformation configInformation;
    //    配置集合
    private List<ConfigInformation> configInformationList=new ArrayList<ConfigInformation>();
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getDownLoadTimes() {
        return downLoadTimes;
    }
    public void setDownLoadTimes(Integer downLoadTimes) {
        this.downLoadTimes = downLoadTimes;
    }
    public ConfigInformation getConfigInformation() {
        return configInformation;
    }
    public void setConfigInformation(ConfigInformation configInformation) {
        this.configInformation = configInformation;
        if(name!=null){
            Boolean isExist=false;
            for(int i=0;i<configInformationList.size();i++){
                if(configInformationList.get(i).getDbConfig().toString().equals(configInformation.getDbConfig().toString())){
                    isExist=true;
                    configInformationList.set(i,configInformation);
                }
            }
            if(!isExist){
                System.out.println(name+":添加了配置");
                System.out.println("               "+configInformation.toString());
                configInformationList.add(configInformation);
            }
        }
    }
    public List<ConfigInformation> getConfigInformationList() {
        return configInformationList;
    }
    public void setConfigInformationList(List<ConfigInformation> configInformationList) {
        this.configInformationList = configInformationList;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return JSON.toJSONString(this,SerializerFeature.DisableCircularReferenceDetect);
    }
}
