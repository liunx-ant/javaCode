package ${mainObject.apiPackageName}.dao;

import java.util.List;
import com.foriseland.fjf.annotation.MyBatisRepository;
import com.foriseland.fjf.base.IBaseMapper;
import ${mainObject.domainPackageName}.${mainObject.className};
import ${mainObject.domainPackageName}.${mainObject.className}Vo;
import ${projStructurePath}.util.dao.BaseDao;

 /**
  * 
  * ${mainObject.title}dao
  * @author ${author}
  * @version ${version}
  * @date ${nowDate}
  * Copyright ${copyright}
 */
@MyBatisRepository
public interface ${mainObject.className}Dao extends IBaseMapper<${mainObject.className}>{

	/**
	 * 删除实体信息
	 * 
	 * @param entity 实体
	 *  @return  更新数量          
	 * @author ${author}
	 * @date ${nowDate}
	 */
	public int delete(E entity);
	
	/**
	 * 根据多个参数进行查询结果集返回行
	 * @param 
	 * @return 
	 * @author ${author}
	 * @date ${nowDate}
	 */
	public E  getInfo(E entity);
	
		/**
	 *  分页
	 * @param 
	 * @return 
	 * @author ${author}
	 * @date ${nowDate}
	 */
	public List<${mainObject.className}Vo> getAll(E entity);
}