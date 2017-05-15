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
import com.qh.domain.drug. DrugDestroy;
import com.qh.api.drug.service.DrugDestroyService;
import com.qh.api.drug.dao.DrugBasicDao;
import com.qh.api.drug.dao.DrugDestroyDao;

/**
 * 
  * 药剂销毁服务
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
@Service
@Transactional
public class DrugDestroyServiceImpl implements DrugDestroyService {
    
	//药剂基本信息Dao
    @Autowired
    private DrugBasicDao drugBasicDao;
	//药剂销毁Dao
    @Autowired
    private DrugDestroyDao drugDestroyDao;

	/**
	 * 新增药剂销毁
	 * @param 药剂销毁
	 * @return ResultMessage 对象
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response add(String modelStr){
		DrugDestroy drugDestroy= JSON.parseObject(modelStr, DrugDestroy.class);
		drugDestroy.setId(UUIDUtil.uuid());
		drugDestroy.setDeleteFlag(Constants.DATA_NORMAL_STATUS);
		drugDestroy.setCreateDate(new Date());
		drugDestroy.setModifyDate(new Date());
		drugDestroyDao.insert(drugDestroy);

		
		ResultMessage resultMessage=new ResultMessage(drugDestroy);
		return Response.ok(resultMessage).build();
	}

	/**
	 * 修改药剂销毁
	 * @param 药剂销毁
	 * @return ResultMessage 对象
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response update(String id,String modelStr){
		DrugDestroy drugDestroy= JSON.parseObject(modelStr, DrugDestroy.class);
		drugDestroy.setModifyDate(new Date());
		drugDestroyDao.updateSelective(drugDestroy);
		

			
		ResultMessage resultMessage=new ResultMessage(drugDestroy);
		return Response.ok(resultMessage).build();
	}
	
	/**
	 * 根据id查询药剂销毁
	 * @param id
	 * @return 药剂销毁
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response queryById(String id){
		DrugDestroy drugDestroy = new DrugDestroy();
		drugDestroy.setId(id);
		drugDestroy=drugDestroyDao.getInfo(drugDestroy);
		//查询关联信息		
		DrugBasic drugBasic = new DrugBasic();
		drugBasic.setId(drugDestroy.getDrugId());
	
		DrugBasic drugBasicInfo = drugBasicDao.getInfo(drugBasic);
		drugDestroy.setDrugBasic(drugBasicInfo);
		//查询关联信息结束	
		
		ResultMessage resultMessage=new ResultMessage(drugDestroy);
		return Response.ok(resultMessage).build();
	}
	/**
	 * 根据药剂销毁查询药剂销毁
	 * @param 药剂销毁
	 * @return 药剂销毁
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response queryByInfo(String modelStr){
		DrugDestroy drugDestroy= JSON.parseObject(modelStr, DrugDestroy.class);
		drugDestroy=drugDestroyDao.getInfo(drugDestroy);
		//查询关联信息		
		DrugBasic drugBasic = new DrugBasic();
		drugBasic.setId(drugDestroy.getDrugId());
	
		DrugBasic drugBasicInfo = drugBasicDao.getInfo(drugBasic);
		drugDestroy.setDrugBasic(drugBasicInfo);
		//查询关联信息结束	
		
		ResultMessage resultMessage=new ResultMessage(drugDestroy);
		return Response.ok(resultMessage).build();
	}
	
	/**
	 * 分页查询药剂销毁(单表)
	 * @param start 开始记录,size每页记录,modelStr 药剂销毁
	 * @return 药剂销毁集合
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
    @Override
    public Response queryAll(Integer start,Integer size,String modelStr){
     
      	DrugDestroy drugDestroy= JSON.parseObject(modelStr, DrugDestroy.class);
        PageHelper.startPage((size==0?1:start),size,"drugDestroy.modify_date desc");//排序
        List<DrugDestroy> list = drugDestroyDao.getAll(drugDestroy);
        PageInfo<DrugDestroy> pb=new PageInfo<DrugDestroy>(list);
		ResultMessage resultMessage=new ResultMessage(pb);
        return Response.ok(resultMessage).build();
    }
    
	/**
	 * 分页查询药剂销毁(内关联)
	 * @param start 开始记录,size每页记录,modelStr 药剂销毁
	 * @return 药剂销毁集合
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
    @Override
    public Response queryAllInnerJoin(Integer start,Integer size,String modelStr){
    	DrugDestroy drugDestroy= JSON.parseObject(modelStr, DrugDestroy.class);
        if(size==0){
	        PageHelper.startPage(1,size,"drugDestroy.modify_date desc");//排序
      	}else{
	        PageHelper.startPage(((start/size)+1),size,"drugDestroy.modify_date desc");//排序
      	}
      	List<DrugDestroy> list = drugDestroyDao.getAllInnerJoin(drugDestroy);
        PageInfo<DrugDestroy> pb=new PageInfo<DrugDestroy>(list);
		ResultMessage resultMessage=new ResultMessage(pb);
        return Response.ok(resultMessage).build();
    }
    
	/**
	 * 分页查询药剂销毁(左关联)
	 * @param start 开始记录,size每页记录,modelStr 药剂销毁
	 * @return 药剂销毁集合
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
    @Override
    public Response queryAllLeftJoin(Integer start,Integer size,String modelStr){
    
    	DrugDestroy drugDestroy= JSON.parseObject(modelStr, DrugDestroy.class);
		if(size==0){
	        PageHelper.startPage(1,size,"drugDestroy.modify_date desc");//排序
      	}else{
	        PageHelper.startPage(((start/size)+1),size,"drugDestroy.modify_date desc");//排序
      	}        
      	List<DrugDestroy> list = drugDestroyDao.getAllLeftJoin(drugDestroy);
        PageInfo<DrugDestroy> pb=new PageInfo<DrugDestroy>(list);
		ResultMessage resultMessage=new ResultMessage(pb);
		
        return Response.ok(resultMessage).build();
    }

}