package com.qh.app.drug.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qh.domain.drug.DrugInStorage;
import com.qh.util.ServiceUrl;
import com.qh.util.common.Constants;



/**
 * 
  * 采购入库应用
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
@Controller
@RequestMapping("/drugInStorage/")
public class DrugInStorageController  extends AppController {

    /**
     * 
     * 主页面
     * @return   页面路径
     * @author liub
     * @date 2017-04-01 14:37:44
     */
    @RequestMapping("/home")
    public String homePage(HttpServletRequest request){
        request.setAttribute("nowTime", sdf.format(new Date()));
        return "drugInStorage/home";
    }
    /**
     * 
     * 新增页面
     * @return   页面路径
     * @author liub
     * @date 2017-04-01 14:37:44
     */
    @RequestMapping("/new")
    public String newPage(HttpServletRequest request) {
    	request.setAttribute("nowTime", sdf.format(new Date()));
        return "drugInStorage/new";
    }
    /**
     * 
     * 编辑页面
     * @return   页面路径
     * @author liub
     * @date 2017-04-01 14:37:44
     */
    @RequestMapping("/edit/{id}")
    public String editPage(HttpServletRequest request,@PathVariable("id") String id) {
        request.setAttribute("id", id);
        request.setAttribute("nowTime", sdf.format(new Date()));
        return "drugInStorage/edit";
    }
    /**
     * 
     * 详情页面
     * @return   页面路径
     * @author liub
     * @date 2017-04-01 14:37:44
     */
    @RequestMapping("/view/{id}")
    public String viewPage(HttpServletRequest request,@PathVariable("id") String id){
        request.setAttribute("id", id);
        request.setAttribute("nowTime", sdf.format(new Date()));
        return "drugInStorage/view";
    }
    

    /**
     * 
     * 获取列表信息
     * @return   页面路径
     * @author liub
     * @date 2017-04-01 14:37:44
     */
    @RequestMapping("/list/{pageNum}/{pageSize}")
    @ResponseBody
    public String list(@PathVariable("pageNum")Integer pageNum,@PathVariable("pageSize")Integer pageSize,@RequestBody DrugInStorage drugInStorage) {
        if(drugInStorage == null){
            drugInStorage=new DrugInStorage();
        }
        drugInStorage.setDeleteFlag(Constants.DATA_NORMAL_STATUS);
        return postForObject(drugInStorage,getServiceUrl(ServiceUrl.URL_DRUG_SERVICE),"drugInStorage/page",pageNum,pageSize);
    }
    
    /**
     * 获取详细信息
     * @return   详细信息
     * @author liub
     * @date 2017-04-01 14:37:44
     */
    @RequestMapping("/getByInfo")
    @ResponseBody
    public String getByInfo(@RequestBody DrugInStorage drugInStorage) {
        return postForObject(drugInStorage,getServiceUrl(ServiceUrl.URL_DRUG_SERVICE),"drugInStorage/queryByInfo");
    }
    
    /**
     * 创建信息
     * @return   创建结果
     * @author liub
     * @date 2017-04-01 14:37:44
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestBody DrugInStorage drugInStorage) {
        return postForObject(drugInStorage,getServiceUrl(ServiceUrl.URL_DRUG_SERVICE),"drugInStorage/add");
    }
    
    /**
     * 修改信息
     * @return   修改结果
     * @author liub
     * @date 2017-04-01 14:37:44
     */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody DrugInStorage drugInStorage) {
        return postForObject(drugInStorage,getServiceUrl(ServiceUrl.URL_DRUG_SERVICE),"drugInStorage/update",drugInStorage.getId());
    }
    /**
     * 删除信息
     * @return  删除结果
     * @author liub
     * @date 2017-04-01 14:37:44
     */
    @RequestMapping("/del/{id}")
    @ResponseBody
    public String del(@PathVariable("id") String id) {
        DrugInStorage drugInStorage = new DrugInStorage();
        drugInStorage.setId(id);
        drugInStorage.setDeleteFlag(Constants.DATA_DELETE_STATUS);
        return postForObject(drugInStorage,getServiceUrl(ServiceUrl.URL_DRUG_SERVICE),"drugInStorage/update",id);

    }
    
    

}
