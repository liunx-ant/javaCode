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

import com.qh.domain.drug. DrugBasic;
import com.qh.api.drug.service.DrugBasicService;
import com.qh.api.drug.dao.DrugBasicDao;

/**
 * 
  * 药剂基本信息服务
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
@Service
@Transactional
public class DrugBasicServiceImpl implements DrugBasicService {
    
	//药剂基本信息Dao
    @Autowired
    private DrugBasicDao drugBasicDao;

	/**
	 * 新增药剂基本信息
	 * @param 药剂基本信息
	 * @return ResultMessage 对象
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response add(String modelStr){
		DrugBasic drugBasic= JSON.parseObject(modelStr, DrugBasic.class);
		drugBasic.setId(UUIDUtil.uuid());
		drugBasic.setDeleteFlag(Constants.DATA_NORMAL_STATUS);
		drugBasic.setCreateDate(new Date());
		drugBasic.setModifyDate(new Date());
		drugBasicDao.insert(drugBasic);

		
		ResultMessage resultMessage=new ResultMessage(drugBasic);
		return Response.ok(resultMessage).build();
	}

	/**
	 * 修改药剂基本信息
	 * @param 药剂基本信息
	 * @return ResultMessage 对象
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response update(String id,String modelStr){
		DrugBasic drugBasic= JSON.parseObject(modelStr, DrugBasic.class);
		drugBasic.setModifyDate(new Date());
		drugBasicDao.updateSelective(drugBasic);
		

			
		ResultMessage resultMessage=new ResultMessage(drugBasic);
		return Response.ok(resultMessage).build();
	}
	
	/**
	 * 根据id查询药剂基本信息
	 * @param id
	 * @return 药剂基本信息
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response queryById(String id){
		DrugBasic drugBasic = new DrugBasic();
		drugBasic.setId(id);
		drugBasic=drugBasicDao.getInfo(drugBasic);
		
	
		
		ResultMessage resultMessage=new ResultMessage(drugBasic);
		return Response.ok(resultMessage).build();
	}
	/**
	 * 根据药剂基本信息查询药剂基本信息
	 * @param 药剂基本信息
	 * @return 药剂基本信息
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response queryByInfo(String modelStr){
		DrugBasic drugBasic= JSON.parseObject(modelStr, DrugBasic.class);
		drugBasic=drugBasicDao.getInfo(drugBasic);
		
	
		
		ResultMessage resultMessage=new ResultMessage(drugBasic);
		return Response.ok(resultMessage).build();
	}
	
	/**
	 * 分页查询药剂基本信息(单表)
	 * @param start 开始记录,size每页记录,modelStr 药剂基本信息
	 * @return 药剂基本信息集合
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
    @Override
    public Response queryAll(Integer start,Integer size,String modelStr){
     
      	DrugBasic drugBasic= JSON.parseObject(modelStr, DrugBasic.class);
        PageHelper.startPage((size==0?1:start),size,"drugBasic.modify_date desc");//排序
        List<DrugBasic> list = drugBasicDao.getAll(drugBasic);
        PageInfo<DrugBasic> pb=new PageInfo<DrugBasic>(list);
		ResultMessage resultMessage=new ResultMessage(pb);
        return Response.ok(resultMessage).build();
    }
    

}