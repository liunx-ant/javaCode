package ${mainObject.servicePackageName}.service.impl;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

<#list objects as object>
import ${mainObject.domainPackageName}.domain.${object.className};
import ${mainObject.apiPackageName}.consumer.dto.${object.className}Dto;
import ${mainObject.apiPackageName}.consumer.vo.${object.className}Vo;
</#list>   
import ${mainObject.servicePackageName}.service.I${mainObject.className}Service;
<#list objects as object>
import ${mainObject.apiPackageName}.dao.${object.className}Dao;
</#list> 

/**
 * 
  * ${mainObject.title}服务
  * @author ${author}
  * @version ${version}
  * @date ${nowDate}
  * Copyright ${copyright}
 */
@Service
public class ${mainObject.className}ServiceImpl implements I${mainObject.className}Service {
    
<#list objects as object>
	//${object.title}Dao
    @Autowired
    private ${object.className}Dao ${object.objectName}Dao;
</#list>    

	/**
	 * 新增${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ResultMessage 对象
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@Override
	public int save(${mainObject.className} ${mainObject.objectName}){
		${mainObject.objectName}.setCreated(new Date());
		${mainObject.objectName}Dao.save(${mainObject.objectName});
		<#include "/service/createRelation.ftl"/>
		
		return 1;
	}

	/**
	 * 修改${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ResultMessage 对象
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@Override
	public int update(${mainObject.className} ${mainObject.objectName}){
		${mainObject.objectName}.setLastUpd(new Date());
		int i =${mainObject.objectName}Dao.updateByIdSelective(${mainObject.objectName});
		
<#list mainObject.objectRels as objectRel>
	<#if objectRel.relType == 'ONETOMANY'>
			<#include "/service/delRelation.ftl"/>
	</#if>
</#list>
		<#include "/service/modifyRelation.ftl"/>
		
		return i;
	}
	
	/**
	 * 根据id查询${mainObject.title}
	 * @param id
	 * @return ${mainObject.title}
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@Override
	public ${mainObject.className} queryById(Long id){
		${mainObject.className} ${mainObject.objectName} = new ${mainObject.className}();
		${mainObject.objectName}.setId(id);
		${mainObject.objectName}=${mainObject.objectName}Dao.getInfo(${mainObject.objectName});
		<#include "/service/queryRelation.ftl"/>
		
		return ${mainObject.objectName};
	}
	/**
	 * 根据${mainObject.title}查询${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ${mainObject.title}
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@Override
	public ${mainObject.className} queryByInfo(${mainObject.className} ${mainObject.objectName}){
		${mainObject.objectName}=${mainObject.objectName}Dao.getInfo(${mainObject.objectName});
		<#include "/service/queryRelation.ftl"/>
		
		return ${mainObject.objectName};
	}
	
	/**
	 * 分页查询${mainObject.title}(单表)
	 * @param ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    @Override
    public List<${mainObject.className}Vo> queryAll(${mainObject.className}Dto ${mainObject.objectName}Dto){
     
        return ${mainObject.objectName}Dao.getAll(${mainObject.objectName}Dto);
    }
	/**
	 * 分页查询${mainObject.title}(单表)
	 * @param start ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    @Override
    public Page<${mainObject.className}Vo> queryAll(${mainObject.className}Dto ${mainObject.objectName}Dto ,int pageNum , int pageSize){
     
        PageHelper.startPage((pageSize==0?1:pageNum),pageSize,"<#list objects as object><#list object.properties as property><#if property.propertyList.isExist && property.propertyList.orderBy != '' >${object.objectName}.${property.colName} ${property.propertyList.orderBy} ,</#if></#list></#list>${mainObject.objectName}.last_upd desc");//排序
        List<${mainObject.className}Vo> list = ${mainObject.objectName}Dao.getAll(${mainObject.objectName}Dto);
        return (Page<${mainObject.className}Vo>)list;
    }
    
<#if (mainObject.objectRels?size>0) >  
	/**
	 * 分页查询${mainObject.title}(内关联)
	 * @param start 开始记录,size每页记录, ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    @Override
    public Page<${mainObject.className}Vo> queryAllInnerJoin(${mainObject.className}Dto ${mainObject.objectName}Dto ,int pageNum , int pageSize){
    	${mainObject.className} ${mainObject.objectName}= JSON.parseObject(modelStr, ${mainObject.className}.class);
        if(pageSize==0){
	        PageHelper.startPage(1,pageSize,"<#list objects as object><#list object.properties as property><#if property.propertyList.isExist && property.propertyList.orderBy != '' >${object.objectName}.${property.colName} ${property.propertyList.orderBy} ,</#if></#list></#list>${mainObject.objectName}.last_upd desc");//排序
      	}else{
	        PageHelper.startPage((pageSize==0?1:pageNum),pageSize,"<#list objects as object><#list object.properties as property><#if property.propertyList.isExist && property.propertyList.orderBy != '' >${object.objectName}.${property.colName} ${property.propertyList.orderBy} ,</#if></#list></#list>${mainObject.objectName}.last_upd desc");//排序
      	}
      	List<${mainObject.className}Vo> list = ${mainObject.objectName}Dao.getAllInnerJoin(${mainObject.objectName}Dto);
        return (Page<${mainObject.className}Vo>)list;
    }
    
	/**
	 * 分页查询${mainObject.title}(左关联)
	 * @param start 开始记录,size每页记录, ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    @Override
    public Page<${mainObject.className}Vo> queryAllLeftJoin(${mainObject.className}Dto ${mainObject.objectName}Dto ,int pageNum , int pageSize){
    
    	${mainObject.className} ${mainObject.objectName}= JSON.parseObject(modelStr, ${mainObject.className}.class);
      	if(pageSize==0){
	        PageHelper.startPage(1,pageSize,"<#list objects as object><#list object.properties as property><#if property.propertyList.isExist && property.propertyList.orderBy != '' >${object.objectName}.${property.colName} ${property.propertyList.orderBy} ,</#if></#list></#list>${mainObject.objectName}.last_upd desc");//排序
      	}else{
	        PageHelper.startPage((pageSize==0?1:pageNum),pageSize,"<#list objects as object><#list object.properties as property><#if property.propertyList.isExist && property.propertyList.orderBy != '' >${object.objectName}.${property.colName} ${property.propertyList.orderBy} ,</#if></#list></#list>${mainObject.objectName}.last_upd desc");//排序
      	}
      	List<${mainObject.className}Vo> list = ${mainObject.objectName}Dao.getAllLeftJoin(${mainObject.objectName}Dto);
        return (Page<${mainObject.className}Vo>)list;
    }
</#if>    

}