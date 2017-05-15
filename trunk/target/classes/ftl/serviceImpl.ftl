package ${mainObject.apiPackageName}.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;

import ${projStructurePath}.util.common.ResultMessage;
import ${projStructurePath}.util.common.Constants;
import ${projStructurePath}.util.common.UUIDUtil;

<#list objects as object>
import ${mainObject.domainPackageName}. ${object.className};
</#list>   
import ${mainObject.apiPackageName}.service.${mainObject.className}Service;
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
@Transactional
public class ${mainObject.className}ServiceImpl implements ${mainObject.className}Service {
    
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
	public Response add(String modelStr){
		${mainObject.className} ${mainObject.objectName}= JSON.parseObject(modelStr, ${mainObject.className}.class);
		${mainObject.objectName}.setId(UUIDUtil.uuid());
		${mainObject.objectName}.setDeleteFlag(Constants.DATA_NORMAL_STATUS);
		${mainObject.objectName}.setCreateDate(new Date());
		${mainObject.objectName}.setModifyDate(new Date());
		${mainObject.objectName}Dao.insert(${mainObject.objectName});
		<#include "/service/createRelation.ftl"/>
		
		ResultMessage resultMessage=new ResultMessage(${mainObject.objectName});
		return Response.ok(resultMessage).build();
	}

	/**
	 * 修改${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ResultMessage 对象
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@Override
	public Response update(String id,String modelStr){
		${mainObject.className} ${mainObject.objectName}= JSON.parseObject(modelStr, ${mainObject.className}.class);
		${mainObject.objectName}.setModifyDate(new Date());
		${mainObject.objectName}Dao.updateSelective(${mainObject.objectName});
		
<#list mainObject.objectRels as objectRel>
	<#if objectRel.relType == 'ONETOMANY'>
			<#include "/service/delRelation.ftl"/>
	</#if>
</#list>
		<#include "/service/modifyRelation.ftl"/>
		
		ResultMessage resultMessage=new ResultMessage(${mainObject.objectName});
		return Response.ok(resultMessage).build();
	}
	
	/**
	 * 根据id查询${mainObject.title}
	 * @param id
	 * @return ${mainObject.title}
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@Override
	public Response queryById(String id){
		${mainObject.className} ${mainObject.objectName} = new ${mainObject.className}();
		${mainObject.objectName}.setId(id);
		${mainObject.objectName}=${mainObject.objectName}Dao.getInfo(${mainObject.objectName});
		<#include "/service/queryRelation.ftl"/>
		
		ResultMessage resultMessage=new ResultMessage(${mainObject.objectName});
		return Response.ok(resultMessage).build();
	}
	/**
	 * 根据${mainObject.title}查询${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ${mainObject.title}
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@Override
	public Response queryByInfo(String modelStr){
		${mainObject.className} ${mainObject.objectName}= JSON.parseObject(modelStr, ${mainObject.className}.class);
		${mainObject.objectName}=${mainObject.objectName}Dao.getInfo(${mainObject.objectName});
		<#include "/service/queryRelation.ftl"/>
		
		ResultMessage resultMessage=new ResultMessage(${mainObject.objectName});
		return Response.ok(resultMessage).build();
	}
	
	/**
	 * 分页查询${mainObject.title}(单表)
	 * @param start 开始记录,size每页记录,modelStr ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    @Override
    public Response queryAll(Integer start,Integer size,String modelStr){
     
      	${mainObject.className} ${mainObject.objectName}= JSON.parseObject(modelStr, ${mainObject.className}.class);
        PageHelper.startPage((size==0?1:start),size,"<#list objects as object><#list object.properties as property><#if property.propertyList.isExist && property.propertyList.orderBy != '' >${object.objectName}.${property.colName} ${property.propertyList.orderBy} ,</#if></#list></#list>${mainObject.objectName}.modify_date desc");//排序
        List<${mainObject.className}> list = ${mainObject.objectName}Dao.getAll(${mainObject.objectName});
        PageInfo<${mainObject.className}> pb=new PageInfo<${mainObject.className}>(list);
		ResultMessage resultMessage=new ResultMessage(pb);
        return Response.ok(resultMessage).build();
    }
    
<#if (mainObject.objectRels?size>0) >  
	/**
	 * 分页查询${mainObject.title}(内关联)
	 * @param start 开始记录,size每页记录,modelStr ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    @Override
    public Response queryAllInnerJoin(Integer start,Integer size,String modelStr){
    	${mainObject.className} ${mainObject.objectName}= JSON.parseObject(modelStr, ${mainObject.className}.class);
        if(size==0){
	        PageHelper.startPage(1,size,"<#list objects as object><#list object.properties as property><#if property.propertyList.isExist && property.propertyList.orderBy != '' >${object.objectName}.${property.colName} ${property.propertyList.orderBy} ,</#if></#list></#list>${mainObject.objectName}.modify_date desc");//排序
      	}else{
	        PageHelper.startPage(((start/size)+1),size,"<#list objects as object><#list object.properties as property><#if property.propertyList.isExist && property.propertyList.orderBy != '' >${object.objectName}.${property.colName} ${property.propertyList.orderBy} ,</#if></#list></#list>${mainObject.objectName}.modify_date desc");//排序
      	}
      	List<${mainObject.className}> list = ${mainObject.objectName}Dao.getAllInnerJoin(${mainObject.objectName});
        PageInfo<${mainObject.className}> pb=new PageInfo<${mainObject.className}>(list);
		ResultMessage resultMessage=new ResultMessage(pb);
        return Response.ok(resultMessage).build();
    }
    
	/**
	 * 分页查询${mainObject.title}(左关联)
	 * @param start 开始记录,size每页记录,modelStr ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    @Override
    public Response queryAllLeftJoin(Integer start,Integer size,String modelStr){
    
    	${mainObject.className} ${mainObject.objectName}= JSON.parseObject(modelStr, ${mainObject.className}.class);
		if(size==0){
	        PageHelper.startPage(1,size,"<#list objects as object><#list object.properties as property><#if property.propertyList.isExist && property.propertyList.orderBy != '' >${object.objectName}.${property.colName} ${property.propertyList.orderBy} ,</#if></#list></#list>${mainObject.objectName}.modify_date desc");//排序
      	}else{
	        PageHelper.startPage(((start/size)+1),size,"<#list objects as object><#list object.properties as property><#if property.propertyList.isExist && property.propertyList.orderBy != '' >${object.objectName}.${property.colName} ${property.propertyList.orderBy} ,</#if></#list></#list>${mainObject.objectName}.modify_date desc");//排序
      	}        
      	List<${mainObject.className}> list = ${mainObject.objectName}Dao.getAllLeftJoin(${mainObject.objectName});
        PageInfo<${mainObject.className}> pb=new PageInfo<${mainObject.className}>(list);
		ResultMessage resultMessage=new ResultMessage(pb);
		
        return Response.ok(resultMessage).build();
    }
</#if>    

}