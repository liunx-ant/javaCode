package com.qh.util.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

 /**
  * 公共的RestTemplate封装
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
  */
public class Controller  {
    
   @Autowired
    protected RestTemplate restTemplate;
    
    /**
     * 设置http头信息，应用于rest风格的post请求
     * @return
     */
    public HttpHeaders settingHead(){
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return headers;
    }
    
    /**
     * 
     * post请求，增加消息头
     * @param object 传入的对象/集合
     * @param objects 按照路径传参数 路径顺序传参 serviceUrl,methedUrl,params[0],params[1]
     *        例: companyService.postForObject(company,companyServiceUrl,"company/queryAll",startNum,size);
     * @return 
     * @author liub
     * @date 2016年3月18日
     */
    public String postForObject(Object object,Object... objects) {
        // TODO Auto-generated method stub
        HttpEntity<String> formEntity = new HttpEntity<String>(JSON.toJSONString(object),settingHead());
        String message = restTemplate.postForObject(getUrl(objects), formEntity, String.class);
        return message;
    }
    
    /**
     * 
     * get请求 ，封装
     * @param params 路径顺序传参 serviceUrl,methedUrl,params[0],params[1]
     *          例:companyService.getForObject(companyServiceUrl,"company/queryInfo",companyId);
     * @return 
     * @author liub
     * @date 2016年3月18日
     */
    public String getForObject(Object... params) {
        String message = restTemplate.getForObject(getUrl(params),String.class);
        return message;
    }

    private String getUrl(Object... params) {
        String url="";
        if(params!=null&&params.length>0){
            
            for(Object o: params){
                url=url+o+"/";
            }
            url=url.substring(0,url.length()-1);
        }
        return url;
    }
    
}
