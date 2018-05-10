package  ${mainObject.apiPackageName}.service;

import java.util.List;
import com.github.pagehelper.Page;
<#list objects as object>
import ${mainObject.domainPackageName}. ${object.className};
import ${mainObject.domainPackageName}. ${object.className}Dto;
import ${mainObject.domainPackageName}. ${object.className}Vo;
</#list>   

/**
 * 
  * ${mainObject.title}service
  * @author ${author}
  * @version ${version}
  * @date ${nowDate}
  * Copyright ${copyright}
 */
public interface ${mainObject.className}Service {
    
	/**
	 * 新增${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ResultMessage 对象
	 * @author ${author}
	 * @date ${nowDate}
	 */
	public int save(${mainObject.className} ${mainObject.objectName});

	/**
	 * 修改${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ResultMessage 对象
	 * @author ${author}
	 * @date ${nowDate}
	 */
	public int update(${mainObject.className} ${mainObject.objectName});
	
	/**
	 * 根据id查询${mainObject.title}
	 * @param id
	 * @return ${mainObject.title}
	 * @author ${author}
	 * @date ${nowDate}
	 */
	public ${mainObject.className} queryById(String id);
	
	/**
	 * 根据${mainObject.title}查询${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ${mainObject.title}
	 * @author ${author}
	 * @date ${nowDate}
	 */
	public ${mainObject.className} queryByInfo(${mainObject.className} ${mainObject.objectName});
	
	/**
	 * 分页查询${mainObject.title}(单表)
	 * @param ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    public List<${mainObject.className}Vo> queryAll(${mainObject.className}Dto ${mainObject.objectName}Dto);
	/**
	 * 分页查询${mainObject.title}(单表)
	 * @param start 开始记录,size每页记录, ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    public Page<${mainObject.className}Vo> queryAll(${mainObject.className}Dto ${mainObject.objectName}Dto ,int pageNum , int pageSize);
    
<#if (mainObject.objectRels?size>0) >  
	/**
	 * 分页查询${mainObject.title}(内关联)
	 * @param start 开始记录,size每页记录,modelStr ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    public Page<${mainObject.className}Vo> queryAllInnerJoin(${mainObject.className}Dto ${mainObject.objectName}Dto ,int pageNum , int pageSize);
    
	/**
	 * 分页查询${mainObject.title}(左关联)
	 * @param start 开始记录,size每页记录,modelStr ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    public Page<${mainObject.className}Vo> queryAllLeftJoin(${mainObject.className}Dto ${mainObject.objectName}Dto ,int pageNum , int pageSize);
</#if>    

}