package com.qh.util.dao;
import java.util.List;

/**
  * 基础数据操作
  * @author liub
  * @version 1.2.3
  * @date 2017-04-01 14:37:44
  * Copyright 北京超级酸
 */
public interface BaseDao<E> {

	/**
	 * 实体增加
	 * 
	 * @param entity 实体对象
	 * @return int 实体增加数量
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	public int  insert(E entity);

	/**
	 * 批量实体增加
	 * 
	 * @param entitys 实体对象
	 * @return int 实体增加数量
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	public int  insertBatch(List<E> list);
	
	/**
	 * 修改实体信息(修改所有)
	 * 
	 * @param entity 实体
	 *  @return  更新数量          
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	public int  update(E entity);
	
	/**
	 * 修改实体信息(修改不为null的值)
	 * 
	 * @param entity 实体
	 *  @return  更新数量          
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	public int  updateSelective(E entity);
	
	/**
	 * 删除实体信息
	 * 
	 * @param entity 实体
	 *  @return  更新数量          
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	public int delete(E entity);
	
	/**
	 * 根据多个参数进行查询结果集返回行
	 * @param 
	 * @return 
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	public E  getInfo(E entity);
	
	/**
	 *  分页
	 * @param 
	 * @return 
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	public List<E> getAll(E entity);
	
	/**
	 *  分页(内连接)
	 * @param 
	 * @return 
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	public List<E> getAllInnerJoin(E entity);
	
	/**
	 *  分页(左连接)
	 * @param 
	 * @return 
	 * @author liub
	 * @date 2017-04-01 14:37:44
	 */
	public List<E> getAllLeftJoin(E entity);
	
}