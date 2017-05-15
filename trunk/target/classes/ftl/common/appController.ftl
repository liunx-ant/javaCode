package ${mainObject.appPackageName}.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ${projStructurePath}.util.common.PropertiesUtil;



/**
 * 
  * 公用服务
  * @author ${author}
  * @version ${version}
  * @date ${nowDate}
  * Copyright ${copyright}
 */
@Controller
public class AppController  extends ${projStructurePath}.util.web.Controller {
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
     * @author ${author}
  	 * @date ${nowDate}
     */
    protected String getServiceUrl(String key) {
        
        return propertiesUtil.getContextProperty(key);
    }
    /**
     * 获取登录用户信息
     * @param userStr 用户值
     * @return 
     * @author ${author}
  	 * @date ${nowDate}
     */
    protected Object getUser(Object userStr) {
        
        return "";
    }
	
}
