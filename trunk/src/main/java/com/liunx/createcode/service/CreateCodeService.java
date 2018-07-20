package com.liunx.createcode.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.liunx.common.util.DelAllFile;
import com.liunx.common.util.Freemarker;
import com.liunx.createcode.config.Config;
import com.liunx.createcode.config.DBConfig;
import com.liunx.createcode.util.CreateCodeObject;
import com.liunx.createcode.util.CreateCodeObjects;
import com.liunx.createcode.util.CreateCodeUtil;
import com.liunx.createcode.util.ObjectRel;
import com.liunx.createcode.util.Property;
import com.liunx.createcode.util.PropertyEdit;
import com.liunx.createcode.util.PropertyList;
import com.liunx.createcode.util.PropertyNew;
import com.liunx.createcode.util.PropertyQuery;
import com.liunx.createcode.util.PropertyView;

/**
  * Description
  * @author liub
  * @version 1.0
  * @date 2017-3-28
  * Copyright
 */
@Service
public class CreateCodeService {
    /**
     * 
     * Description
     * @param 
     * @return 
     * @author liub
     * @date 2017-3-28
     */
    public List<CreateCodeObjects> proCode(List<String> fileNames,DBConfig dbConfig,Config config) throws Exception {
        List<CreateCodeObjects> objectsList = new ArrayList<CreateCodeObjects>();

        // 将xml配置文件内容转成createCodeObjects对象
        File xmlFolder = new File(config.getXmlFolderRootPath());
        File list[] = xmlFolder.listFiles();
        /**显示目录结构*/
        for(File file:list){
            if(file.isFile()&&!file.isDirectory()){
                if(fileNames.contains(file.getName())){
                    CreateCodeObjects createCodeObjects = getObjectsByXml(file.getPath());
                    // 通过ftl生成文件
//                createFileByFtl(createCodeObjects);
                    objectsList.add(createCodeObjects);
                }
            }   
        }
        return objectsList;
    }

    /**
     * 
     * Description
     * @param 
     * @return 
     * @author liub
     * @date 2017-3-28
     */
    public void createFileByFtl(List<CreateCodeObjects> createCodeObjectsList,DBConfig dbConfig,Config config) throws Exception {
        String g=File.separator;
        Map<String,Object> dbMap=JSON.parseObject(JSON.toJSONString(dbConfig),Map.class);
        String serviceImplPath="";
        List<String> serviceImplNames=new ArrayList<String>();
        Map<String, Object> rootMap=new HashMap<String, Object>();
        
        // 生成代码前,先清空之前生成的代码
        DelAllFile.delFolder(config.getApiProjectPath());
        DelAllFile.delFolder(config.getAppProjectPath()+".controller");
        DelAllFile.delFolder(config.getDomainProjectPath()+".domain");
        DelAllFile.delFolder(config.getDaoProjectPath()+".dao");
        DelAllFile.delFolder(config.getServiceProjectPath()+".service");
        DelAllFile.delFolder(config.getProvideProjectPath()+".provide");
        for(CreateCodeObjects createCodeObjects : createCodeObjectsList){
            // 设置FreeMarker 使用的root值
            rootMap = CreateCodeUtil.createCodeObjectsToMap(createCodeObjects);
            System.out.println(createCodeObjects.getMainObject().getTitle()+":");
            System.out.println("    api文件生成:"+config.getApiProjectPath());
            System.out.println("    app文件生成:"+config.getAppProjectPath()+".controller");
            System.out.println("    domain文件生成:"+config.getDomainProjectPath()+".domain");
            System.out.println("    dao文件生成:"+config.getDaoProjectPath()+".dao");
            System.out.println("    service文件生成:"+config.getServiceProjectPath()+".service");
            System.out.println("    provide文件生成:"+config.getProvideProjectPath()+".provide");
            String []s=createCodeObjects.getMainObject().getApiPackageName().split("\\.");
            String []s1=createCodeObjects.getMainObject().getAppPackageName().split("\\.");
            String []s2=createCodeObjects.getMainObject().getDomainPackageName().split("\\.");
            String ss="";
            String projStructurePathStr="";
            ss:for(int i=0;i<(s.length>s1.length?(s1.length>s2.length?s2.length:s.length):(s.length>s2.length?s2.length:s.length));i++){
                if(s[i].equals(s1[i])){
                    if(s[i].equals(s2[i])){
                        ss=ss+s[i]+g;
                        projStructurePathStr+=s[i]+".";
                    }else{
                        break ss;
                    }
                }else{
                    break ss;
                }
            }
            Map<String,Object> projStructurePath=new HashMap<String,Object>();
            projStructurePath.put("projStructurePath",projStructurePathStr.substring(0,projStructurePathStr.length()-1));
            rootMap.putAll(projStructurePath);
            
            String baseStr = config.getJavaFolderRootPath();
            for(String path:config.getDomainPackageNamePrefix().split("\\.")) {
            	baseStr =baseStr+g+path;
            }
            String domain = config.getDomainProjectPath()+".domain" +baseStr;
            String dao = config.getDaoProjectPath()+".dao"+baseStr;
            String service = config.getServiceProjectPath()+".service"+baseStr;
            String provide = config.getProvideProjectPath()+".provide"+baseStr;
            String controller = config.getAppProjectPath()+".controller"+baseStr;
            
            String api = config.getApiProjectPath()+baseStr+g+"consumer"+g ;
           
            //配置文件
//            Freemarker.printFile("api_envProperties.ftl", dbMap, "env.properties", config.getApiProjectPath()+g+"src"+g+"main"+g+"webapp"+g+"WEB-INF"+g+"config", config.getFtlFolderPath()+g+"common");
//            Freemarker.printFile("api_springSystem.ftl",projStructurePath, "spring-system.xml", config.getApiProjectPath()+g+"src"+g+"main"+g+"webapp"+g+"WEB-INF"+g+"config", config.getFtlFolderPath()+g+"common");
//            Freemarker.printFile("app_envProperties.ftl", rootMap, "env.properties", config.getAppProjectPath()+g+"src"+g+"main"+g+"webapp"+g+"WEB-INF"+g+"config", config.getFtlFolderPath()+g+"common");
//            Freemarker.printFile("app_springSystem.ftl",projStructurePath, "spring-system.xml", config.getAppProjectPath()+g+"src"+g+"main"+g+"webapp"+g+"WEB-INF"+g+"config", config.getFtlFolderPath()+g+"common");
            //公用文件 app
//            Freemarker.printFile("appController.ftl", rootMap, "AppController.java",config.getAppProjectPath()+config.getJavaFolderRootPath() + createCodeObjects.getMainObject().getAppRelativePath() + g+"controller"+g, config.getFtlFolderPath()+g+"common");
//            Freemarker.printFile("serviceUrl.ftl", rootMap, "ServiceUrl.java", config.getAppProjectPath()+config.getJavaFolderRootPath() + ss+"util"+g, config.getFtlFolderPath()+g+"common");
            //公用文件 common
//            Freemarker.printFile("UUIDUtil.ftl", rootMap,"UUIDUtil.java",config.getDomainProjectPath()+config.getJavaFolderRootPath() + projStructurePath.get("projStructurePath").toString().replaceAll("\\.","\\"+g)+g+"util"+g+"common", config.getFtlFolderPath()+g+"common");
//            Freemarker.printFile("constants.ftl", rootMap,"Constants.java",config.getDomainProjectPath()+config.getJavaFolderRootPath() + projStructurePath.get("projStructurePath").toString().replaceAll("\\.","\\"+g)+g+"util"+g+"common", config.getFtlFolderPath()+g+"common");
//            Freemarker.printFile("propertiesUtil.ftl", rootMap,"PropertiesUtil.java",config.getDomainProjectPath()+config.getJavaFolderRootPath() + projStructurePath.get("projStructurePath").toString().replaceAll("\\.","\\"+g)+g+"util"+g+"common", config.getFtlFolderPath()+g+"common");
//            Freemarker.printFile("resultMessage.ftl", rootMap,"ResultMessage.java",config.getDomainProjectPath()+config.getJavaFolderRootPath() + projStructurePath.get("projStructurePath").toString().replaceAll("\\.","\\"+g)+g+"util"+g+"common", config.getFtlFolderPath()+g+"common");
            
//            Freemarker.printFile("commonController.ftl", rootMap,"Controller.java",config.getDomainProjectPath()+config.getJavaFolderRootPath() + projStructurePath.get("projStructurePath").toString().replaceAll("\\.","\\"+g)+g+"util"+g+"web", config.getFtlFolderPath()+g+"common");
//            Freemarker.printFile("baseDao.ftl", rootMap,"BaseDao.java",config.getDomainProjectPath()+config.getJavaFolderRootPath() + projStructurePath.get("projStructurePath").toString().replaceAll("\\.","\\"+g)+g+"util"+g+"dao", config.getFtlFolderPath()+g+"common");
//            Freemarker.printFile("model.ftl", rootMap,"Model.java",config.getDomainProjectPath()+config.getJavaFolderRootPath() + projStructurePath.get("projStructurePath").toString().replaceAll("\\.","\\"+g)+g+"util"+g+"domain", config.getFtlFolderPath()+g+"common");
            Freemarker.printFile("ognl.ftl", rootMap,"Ognl.java", config.getDaoProjectPath()+".dao"+config.getJavaFolderRootPath(), config.getFtlFolderPath()+g+"common");
            
            
            for (CreateCodeObject createCodeObject : createCodeObjects.getObjects()) {
                if (createCodeObject.getIsMain()) {
                    serviceImplNames.add(createCodeObject.getClassName() + "ServiceImpl");
                    
                    //公用文件 common
//                    Freemarker.printFile("serviceDoc.ftl", rootMap,createCodeObject.getObjectName()+"ServiceDoc.text",config.getDomainProjectPath()+g+"doc"+g, config.getFtlFolderPath()+g+"common");
                    //jsp
//                    Freemarker.printFile("jsp_home.ftl", rootMap, "home.jsp", config.getAppProjectPath()+config.getJspFolderRootPath() + createCodeObject.getObjectName() + g, config.getFtlFolderPath());
//                    Freemarker.printFile("jsp_new.ftl", rootMap, "new.jsp", config.getAppProjectPath()+config.getJspFolderRootPath() + createCodeObject.getObjectName() + g, config.getFtlFolderPath());
//                    Freemarker.printFile("jsp_edit.ftl", rootMap, "edit.jsp", config.getAppProjectPath()+config.getJspFolderRootPath() + createCodeObject.getObjectName() + g, config.getFtlFolderPath());
//                    Freemarker.printFile("jsp_view.ftl", rootMap, "view.jsp", config.getAppProjectPath()+config.getJspFolderRootPath() + createCodeObject.getObjectName() + g, config.getFtlFolderPath());
//                    Freemarker.printFile("table.ftl", rootMap, createCodeObject.getObjectName()+"Template.jspf", config.getAppProjectPath()+config.getJspFolderRootPath() + createCodeObject.getObjectName() + g, config.getFtlFolderPath()+"/form/home");
                    //js
//                    Freemarker.printFile("business.ftl", rootMap,createCodeObject.getObjectName()+"Business.js",config.getAppProjectPath()+ config.getJsFolderRootPath() + g+"js"+g+createCodeObject.getObjectName()+g+"controller"+g, config.getFtlFolderPath());
//                    Freemarker.printFile("initList.ftl", rootMap,createCodeObject.getObjectName()+"List.js", config.getAppProjectPath()+config.getJsFolderRootPath() + g+"js"+g+createCodeObject.getObjectName()+g+"controller"+g, config.getFtlFolderPath()+g+"init"+g+"page");
//                    Freemarker.printFile("initInfo.ftl", rootMap,createCodeObject.getObjectName()+"Info.js",config.getAppProjectPath()+ config.getJsFolderRootPath() + g+"js"+g+createCodeObject.getObjectName()+g+"controller"+g, config.getFtlFolderPath()+g+"init"+g+"page");
//                    Freemarker.printFile("initEdit.ftl", rootMap,createCodeObject.getObjectName()+"Edit.js",config.getAppProjectPath()+ config.getJsFolderRootPath() + g+"js"+g+createCodeObject.getObjectName()+g+"controller"+g, config.getFtlFolderPath()+g+"init"+g+"page");
//                    Freemarker.printFile("initAdd.ftl", rootMap,createCodeObject.getObjectName()+"Add.js", config.getAppProjectPath()+config.getJsFolderRootPath() + g+"js"+g+createCodeObject.getObjectName()+g+"controller"+g, config.getFtlFolderPath()+g+"init"+g+"page");
                    //java
//                    Freemarker.printFile("controller.ftl", rootMap, createCodeObject.getClassName() + "Controller.java", config.getAppProjectPath()+config.getJavaFolderRootPath() + createCodeObject.getAppRelativePath() + g+"controller"+g, config.getFtlFolderPath());

                    Freemarker.printFile("service.ftl", rootMap, "I"+createCodeObject.getClassName() + "Service.java", service+ g+"service"+g, config.getFtlFolderPath());
                    Freemarker.printFile("serviceImpl.ftl", rootMap, createCodeObject.getClassName() + "ServiceImpl.java", service+g+"service"+g+"impl", config.getFtlFolderPath());
                    //实体
                    Freemarker.printFile("entity.ftl", rootMap, createCodeObject.getClassName() + ".java", domain+g+"domain", config.getFtlFolderPath());
                    
                    Freemarker.printFile("provide.ftl", rootMap, createCodeObject.getClassName() + "Provide.java", provide+g+"provide"+g, config.getFtlFolderPath());

                    Freemarker.printFile("api.ftl", rootMap, "I"+createCodeObject.getClassName() + "Api.java",  api+g+"api", config.getFtlFolderPath());
                    Freemarker.printFile("vo.ftl", rootMap, createCodeObject.getClassName() + "Vo.java", api+g+"vo", config.getFtlFolderPath());
                    Freemarker.printFile("dto.ftl", rootMap, createCodeObject.getClassName() + "Dto.java",api +g+"dto", config.getFtlFolderPath());
                    //sql
                    Freemarker.printFile("dao.ftl", rootMap, "I"+createCodeObject.getClassName() + "Dao.java", dao + g+"dao"+g, config.getFtlFolderPath());
                    Freemarker.printFile("mapper.ftl", rootMap, createCodeObject.getClassName() + "Mapper.xml", dao + g+"mappers"+g, config.getFtlFolderPath());
                }
            }
            System.out.println(createCodeObjects.getMainObject().getTitle()+"完成!");
            System.out.println();
        }
        
//        System.out.println("生成RESTfulApplication...");
//        Map<String,Object> tables=new HashMap<String,Object>();
//        tables.put("applicationName",rootMap.get("applicationName"));
//        tables.put("apiPackageNamePrefix",config.getApiPackageNamePrefix());
//        tables.put("serviceImplNames", serviceImplNames);
//        tables.put("servicePath", (createCodeObjectsList.get(0).getMainObject().getApiRelativePath() + g+"service"+g).replaceAll("\\\\",".").replaceAll("/","."));
//        Freemarker.printFile("RESTfulApplication.ftl", tables, "RESTfulApplication.java", config.getApiProjectPath()+config.getJavaFolderRootPath()+ config.getApiPackageNamePrefix().replace(".", File.separator), config.getFtlFolderPath()+g+"common");
//        System.out.println("生成RESTfulApplication完成");
    }


    /**
     * 通过配置文件，获得ObjectCreateInfoList
     * 
     * @param object
     * @return
     * @throws DocumentException
     */
    private List<CreateCodeObject> getObjectCreateInfoListByXml(Element rootElement) throws DocumentException {
        List list = new ArrayList();
        for (Iterator iteratorObject = rootElement.elementIterator("object"); iteratorObject.hasNext();) {
            Element objectElement = (Element) iteratorObject.next();
            CreateCodeObject createCodeObject = getObjectCreateInfoByXml(objectElement);
            createCodeObject.setProperties(getPropertiesByXml(objectElement));
            createCodeObject.setObjectRels(getObjectRelsCreateInfoByXml(objectElement));
            list.add(createCodeObject);
        }
        return list;
    }

    /**
     * 通过配置文件，获得ObjectRels
     * 
     * @param objectElement
     * @return
     */
    private List getObjectRelsCreateInfoByXml(Element objectElement) throws DocumentException {
        Element objectRelsElement = objectElement.element("objectRels");
        List<ObjectRel> objectRels = new ArrayList();
        if (objectRelsElement != null) {
            for (Iterator objectRelIterator = objectRelsElement.elementIterator("objectRel"); objectRelIterator.hasNext();) {
                Element objectRelElement = (Element) objectRelIterator.next();
                objectRels.add(getObjectRelByXml(objectRelElement));
            }
        }
        return objectRels;
    }

    /**
     * 通过配置文件，获得ObjectRel
     * 
     * @param objectRelElement
     * @return
     */
    private ObjectRel getObjectRelByXml(Element objectRelElement) {
        ObjectRel objectRel = new ObjectRel();
        objectRel.setRelClassName(objectRelElement.attribute("relClassName").getValue());
        objectRel.setRelProperty(objectRelElement.attribute("relProperty").getValue());
        objectRel.setRelToClassName(objectRelElement.attribute("relToClassName").getValue());
        objectRel.setRelToProperty(objectRelElement.attribute("relToProperty").getValue());
        objectRel.setRelType(objectRelElement.attribute("relType").getValue().toUpperCase());
        return objectRel;
    }

    /**
     * 通过配置文件，获得Properties
     * 
     * @param objectElement
     * @return
     */

    private List<Property> getPropertiesByXml(Element objectElement) {
        Element propertiesElement = objectElement.element("properties");
        List<Property> properties = new ArrayList();
        for (Iterator propertyIterator = propertiesElement.elementIterator("property"); propertyIterator.hasNext();) {
            Element propertyElement = (Element) propertyIterator.next();
            properties.add(getPropertyByXml(propertyElement));
        }
        return properties;
    }

    /**
     * 通过xml获得Property
     * 
     * @param propertiesElement
     * @return
     */
    private Property getPropertyByXml(Element propertyElement) {
        Property property = new Property();
        property.setColName(propertyElement.attribute("colName").getValue());
        property.setName(propertyElement.attribute("name").getValue());
        property.setColType(propertyElement.attribute("colType").getValue());
        property.setLenth(propertyElement.attribute("lenth").getValue());
        if (!propertyElement.attribute("isPk").getValue().equals("")) {
            property.setIsPk(Boolean.parseBoolean(propertyElement.attribute("isPk").getValue()));
        }
        property.setDictType(propertyElement.attribute("dictType").getValue());
        property.setTitle(propertyElement.attribute("title").getValue());

        property.setPropertyEdit(getPropertyEditByXml(propertyElement.element("edit")));
        property.setPropertyNew(getPropertyNewByXml(propertyElement.element("new")));
        property.setPropertyList(getPropertyListByXml(propertyElement.element("list")));
        property.setPropertyQuery(getPropertyQueryByXml(propertyElement.element("query")));
        property.setPropertyView(getPropertyViewByXml(propertyElement.element("view")));

        return property;
    }

    /**
     * 通过xml获得Property
     * 
     * @param propertiesElement
     * @return
     */
    private PropertyNew getPropertyNewByXml(Element propertyElementNew) {
        PropertyNew propertyNew = new PropertyNew();
        propertyNew.setIsMust(false);
        propertyNew.setIsExist(false);
        if(propertyElementNew!=null){
            if (!propertyElementNew.attribute("isExist").getValue().equals("")) {
                propertyNew.setIsExist(Boolean.parseBoolean(propertyElementNew.attribute("isExist").getValue()));
            }
            propertyNew.setShowType(propertyElementNew.attribute("showType").getValue().toUpperCase());
            if (!propertyElementNew.attribute("isMust").getValue().equals("")) {
                propertyNew.setIsMust(Boolean.parseBoolean(propertyElementNew.attribute("isMust").getValue()));
            }
        }
        return propertyNew;
    }

    /**
     * 通过xml获得Property
     * 
     * @param propertiesElement
     * @return
     */
    private PropertyList getPropertyListByXml(Element propertyElementList) {
        PropertyList propertyList = new PropertyList();
        propertyList.setIsExist(false);
        if (!propertyElementList.attribute("isExist").getValue().equals("")) {
            propertyList.setIsExist(Boolean.parseBoolean(propertyElementList.attribute("isExist").getValue()));
        }
        propertyList.setShowType(propertyElementList.attribute("showType").getValue().toUpperCase());
        propertyList.setOrderBy(propertyElementList.attribute("orderBy").getValue());
        return propertyList;
    }

    /**
     * 通过xml获得Property
     * 
     * @param propertiesElement
     * @return
     */
    private PropertyEdit getPropertyEditByXml(Element propertyElementEdit) {
        PropertyEdit propertyEdit = new PropertyEdit();
        propertyEdit.setIsMust(false);
        propertyEdit.setIsExist(false);
        if (!propertyElementEdit.attribute("isExist").getValue().equals("")) {
            propertyEdit.setIsExist(Boolean.parseBoolean(propertyElementEdit.attribute("isExist").getValue()));
        }
        propertyEdit.setShowType(propertyElementEdit.attribute("showType").getValue().toUpperCase());
        if (!propertyElementEdit.attribute("isMust").getValue().equals("")) {
            propertyEdit.setIsMust(Boolean.parseBoolean(propertyElementEdit.attribute("isMust").getValue()));
        }
        return propertyEdit;
    }

    /**
     * 通过xml获得Property
     * 
     * @param propertiesElement
     * @return
     */
    private PropertyQuery getPropertyQueryByXml(Element propertyElementQuery) {
        PropertyQuery propertyQuery = new PropertyQuery();
        propertyQuery.setIsExist(false);
        if (!propertyElementQuery.attribute("isExist").getValue().equals("")) {
            propertyQuery.setIsExist(Boolean.parseBoolean(propertyElementQuery.attribute("isExist").getValue()));
        }
        propertyQuery.setShowType(propertyElementQuery.attribute("showType").getValue().toUpperCase());
        propertyQuery.setQueryType(propertyElementQuery.attribute("queryType").getValue().toUpperCase());
        return propertyQuery;
    }

    /**
     * 通过xml获得Property
     * 
     * @param propertiesElement
     * @return
     */
    private PropertyView getPropertyViewByXml(Element propertyElementView) {
        PropertyView propertyView = new PropertyView();
        propertyView.setIsExist(false);
        if (!propertyElementView.attribute("isExist").getValue().equals("")) {
            propertyView.setIsExist(Boolean.parseBoolean(propertyElementView.attribute("isExist").getValue()));
        }
        propertyView.setShowType(propertyElementView.attribute("showType").getValue().toUpperCase());
        return propertyView;
    }

    /**
     * 通过配置文件，获得ObjectCreateInfo
     * 
     * @param object
     * @return
     * @throws DocumentException
     */
    private CreateCodeObject getObjectCreateInfoByXml(Element objectElement) throws DocumentException {
        CreateCodeObject objectCreateInfo = new CreateCodeObject();
        objectCreateInfo.setClassName(objectElement.element("className").getText());
        objectCreateInfo.setAppPackageName(objectElement.element("appPackageName").getText());
        objectCreateInfo.setApiPackageName(objectElement.element("apiPackageName").getText());
        objectCreateInfo.setDomainPackageName(objectElement.element("domainPackageName").getText());
        objectCreateInfo.setDaoPackageName(objectElement.element("daoPackageName").getText());
        objectCreateInfo.setServicePackageName(objectElement.element("servicePackageName").getText());
        objectCreateInfo.setProvidePackageName(objectElement.element("providePackageName").getText());
        objectCreateInfo.setTableName(objectElement.element("tableName").getText());
        objectCreateInfo.setTitle(objectElement.element("title").getText());
        objectCreateInfo.setIsMain(false);
        if (!objectElement.element("isMain").getText().equals("")) {
            objectCreateInfo.setIsMain(Boolean.parseBoolean(objectElement.element("isMain").getText()));
        }
        return objectCreateInfo;
    }

    /**
     * 获取注释信息
     * 
     * @param
     * @return
     * @author liub
     * @date 2016-7-14
     */
    private CreateCodeObjects getObjectsByXml(String xmlPath) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(new File(xmlPath));
        Element rootElement = doc.getRootElement();
        CreateCodeObjects createCodeObjects = new CreateCodeObjects();
        createCodeObjects.setAuthor(rootElement.element("author").getTextTrim());
        createCodeObjects.setVersion(rootElement.element("version").getTextTrim());
        createCodeObjects.setCopyright(rootElement.element("copyright").getTextTrim());
        createCodeObjects.setObjects(getObjectCreateInfoListByXml(rootElement));
        return createCodeObjects;
    }

    /**
     * 2个数组copy
     * 
     * @param news
     * @param olds
     */
    public void arrryCopy(String[] news, String[] olds) {
        for (int j = 0; j < olds.length; j++) {
            news[j] = olds[j];
        }
    }

    /**
     * 通过字段名获得model中的属性
     * 
     * @param field
     * @return
     */
    public String getPropertyNameByFieldName(String field) {
        String propertyName = "";
        String[] fieldPart = field.split("_");

        for (int i = 0; i < fieldPart.length; i++) {
            if (i == 0) {
                propertyName = fieldPart[i];
            } else {

                propertyName += firstCharUpper(fieldPart[i]);
            }
        }
        return propertyName;

    }

    public String firstCharLower(String str) {
        str = str.substring(0, 1).toLowerCase() + str.substring(1);
        return str;
    }

    public String firstCharUpper(String str) {
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return str;
    }

}
