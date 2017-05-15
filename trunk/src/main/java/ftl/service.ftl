package  ${mainObject.apiPackageName}.service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * 
  * ${mainObject.title}服务
  * @author ${author}
  * @version ${version}
  * @date ${nowDate}
  * Copyright ${copyright}
 */
@javax.ws.rs.Path(value="/${mainObject.objectName}")
public interface ${mainObject.className}Service {
    
	/**
	 * 新增${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ResultMessage 对象
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@POST
    @Path("/add")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
	public Response add(String modelStr);

	/**
	 * 修改${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ResultMessage 对象
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@POST
    @Path("/update/{id}")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
	public Response update(@PathParam("id") String id,String modelStr);
	
	/**
	 * 根据id查询${mainObject.title}
	 * @param id
	 * @return ${mainObject.title}
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@GET
    @Path("/query/{id}")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
	public Response queryById(@PathParam("id") String id);
	
	/**
	 * 根据${mainObject.title}查询${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ${mainObject.title}
	 * @author ${author}
	 * @date ${nowDate}
	 */
	@POST
    @Path("/queryByInfo")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
	public Response queryByInfo(String modelStr);
	
	/**
	 * 分页查询${mainObject.title}(单表)
	 * @param start 开始记录,size每页记录,modelStr ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    @POST
    @Path("/page/{start}/{size}")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
    public Response queryAll(@PathParam("start") Integer start,@PathParam("size") Integer size,String modelStr);
    
<#if (mainObject.objectRels?size>0) >  
	/**
	 * 分页查询${mainObject.title}(内关联)
	 * @param start 开始记录,size每页记录,modelStr ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    @POST
    @Path("/pageInnerJoin/{start}/{size}")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
    public Response queryAllInnerJoin(@PathParam("start") Integer start,@PathParam("size") Integer size,String modelStr);
    
	/**
	 * 分页查询${mainObject.title}(左关联)
	 * @param start 开始记录,size每页记录,modelStr ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    @POST
    @Path("/pageLeftJoin/{start}/{size}")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
    public Response queryAllLeftJoin(@PathParam("start") Integer start,@PathParam("size") Integer size,String modelStr);
</#if>    

}