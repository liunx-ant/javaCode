package ${mainObject.appPackageName}.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ${mainObject.domainPackageName}.${mainObject.className};
import ${projStructurePath}.util.ServiceUrl;
import ${projStructurePath}.util.common.Constants;



/**
 * 
  * ${mainObject.title}应用
  * @author ${author}
  * @version ${version}
  * @date ${nowDate}
  * Copyright ${copyright}
 */
@Controller
@RequestMapping("/${mainObject.objectName}/")
public class ${mainObject.className}Controller  extends AppController {

    /**
     * 
     * 主页面
     * @return   页面路径
     * @author ${author}
     * @date ${nowDate}
     */
    @RequestMapping("/home")
    public String homePage(HttpServletRequest request){
        request.setAttribute("nowTime", sdf.format(new Date()));
        return "${mainObject.objectName}/home";
    }
    /**
     * 
     * 新增页面
     * @return   页面路径
     * @author ${author}
     * @date ${nowDate}
     */
    @RequestMapping("/new")
    public String newPage(HttpServletRequest request) {
    	request.setAttribute("nowTime", sdf.format(new Date()));
        return "${mainObject.objectName}/new";
    }
    /**
     * 
     * 编辑页面
     * @return   页面路径
     * @author ${author}
     * @date ${nowDate}
     */
    @RequestMapping("/edit/{id}")
    public String editPage(HttpServletRequest request,@PathVariable("id") String id) {
        request.setAttribute("id", id);
        request.setAttribute("nowTime", sdf.format(new Date()));
        return "${mainObject.objectName}/edit";
    }
    /**
     * 
     * 详情页面
     * @return   页面路径
     * @author ${author}
     * @date ${nowDate}
     */
    @RequestMapping("/view/{id}")
    public String viewPage(HttpServletRequest request,@PathVariable("id") String id){
        request.setAttribute("id", id);
        request.setAttribute("nowTime", sdf.format(new Date()));
        return "${mainObject.objectName}/view";
    }
    

    /**
     * 
     * 获取列表信息
     * @return   页面路径
     * @author ${author}
     * @date ${nowDate}
     */
    @RequestMapping("/list/{pageNum}/{pageSize}")
    @ResponseBody
    public String list(@PathVariable("pageNum")Integer pageNum,@PathVariable("pageSize")Integer pageSize,@RequestBody ${mainObject.className} ${mainObject.objectName}) {
        if(${mainObject.objectName} == null){
            ${mainObject.objectName}=new ${mainObject.className}();
        }
        ${mainObject.objectName}.setDeleteFlag(Constants.DATA_NORMAL_STATUS);
        return postForObject(${mainObject.objectName},getServiceUrl(ServiceUrl.URL_${applicationName?upper_case}_SERVICE),"${mainObject.objectName}/page<#if (mainObject.objectRels?size>0) >LeftJoin</#if>",pageNum,pageSize);
    }
    
    /**
     * 获取详细信息
     * @return   详细信息
     * @author ${author}
     * @date ${nowDate}
     */
    @RequestMapping("/getByInfo")
    @ResponseBody
    public String getByInfo(@RequestBody ${mainObject.className} ${mainObject.objectName}) {
        return postForObject(${mainObject.objectName},getServiceUrl(ServiceUrl.URL_${applicationName?upper_case}_SERVICE),"${mainObject.objectName}/queryByInfo");
    }
    
    /**
     * 创建信息
     * @return   创建结果
     * @author ${author}
     * @date ${nowDate}
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestBody ${mainObject.className} ${mainObject.objectName}) {
        return postForObject(${mainObject.objectName},getServiceUrl(ServiceUrl.URL_${applicationName?upper_case}_SERVICE),"${mainObject.objectName}/add");
    }
    
    /**
     * 修改信息
     * @return   修改结果
     * @author ${author}
     * @date ${nowDate}
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody ${mainObject.className} ${mainObject.objectName}) {
        return postForObject(${mainObject.objectName},getServiceUrl(ServiceUrl.URL_${applicationName?upper_case}_SERVICE),"${mainObject.objectName}/update",${mainObject.objectName}.getId());
    }
    /**
     * 删除信息
     * @return  删除结果
     * @author ${author}
     * @date ${nowDate}
     */
    @RequestMapping("/del/{id}")
    @ResponseBody
    public String del(@PathVariable("id") String id) {
        ${mainObject.className} ${mainObject.objectName} = new ${mainObject.className}();
        ${mainObject.objectName}.setId(id);
        ${mainObject.objectName}.setDeleteFlag(Constants.DATA_DELETE_STATUS);
        return postForObject(${mainObject.objectName},getServiceUrl(ServiceUrl.URL_${applicationName?upper_case}_SERVICE),"${mainObject.objectName}/update",id);

    }
    
    

}
