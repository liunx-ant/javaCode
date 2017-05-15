package  com.qh.api.drug.service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * 
  * 药剂销毁服务
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
@javax.ws.rs.Path(value="/drugDestroy")
public interface DrugDestroyService {
    
	/**
	 * 新增药剂销毁
	 * @param 药剂销毁
	 * @return ResultMessage 对象
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@POST
    @Path("/add")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
	public Response add(String modelStr);

	/**
	 * 修改药剂销毁
	 * @param 药剂销毁
	 * @return ResultMessage 对象
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@POST
    @Path("/update/{id}")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
	public Response update(@PathParam("id") String id,String modelStr);
	
	/**
	 * 根据id查询药剂销毁
	 * @param id
	 * @return 药剂销毁
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@GET
    @Path("/query/{id}")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
	public Response queryById(@PathParam("id") String id);
	
	/**
	 * 根据药剂销毁查询药剂销毁
	 * @param 药剂销毁
	 * @return 药剂销毁
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@POST
    @Path("/queryByInfo")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
	public Response queryByInfo(String modelStr);
	
	/**
	 * 分页查询药剂销毁(单表)
	 * @param start 开始记录,size每页记录,modelStr 药剂销毁
	 * @return 药剂销毁集合
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
    @POST
    @Path("/page/{start}/{size}")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
    public Response queryAll(@PathParam("start") Integer start,@PathParam("size") Integer size,String modelStr);
    
	/**
	 * 分页查询药剂销毁(内关联)
	 * @param start 开始记录,size每页记录,modelStr 药剂销毁
	 * @return 药剂销毁集合
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
    @POST
    @Path("/pageInnerJoin/{start}/{size}")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
    public Response queryAllInnerJoin(@PathParam("start") Integer start,@PathParam("size") Integer size,String modelStr);
    
	/**
	 * 分页查询药剂销毁(左关联)
	 * @param start 开始记录,size每页记录,modelStr 药剂销毁
	 * @return 药剂销毁集合
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
    @POST
    @Path("/pageLeftJoin/{start}/{size}")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
    public Response queryAllLeftJoin(@PathParam("start") Integer start,@PathParam("size") Integer size,String modelStr);

}