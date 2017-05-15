package com.qh.app.drug.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qh.util.common.PropertiesUtil;



/**
 * 
  * 公用服务
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
@Controller
public class AppController  extends com.qh.util.web.Controller {
 	@Autowired 
    private PropertiesUtil propertiesUtil;
    
	/**
	 * 时间格式化
	*/
	protected SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
     * 获取路径
     * @param key
     * @return 返回路径
     * @author liub
  	 * @date 2017-04-01 14:37:44
     */
    protected String getServiceUrl(String key) {
        
        return propertiesUtil.getContextProperty(key);
    }
    /**
     * 获取登录用户信息
     * @param userStr 用户值
     * @return 
     * @author liub
  	 * @date 2017-04-01 14:37:44
     */
    protected Object getUser(Object userStr) {
        
        return "";
    }
	
}
