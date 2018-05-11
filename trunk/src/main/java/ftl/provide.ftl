package ${mainObject.servicePackageName}.provide;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foriseland.fjf.rpc.storage.PageRequest;
import com.foriseland.fjf.rpc.storage.PageResponse;
import com.foriseland.fjf.rpc.storage.Request;
import com.foriseland.fjf.rpc.storage.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.pagehelper.Page;
import org.springframework.beans.BeanUtils;
<#list objects as object>
import ${mainObject.domainPackageName}.domain.${object.className};
import ${mainObject.apiPackageName}.consumer.dto.${object.className}Dto;
import ${mainObject.apiPackageName}.consumer.vo.${object.className}Vo;
</#list>   
import ${mainObject.apiPackageName}.consumer.api.I${mainObject.className}Api;
<#list objects as object>
import ${mainObject.servicePackageName}.service.I${object.className}Service;
</#list> 

/**
 * 
  * ${mainObject.title}服务
  * @author ${author}
  * @version ${version}
  * @date ${nowDate}
  * Copyright ${copyright}
 */
@Service("i${mainObject.className}Api")
public class ${mainObject.className}Provide implements I${mainObject.className}Api {
    private static final Logger logger = LoggerFactory.getLogger(${mainObject.className}Provide.class);
<#list objects as object>
	//${object.title}Service
    @Autowired
    private I${object.className}Service ${object.objectName}Service;
</#list>    

	/**
	 * 新增${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ResultMessage 对象
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@Override
	public Response<Integer> save(Request<${mainObject.className}Dto> req){
		Response<Integer> response = Response.create();
		try {
			${mainObject.className}Dto ${mainObject.objectName}Dto = req.getData();
			${mainObject.className} ${mainObject.objectName} = new ${mainObject.className}();
			BeanUtils.copyProperties(${mainObject.objectName}Dto, ${mainObject.objectName});
			${mainObject.objectName}Service.save(${mainObject.objectName});
			<#include "/service/createRelation.ftl"/>
			response.success(1);
		}catch(Exception e) {
			logger.error("*********新增${mainObject.title}*******"+e.getMessage());
			response.failure("500",e.getMessage());
		}
		return response;
	}

	/**
	 * 修改${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ResultMessage 对象
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@Override
	public Response<Integer> update(Request<${mainObject.className}Dto> req){
		
		Response<Integer> response = Response.create();
		try {
			${mainObject.className}Dto ${mainObject.objectName}Dto = req.getData();
			${mainObject.className} ${mainObject.objectName} = new ${mainObject.className}();
			BeanUtils.copyProperties(${mainObject.objectName}Dto, ${mainObject.objectName});
			response.success(${mainObject.objectName}Service.update(${mainObject.objectName}));
			<#list mainObject.objectRels as objectRel>
				<#if objectRel.relType == 'ONETOMANY'>
						<#include "/service/delRelation.ftl"/>
				</#if>
			</#list>
					<#include "/service/modifyRelation.ftl"/>
		}catch(Exception e) {
			logger.error("*********修改${mainObject.title}*******"+e.getMessage());
			response.failure("500",e.getMessage());
		}
		return response;
	}
	
	/**
	 * 根据id查询${mainObject.title}
	 * @param id
	 * @return ${mainObject.title}
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@Override
	public Response<${mainObject.className}Vo> queryById(Request<Long> req){
		Response<${mainObject.className}Vo> response = Response.create();
		try {
			${mainObject.className}Vo ${mainObject.objectName}Vo = new ${mainObject.className}Vo();
			${mainObject.className} ${mainObject.objectName} = ${mainObject.objectName}Service.queryById(req.getData());
			<#include "/service/queryRelation.ftl"/>
			BeanUtils.copyProperties(${mainObject.objectName}, ${mainObject.objectName}Vo);
			response.success(${mainObject.objectName}Vo);
		}catch(Exception e) {
			logger.error("*********根据id查询${mainObject.title}*******"+e.getMessage());
			response.failure("500",e.getMessage());
		}
		return response;
	}
	/**
	 * 根据${mainObject.title}查询${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ${mainObject.title}
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@Override
	public Response<${mainObject.className}Vo> queryByInfo(Request<${mainObject.className}Dto> req){
		Response<${mainObject.className}Vo> response = Response.create();
		try {
			${mainObject.className}Dto ${mainObject.objectName}Dto = req.getData();
			${mainObject.className}Vo ${mainObject.objectName}Vo = new ${mainObject.className}Vo();
			${mainObject.className} ${mainObject.objectName} = new ${mainObject.className}();
			BeanUtils.copyProperties(${mainObject.objectName}Dto, ${mainObject.objectName});
			${mainObject.objectName} = ${mainObject.objectName}Service.queryByInfo(${mainObject.objectName});
			BeanUtils.copyProperties(${mainObject.objectName}, ${mainObject.objectName}Vo);
			<#include "/service/queryRelation.ftl"/>
			response.success(${mainObject.objectName}Vo);
		}catch(Exception e) {
			logger.error("*********根据${mainObject.title}查询${mainObject.title}*******"+e.getMessage());
			response.failure("500",e.getMessage());
		}
		return response;
	}
	
	/**
	 * 查询${mainObject.title}(单表)
	 * @param ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    @Override
    public Response<List<${mainObject.className}Vo>> queryAll(Request<${mainObject.className}Dto> req){
    	Response<List<${mainObject.className}Vo>> response = Response.create();
     	try {
			${mainObject.className}Dto ${mainObject.objectName}Dto = req.getData();
			List<${mainObject.className}Vo> ${mainObject.objectName}Vos = ${mainObject.objectName}Service.queryAll(${mainObject.objectName}Dto);
			<#include "/service/queryRelation.ftl"/>
			response.success(${mainObject.objectName}Vos);
		}catch(Exception e) {
			logger.error("*********查询${mainObject.title}*******"+e.getMessage());
			response.failure("500",e.getMessage());
		}
		return response;
    }
	/**
	 * 分页查询${mainObject.title}(单表)
	 * @param start ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    @Override
     public PageResponse<List<${mainObject.className}Vo>> queryAll(PageRequest<${mainObject.className}Dto> req){
     	PageResponse<List<${mainObject.className}Vo>> response = PageResponse.create();
     	try {
			${mainObject.className}Dto ${mainObject.objectName}Dto = req.getData();
			Page<${mainObject.className}Vo> pageVo = ${mainObject.objectName}Service.queryAll(${mainObject.objectName}Dto,req.getPageNum(),req.getPageSize());
			<#include "/service/queryRelation.ftl"/>
			response.success(pageVo.getResult());
			response.setPageNum(pageVo.getPageNum());
			response.setPageSize(pageVo.getPageSize());
			response.setTotalRecordCount(pageVo.getTotal());
		}catch(Exception e) {
			logger.error("*********分页查询${mainObject.title}*******"+e.getMessage());
			response.failure("500",e.getMessage());
		}
		return response;
    }

}