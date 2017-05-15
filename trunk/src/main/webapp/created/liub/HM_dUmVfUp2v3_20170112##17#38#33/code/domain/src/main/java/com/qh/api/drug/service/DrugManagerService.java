package  com.qh.api.drug.service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * 
  * 药剂出入库服务
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
@javax.ws.rs.Path(value="/drugManager")
public interface DrugManagerService {
    
	/**
	 * 新增药剂出入库
	 * @param 药剂出入库
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
	 * 修改药剂出入库
	 * @param 药剂出入库
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
	 * 根据id查询药剂出入库
	 * @param id
	 * @return 药剂出入库
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@GET
    @Path("/query/{id}")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
	public Response queryById(@PathParam("id") String id);
	
	/**
	 * 根据药剂出入库查询药剂出入库
	 * @param 药剂出入库
	 * @return 药剂出入库
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@POST
    @Path("/queryByInfo")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
	public Response queryByInfo(String modelStr);
	
	/**
	 * 分页查询药剂出入库(单表)
	 * @param start 开始记录,size每页记录,modelStr 药剂出入库
	 * @return 药剂出入库集合
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
    @POST
    @Path("/page/{start}/{size}")
    @Consumes("application/json")
    @Produces("application/json;charset=UTF-8")
    public Response queryAll(@PathParam("start") Integer start,@PathParam("size") Integer size,String modelStr);
    

}