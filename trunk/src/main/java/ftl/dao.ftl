package ${mainObject.daoPackageName}.dao;

import java.util.List;
import com.foriseland.fjf.annotation.MyBatisRepository;
import com.foriseland.fjf.base.IBaseMapper;
import ${mainObject.domainPackageName}.domain.${mainObject.className};
import ${mainObject.apiPackageName}.consumer.dto.${mainObject.className}Dto;
import ${mainObject.apiPackageName}.consumer.vo.${mainObject.className}Vo;

 /**
  * 
  * ${mainObject.title}dao
  * @author ${author}
  * @version ${version}
  * @date ${nowDate}
  * Copyright ${copyright}
 */
@MyBatisRepository
public interface I${mainObject.className}Dao extends IBaseMapper<${mainObject.className}>{

	/**
	 * 删除实体信息
	 * 
	 * @param entity 实体
	 *  @return  更新数量          
	 * @author ${author}
	 * @date ${nowDate}
	 */
	public int delete(${mainObject.className} entity);
	
	/**
	 * 根据多个参数进行查询结果集返回行
	 * @param 
	 * @return 
	 * @author ${author}
	 * @date ${nowDate}
	 */
	public ${mainObject.className}  getInfo(${mainObject.className} entity);
	
		/**
	 *  分页
	 * @param 
	 * @return 
	 * @author ${author}
	 * @date ${nowDate}
	 */
	public List<${mainObject.className}Vo> getAll(${mainObject.className}Dto dto);
}