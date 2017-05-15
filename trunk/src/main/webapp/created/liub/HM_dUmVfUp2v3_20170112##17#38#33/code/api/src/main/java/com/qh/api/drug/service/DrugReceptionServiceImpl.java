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

import com.qh.domain.drug. DrugReception;
import com.qh.api.drug.service.DrugReceptionService;
import com.qh.api.drug.dao.DrugReceptionDao;

/**
 * 
  * 药剂领用服务
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
@Service
@Transactional
public class DrugReceptionServiceImpl implements DrugReceptionService {
    
	//药剂领用Dao
    @Autowired
    private DrugReceptionDao drugReceptionDao;

	/**
	 * 新增药剂领用
	 * @param 药剂领用
	 * @return ResultMessage 对象
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response add(String modelStr){
		DrugReception drugReception= JSON.parseObject(modelStr, DrugReception.class);
		drugReception.setId(UUIDUtil.uuid());
		drugReception.setDeleteFlag(Constants.DATA_NORMAL_STATUS);
		drugReception.setCreateDate(new Date());
		drugReception.setModifyDate(new Date());
		drugReceptionDao.insert(drugReception);

		
		ResultMessage resultMessage=new ResultMessage(drugReception);
		return Response.ok(resultMessage).build();
	}

	/**
	 * 修改药剂领用
	 * @param 药剂领用
	 * @return ResultMessage 对象
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response update(String id,String modelStr){
		DrugReception drugReception= JSON.parseObject(modelStr, DrugReception.class);
		drugReception.setModifyDate(new Date());
		drugReceptionDao.updateSelective(drugReception);
		

			
		ResultMessage resultMessage=new ResultMessage(drugReception);
		return Response.ok(resultMessage).build();
	}
	
	/**
	 * 根据id查询药剂领用
	 * @param id
	 * @return 药剂领用
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response queryById(String id){
		DrugReception drugReception = new DrugReception();
		drugReception.setId(id);
		drugReception=drugReceptionDao.getInfo(drugReception);
		
	
		
		ResultMessage resultMessage=new ResultMessage(drugReception);
		return Response.ok(resultMessage).build();
	}
	/**
	 * 根据药剂领用查询药剂领用
	 * @param 药剂领用
	 * @return 药剂领用
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response queryByInfo(String modelStr){
		DrugReception drugReception= JSON.parseObject(modelStr, DrugReception.class);
		drugReception=drugReceptionDao.getInfo(drugReception);
		
	
		
		ResultMessage resultMessage=new ResultMessage(drugReception);
		return Response.ok(resultMessage).build();
	}
	
	/**
	 * 分页查询药剂领用(单表)
	 * @param start 开始记录,size每页记录,modelStr 药剂领用
	 * @return 药剂领用集合
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
    @Override
    public Response queryAll(Integer start,Integer size,String modelStr){
     
      	DrugReception drugReception= JSON.parseObject(modelStr, DrugReception.class);
        PageHelper.startPage((size==0?1:start),size,"drugReception.modify_date desc");//排序
        List<DrugReception> list = drugReceptionDao.getAll(drugReception);
        PageInfo<DrugReception> pb=new PageInfo<DrugReception>(list);
		ResultMessage resultMessage=new ResultMessage(pb);
        return Response.ok(resultMessage).build();
    }
    

}