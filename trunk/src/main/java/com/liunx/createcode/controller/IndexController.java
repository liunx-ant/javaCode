package com.liunx.createcode.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.liunx.common.util.FileUtil;
import com.liunx.createcode.config.Config;
import com.liunx.createcode.config.DBConfig;
import com.liunx.createcode.service.CreateCodeService;
import com.liunx.createcode.service.CreateXmlService;
import com.liunx.createcode.util.CreateCodeObject;
import com.liunx.createcode.util.CreateCodeObjects;
import com.liunx.createcode.util.CreateCodeUtil;
import com.liunx.createcode.util.FileToZip;
import com.liunx.dto.ConfigInformation;
import com.liunx.dto.User;
/**
  *controlelr 
  * @author liub
  * @version 1.0
  * @date 2017-3-28
  * Copyright
 */
@Controller
public class IndexController{
    @Autowired
    private CreateXmlService xmlService;
    @Autowired
    private CreateCodeService codeService;
    //配置文件目录
    private String confRootPath;
    
    private Boolean isDevp=false;
    
    @RequestMapping("/list")
    public String index(HttpServletRequest request,HttpServletResponse response,HttpSession session,User user) {
        confRootPath=request.getServletContext().getRealPath("/");
        //判断登录信息
        User sessionUser=(User) session.getAttribute("user");
        if((user.getName()==null||user.getName().trim().equals(""))&&sessionUser==null){
            try {
                response.sendRedirect("/");
                return "";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //添加用户信息
        if(sessionUser==null||user.getName()!=null&&!user.getName().trim().equals("")){
            //添加配置信息
            String json=readFile(user,request);
            if(json!=null&&!json.toString().equals("")){
                user =(User) JSON.parseObject(json,User.class);
            }
            session.setAttribute("user",user);
        }else{
            user=sessionUser;
        }
        if(user!=null&&user.getConfigInformation()!=null){
            Config config=user.getConfigInformation().getConfig();
            File xmlFolder = new File(config.getXmlFolderRootPath());
            FilenameFilter filter=new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    // TODO Auto-generated method stub
                    if(!name.contains(".xml")){
                        return false;
                    }else{
                        return true;
                    }
                }
            };
            request.setAttribute("tables",xmlFolder.list(filter));
        }
        return "/index";
    }
    
    @RequestMapping("/getXML")
    public String getXML(HttpServletRequest request,HttpServletResponse response,HttpSession session, String fileNames) {
       try {
           User user=(User) session.getAttribute("user");
           if(user==null){
               try {
                   response.sendRedirect("/");
                   return "";
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           DBConfig dbConfig=user.getConfigInformation().getDbConfig();
           Config config=user.getConfigInformation().getConfig();
           request.setAttribute("objects",codeService.proCode(Arrays.asList(fileNames.split(",")),dbConfig,config));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/xmlEdit";
    }
    @RequestMapping("/createCode")
    public void createCode(HttpServletRequest request,HttpSession session,HttpServletResponse response,String fileNames) {
        try {
            User user=(User) session.getAttribute("user");
            DBConfig dbConfig=user.getConfigInformation().getDbConfig();
            Config config=user.getConfigInformation().getConfig();
            String codePath=config.getXmlFolderRootPath()+File.separatorChar+"code";
            if(!isDevp){//如果是不是开发,写在项目容器下面
            	String proNameMain = config.getDomainPackageNamePrefix().split("\\.")[(config.getDomainPackageNamePrefix().split("\\.").length-1)];
                String apiPath=codePath+File.separatorChar+"fas-engine-api"+File.separator+"fsoa-engine-api";
                String appPath=codePath+File.separatorChar+"fas-"+proNameMain+File.separator+"fas."+proNameMain;
                String domainPath=codePath+File.separatorChar+"fas-"+proNameMain+File.separator+"fas."+proNameMain;

                String daoPath=codePath+File.separatorChar+"fas-"+proNameMain+File.separator+"fas."+proNameMain;
                String servicePath=codePath+File.separatorChar+"fas-"+proNameMain+File.separator+"fas."+proNameMain;
                String providePath=codePath+File.separatorChar+"fas-"+proNameMain+File.separator+"fsoa."+proNameMain;
                config.setApiProjectPath(apiPath);
                config.setAppProjectPath(appPath);
                config.setDomainProjectPath(domainPath);
                config.setDaoProjectPath(daoPath);
                config.setServiceProjectPath(servicePath);
                config.setProvideProjectPath(providePath);
            }
            List<CreateCodeObjects> createCodeObjectsList=new ArrayList<CreateCodeObjects>();
            for(String fileName: fileNames.split(",")){
                List<String> names=new ArrayList<String>();
                names.add(fileName);
                CreateCodeObjects createCodeObjects= codeService.proCode(names,dbConfig,config).get(0);
                createCodeObjectsList.add(createCodeObjects);
            }
            if(createCodeObjectsList!=null&&createCodeObjectsList.size()>0){
                codeService.createFileByFtl(createCodeObjectsList,dbConfig, config);
            }
            if(!isDevp){
                //压缩
                String filename="code.zip";
                String fullFileName =codePath+File.separatorChar+filename;  
                new File(fullFileName).delete();
                FileToZip zca = new FileToZip(fullFileName);  
                
                zca.compressExe(codePath,user.getDownLoadTimes()==0);  
                //下载
                response.setContentType("application/octet-stream");  
                //设置Content-Disposition  
                response.setHeader("Content-Disposition", "attachment;filename="+filename);  
                //读取目标文件，通过response将目标文件写到客户端  
                //读取文件  
                InputStream in = new FileInputStream(fullFileName);  
                OutputStream out = response.getOutputStream();  
                  
                //写文件  
                int b;  
                while((b=in.read())!= -1)  
                {  
                    out.write(b);  
                }  
                  
                in.close();  
                out.close();  
                //删除文件
                new File(fullFileName).delete();
                user.setDownLoadTimes(user.getDownLoadTimes()+1);
                writeFile(user,request);
            }else{
                response.getWriter().print("<script>alert(\"生成完成\");window.history.back();</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return "/index";
    }
    @RequestMapping("/downWord")
    public void downWord(HttpServletRequest request,HttpServletResponse response) {
        try {
                String filename="readMe.doc";
                String fullFileName =request.getServletContext().getRealPath("/")+File.separator+"word"+File.separator+filename;  
                //下载
                response.setContentType("application/octet-stream");  
                //设置Content-Disposition  
                response.setHeader("Content-Disposition", "attachment;filename="+filename);  
                //读取目标文件，通过response将目标文件写到客户端  
                //读取文件  
                InputStream in = new FileInputStream(fullFileName);  
                OutputStream out = response.getOutputStream();  
                
                //写文件  
                int b;  
                while((b=in.read())!= -1)  
                {  
                    out.write(b);  
                }  
                
                in.close();  
                out.close();  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @RequestMapping("/saveXML")
    @ResponseBody
    public String createCode(HttpServletRequest request,HttpSession session,CreateCodeObjects createCodeObjects) {
        try {
            User user=(User) session.getAttribute("user");
            DBConfig dbConfig=user.getConfigInformation().getDbConfig();
            Config config=user.getConfigInformation().getConfig();
            /**
             * 赋值初始化信息
             */
            CreateCodeObjects co= user.getConfigInformation().getCreateCodeObjects();
            createCodeObjects.setAuthor(co.getAuthor());
            createCodeObjects.setCopyright(co.getCopyright());
            createCodeObjects.setVersion(co.getVersion());
            
            List<String> tableNames=xmlService.getTableNames(dbConfig,config);
            if(tableNames!=null&&tableNames.size()>0){
                for(CreateCodeObject createObject:createCodeObjects.getObjects()){
                    createObject.setApiPackageName(config.getApiPackageNamePrefix()+"."+CreateCodeUtil.getModuleNameByTableName(dbConfig.getDbName()));
                    createObject.setAppPackageName(config.getAppPackageNamePrefix()+"."+CreateCodeUtil.getModuleNameByTableName(dbConfig.getDbName()));
                    createObject.setDomainPackageName(config.getDomainPackageNamePrefix()+"."+CreateCodeUtil.getModuleNameByTableName(dbConfig.getDbName()));
                    
                    createObject.setDaoPackageName(config.getDaoPackageNamePrefix()+"."+CreateCodeUtil.getModuleNameByTableName(dbConfig.getDbName()));
                    createObject.setServicePackageName(config.getServicePackageNamePrefix()+"."+CreateCodeUtil.getModuleNameByTableName(dbConfig.getDbName()));
                    createObject.setProvidePackageName(config.getProvidePackageNamePrefix()+"."+CreateCodeUtil.getModuleNameByTableName(dbConfig.getDbName()));
                }
            }
            xmlService.createFileByFtl(createCodeObjects,dbConfig,config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"result\":\"sucess\"}";
    }
    
    @RequestMapping("/config")
    public String configPage(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
        User user=(User) session.getAttribute("user");
        request.setAttribute("isDevp", isDevp);
        if(user==null){
            try {
                response.sendRedirect("/");
                return "";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "/config/config";
    }
    
    @RequestMapping("/saveConfig")
    @ResponseBody
    public String saveConfig(HttpServletRequest request,HttpSession session,ConfigInformation configInformation) {
    	Config config = configInformation.getConfig();
    	config.setApiPackageNamePrefix(config.getDomainPackageNamePrefix());
    	config.setAppPackageNamePrefix(config.getDomainPackageNamePrefix());
    	config.setDomainPackageNamePrefix(config.getDomainPackageNamePrefix());
    	config.setDaoPackageNamePrefix(config.getDomainPackageNamePrefix());
    	config.setServicePackageNamePrefix(config.getDomainPackageNamePrefix());
    	config.setProvidePackageNamePrefix(config.getDomainPackageNamePrefix());
    	configInformation.setConfig(config);
    	
        User user=(User) session.getAttribute("user");
        String fileParentPath=confRootPath+File.separator+"created"+File.separator+user.getName()+File.separator+ configInformation.getDbConfig().getDbName()+"_"+new SimpleDateFormat("yyyyMMdd##HH#mm#ss").format(new Date());
        //如果一个数据库实例生成过代码,就不要重新创建文件假存储xml了
        if(user.getConfigInformation() !=null &&
                user.getConfigInformation().getConfig()!=null && 
                user.getConfigInformation().getConfig().getXmlFolderRootPath()!=null &&
                fileParentPath.contains(user.getConfigInformation().getDbConfig().getDbName())){
            fileParentPath=user.getConfigInformation().getConfig().getXmlFolderRootPath();
        }
        configInformation.getConfig().setXmlFolderRootPath(fileParentPath);
        user.setConfigInformation(configInformation);
        try {
            FileUtil.createDir(fileParentPath);
            xmlService.proXml(configInformation.getCreateCodeObjects(),configInformation.getDbConfig(),configInformation.getConfig());
            writeFile(user,request);
            return "{\"result\":\"sucess\"}";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "{\"result\":\"fail\"}";
        }
    }
    /**
     * 写文件
     * @param 
     * @return 
     * @author liub
     * @date 2017-1-9
     */
    protected void writeFile(User user,HttpServletRequest request) {
        PrintWriter pw = null;
        try {
            String fileParentPath=confRootPath+File.separator+"userConfig";
            FileUtil.createDir(fileParentPath);
            pw = new PrintWriter(new File(fileParentPath+File.separator+user.getName()));
            pw.write(user.toString());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(pw!=null){
                pw.close();
            }
        }
    }
    /**
     * 读取文件
     * @param 
     * @return 
     * @author liub
     * @date 2017-1-9
     */
    protected String readFile(User user,HttpServletRequest request) {
        StringBuffer json=new StringBuffer();
        BufferedReader read=null;
        try {
            String fileParentPath=confRootPath+File.separator+"userConfig";
            FileUtil.createDir(fileParentPath);
            read=new BufferedReader(new InputStreamReader(new FileInputStream(fileParentPath+File.separator+user.getName()),"utf-8"));
            String tempString = null;
            while((tempString=read.readLine())!=null){
                json.append(tempString);
            }
            read.close();
        } catch (Exception e) {
            if(e.toString().contains("java.io.FileNotFoundException")){
                return "";
            }else{
                e.printStackTrace();
            }
        }
        return json.toString();
    }
}
