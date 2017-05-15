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

import com.qh.domain.drug. DrugOrder;
import com.qh.api.drug.service.DrugOrderService;
import com.qh.api.drug.dao.DrugOrderDao;

/**
 * 
  * 采购订单服务
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
@Service
@Transactional
public class DrugOrderServiceImpl implements DrugOrderService {
    
	//采购订单Dao
    @Autowired
    private DrugOrderDao drugOrderDao;

	/**
	 * 新增采购订单
	 * @param 采购订单
	 * @return ResultMessage 对象
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response add(String modelStr){
		DrugOrder drugOrder= JSON.parseObject(modelStr, DrugOrder.class);
		drugOrder.setId(UUIDUtil.uuid());
		drugOrder.setDeleteFlag(Constants.DATA_NORMAL_STATUS);
		drugOrder.setCreateDate(new Date());
		drugOrder.setModifyDate(new Date());
		drugOrderDao.insert(drugOrder);

		
		ResultMessage resultMessage=new ResultMessage(drugOrder);
		return Response.ok(resultMessage).build();
	}

	/**
	 * 修改采购订单
	 * @param 采购订单
	 * @return ResultMessage 对象
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response update(String id,String modelStr){
		DrugOrder drugOrder= JSON.parseObject(modelStr, DrugOrder.class);
		drugOrder.setModifyDate(new Date());
		drugOrderDao.updateSelective(drugOrder);
		

			
		ResultMessage resultMessage=new ResultMessage(drugOrder);
		return Response.ok(resultMessage).build();
	}
	
	/**
	 * 根据id查询采购订单
	 * @param id
	 * @return 采购订单
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response queryById(String id){
		DrugOrder drugOrder = new DrugOrder();
		drugOrder.setId(id);
		drugOrder=drugOrderDao.getInfo(drugOrder);
		
	
		
		ResultMessage resultMessage=new ResultMessage(drugOrder);
		return Response.ok(resultMessage).build();
	}
	/**
	 * 根据采购订单查询采购订单
	 * @param 采购订单
	 * @return 采购订单
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response queryByInfo(String modelStr){
		DrugOrder drugOrder= JSON.parseObject(modelStr, DrugOrder.class);
		drugOrder=drugOrderDao.getInfo(drugOrder);
		
	
		
		ResultMessage resultMessage=new ResultMessage(drugOrder);
		return Response.ok(resultMessage).build();
	}
	
	/**
	 * 分页查询采购订单(单表)
	 * @param start 开始记录,size每页记录,modelStr 采购订单
	 * @return 采购订单集合
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
    @Override
    public Response queryAll(Integer start,Integer size,String modelStr){
     
      	DrugOrder drugOrder= JSON.parseObject(modelStr, DrugOrder.class);
        PageHelper.startPage((size==0?1:start),size,"drugOrder.modify_date desc");//排序
        List<DrugOrder> list = drugOrderDao.getAll(drugOrder);
        PageInfo<DrugOrder> pb=new PageInfo<DrugOrder>(list);
		ResultMessage resultMessage=new ResultMessage(pb);
        return Response.ok(resultMessage).build();
    }
    

}