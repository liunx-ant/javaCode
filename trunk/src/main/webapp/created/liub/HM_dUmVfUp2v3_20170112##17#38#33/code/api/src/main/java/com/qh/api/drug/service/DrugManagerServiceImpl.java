package com.qh.api.drug.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;

import com.qh.util.common.ResultMessage;
import com.qh.util.common.Constants;
import com.qh.util.common.UUIDUtil;

import com.qh.domain.drug. DrugManager;
import com.qh.api.drug.service.DrugManagerService;
import com.qh.api.drug.dao.DrugManagerDao;

/**
 * 
  * 药剂出入库服务
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
@Service
@Transactional
public class DrugManagerServiceImpl implements DrugManagerService {
    
	//药剂出入库Dao
    @Autowired
    private DrugManagerDao drugManagerDao;

	/**
	 * 新增药剂出入库
	 * @param 药剂出入库
	 * @return ResultMessage 对象
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response add(String modelStr){
		DrugManager drugManager= JSON.parseObject(modelStr, DrugManager.class);
		drugManager.setId(UUIDUtil.uuid());
		drugManager.setDeleteFlag(Constants.DATA_NORMAL_STATUS);
		drugManager.setCreateDate(new Date());
		drugManager.setModifyDate(new Date());
		drugManagerDao.insert(drugManager);

		
		ResultMessage resultMessage=new ResultMessage(drugManager);
		return Response.ok(resultMessage).build();
	}

	/**
	 * 修改药剂出入库
	 * @param 药剂出入库
	 * @return ResultMessage 对象
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response update(String id,String modelStr){
		DrugManager drugManager= JSON.parseObject(modelStr, DrugManager.class);
		drugManager.setModifyDate(new Date());
		drugManagerDao.updateSelective(drugManager);
		

			
		ResultMessage resultMessage=new ResultMessage(drugManager);
		return Response.ok(resultMessage).build();
	}
	
	/**
	 * 根据id查询药剂出入库
	 * @param id
	 * @return 药剂出入库
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response queryById(String id){
		DrugManager drugManager = new DrugManager();
		drugManager.setId(id);
		drugManager=drugManagerDao.getInfo(drugManager);
		
	
		
		ResultMessage resultMessage=new ResultMessage(drugManager);
		return Response.ok(resultMessage).build();
	}
	/**
	 * 根据药剂出入库查询药剂出入库
	 * @param 药剂出入库
	 * @return 药剂出入库
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response queryByInfo(String modelStr){
		DrugManager drugManager= JSON.parseObject(modelStr, DrugManager.class);
		drugManager=drugManagerDao.getInfo(drugManager);
		
	
		
		ResultMessage resultMessage=new ResultMessage(drugManager);
		return Response.ok(resultMessage).build();
	}
	
	/**
	 * 分页查询药剂出入库(单表)
	 * @param start 开始记录,size每页记录,modelStr 药剂出入库
	 * @return 药剂出入库集合
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
    @Override
    public Response queryAll(Integer start,Integer size,String modelStr){
     
      	DrugManager drugManager= JSON.parseObject(modelStr, DrugManager.class);
        PageHelper.startPage((size==0?1:start),size,"drugManager.modify_date desc");//排序
        List<DrugManager> list = drugManagerDao.getAll(drugManager);
        PageInfo<DrugManager> pb=new PageInfo<DrugManager>(list);
		ResultMessage resultMessage=new ResultMessage(pb);
        return Response.ok(resultMessage).build();
    }
    

}