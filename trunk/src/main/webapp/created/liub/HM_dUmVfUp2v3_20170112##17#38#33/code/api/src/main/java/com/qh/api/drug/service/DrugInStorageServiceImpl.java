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

import com.qh.domain.drug. DrugInStorage;
import com.qh.api.drug.service.DrugInStorageService;
import com.qh.api.drug.dao.DrugInStorageDao;

/**
 * 
  * 采购入库服务
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
@Service
@Transactional
public class DrugInStorageServiceImpl implements DrugInStorageService {
    
	//采购入库Dao
    @Autowired
    private DrugInStorageDao drugInStorageDao;

	/**
	 * 新增采购入库
	 * @param 采购入库
	 * @return ResultMessage 对象
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response add(String modelStr){
		DrugInStorage drugInStorage= JSON.parseObject(modelStr, DrugInStorage.class);
		drugInStorage.setId(UUIDUtil.uuid());
		drugInStorage.setDeleteFlag(Constants.DATA_NORMAL_STATUS);
		drugInStorage.setCreateDate(new Date());
		drugInStorage.setModifyDate(new Date());
		drugInStorageDao.insert(drugInStorage);

		
		ResultMessage resultMessage=new ResultMessage(drugInStorage);
		return Response.ok(resultMessage).build();
	}

	/**
	 * 修改采购入库
	 * @param 采购入库
	 * @return ResultMessage 对象
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response update(String id,String modelStr){
		DrugInStorage drugInStorage= JSON.parseObject(modelStr, DrugInStorage.class);
		drugInStorage.setModifyDate(new Date());
		drugInStorageDao.updateSelective(drugInStorage);
		

			
		ResultMessage resultMessage=new ResultMessage(drugInStorage);
		return Response.ok(resultMessage).build();
	}
	
	/**
	 * 根据id查询采购入库
	 * @param id
	 * @return 采购入库
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response queryById(String id){
		DrugInStorage drugInStorage = new DrugInStorage();
		drugInStorage.setId(id);
		drugInStorage=drugInStorageDao.getInfo(drugInStorage);
		
	
		
		ResultMessage resultMessage=new ResultMessage(drugInStorage);
		return Response.ok(resultMessage).build();
	}
	/**
	 * 根据采购入库查询采购入库
	 * @param 采购入库
	 * @return 采购入库
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	@Override
	public Response queryByInfo(String modelStr){
		DrugInStorage drugInStorage= JSON.parseObject(modelStr, DrugInStorage.class);
		drugInStorage=drugInStorageDao.getInfo(drugInStorage);
		
	
		
		ResultMessage resultMessage=new ResultMessage(drugInStorage);
		return Response.ok(resultMessage).build();
	}
	
	/**
	 * 分页查询采购入库(单表)
	 * @param start 开始记录,size每页记录,modelStr 采购入库
	 * @return 采购入库集合
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
    @Override
    public Response queryAll(Integer start,Integer size,String modelStr){
     
      	DrugInStorage drugInStorage= JSON.parseObject(modelStr, DrugInStorage.class);
        PageHelper.startPage((size==0?1:start),size,"drugInStorage.modify_date desc");//排序
        List<DrugInStorage> list = drugInStorageDao.getAll(drugInStorage);
        PageInfo<DrugInStorage> pb=new PageInfo<DrugInStorage>(list);
		ResultMessage resultMessage=new ResultMessage(pb);
        return Response.ok(resultMessage).build();
    }
    

}